/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import java.util.Arrays;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.util.Util;
import frc.robot.commands.drive.TeleopDrive;

public class Drive extends Subsystem {
  
  private final WPI_TalonSRX leftMaster;
  private final WPI_TalonSRX leftSlave1;
  private final WPI_VictorSPX leftSlave2;
  private final WPI_TalonSRX rightMaster;
  private final WPI_TalonSRX rightSlave1;
  private final WPI_VictorSPX rightSlave2;
  private final DoubleSolenoid shifter;
  private final DifferentialDrive robotDrive;
  private final AHRS ahrs;
  private boolean isBrake;

  public Drive() {
    leftMaster = new WPI_TalonSRX(RobotMap.leftMasterID);
    leftSlave1 = new WPI_TalonSRX(RobotMap.leftSlave1ID);
    leftSlave2 = new WPI_VictorSPX(RobotMap.leftSlave2ID);
    rightMaster = new WPI_TalonSRX(RobotMap.rightMasterID);
    rightSlave1 = new WPI_TalonSRX(RobotMap.rightSlave1ID);
    rightSlave2 = new WPI_VictorSPX(RobotMap.rightSlave2ID);
    leftMaster.setName("Drive", "left-master");
    leftSlave1.setName("Drive", "left-slave1");
    leftSlave2.setName("Drive", "left-slave2");
    rightMaster.setName("Drive", "right-master");
    rightSlave1.setName("Drive", "right-slave1");
    rightSlave2.setName("Drive", "right-slave2");

    robotDrive = new DifferentialDrive(leftMaster, rightMaster);
    ahrs = new AHRS(SPI.Port.kMXP);
    shifter = new DoubleSolenoid(RobotMap.highGearID, RobotMap.lowGearID);

    configSideOfDrive(leftMaster, true, leftSlave1, leftSlave2);
    configSideOfDrive(rightMaster, false, rightSlave1, rightSlave2);

    zeroDriveEncoders();
    ahrsReset();
  }

  public void configSideOfDrive(final WPI_TalonSRX master, final boolean isEncoderInverted, final BaseMotorController... slaves) {
    final double secondsFromNeutralToFull = 0.15;
    final double peakForwardPercent = 1.0;
    final double peakReversePercent = -1.0;
    final double nominalPercent = 0.0;

    master.configFactoryDefault();
    master.configPeakOutputForward(peakForwardPercent);
    master.configPeakOutputReverse(peakReversePercent);
    master.configNominalOutputForward(nominalPercent);
    master.configNominalOutputReverse(nominalPercent);
    master.setNeutralMode(NeutralMode.Brake);
    master.configOpenloopRamp(secondsFromNeutralToFull);

    Arrays.asList(slaves).forEach(slave -> {
      slave.configFactoryDefault();
      slave.follow(master);
    });

    Util.configTalonSRXWithEncoder(master, isEncoderInverted);
  }

  public void xboxDrive(XboxController xbox) {
    double move = xbox.getTriggerAxis(Hand.kRight) - xbox.getTriggerAxis(Hand.kLeft);
    double rotate = xbox.getX(Hand.kLeft);

    robotDrive.arcadeDrive(move, rotate, true);
  }

  public void tankDrive(final double left, final double right) {
    robotDrive.tankDrive(left, right);
  }

  public void set(final ControlMode controlMode, final double left, final double right) {
    leftMaster.set(controlMode, left);
    rightMaster.set(controlMode, right);
  }

  // public void setPID(DriveMode mode) {
  //   double m_P;
  //   double m_I;
  //   double m_D;
   
  //   switch(mode) {
  //     case STRAIGHT:
  //       if(isHighGear()) {
  //         m_P = RobotMap.driveStraightHighP;
  //         m_I = RobotMap.driveStraightHighI;
  //         m_D = RobotMap.driveStraightHighD;
  //       }
  //       else {
  //         m_P = RobotMap.driveStraightLowP;
  //         m_I = RobotMap.driveStraightLowI;
  //         m_D = RobotMap.driveStraightLowD;
  //       }
  //       break;
  //     case PIVOT:
  //       if (isHighGear()) {
  //         m_P = RobotMap.drivePivotHighP;
  //         m_I = RobotMap.drivePivotHighI;
  //         m_D = RobotMap.drivePivotHighD;
  //       }
  //       else {
  //         m_P = RobotMap.drivePivotLowP;
  //         m_I = RobotMap.drivePivotLowI;
  //         m_D = RobotMap.drivePivotLowD;
  //       }
  //       break;
  //     default:
  //       return;
  //   }     
  // }

  public void setBrake() {
    leftMaster.setNeutralMode(NeutralMode.Brake);
    rightMaster.setNeutralMode(NeutralMode.Brake);
  }

  public void setCoast() {
		leftMaster.setNeutralMode(NeutralMode.Coast);
		rightMaster.setNeutralMode(NeutralMode.Coast);
  }

  public boolean isBrake() {
    return isBrake;
  }

  public boolean isHighGear() {
    return shifter.get() == Value.kForward;
  }

  public void setHighGear() {
    shifter.set(Value.kForward);
  }
  
  public void setLowGear() {
    shifter.set(Value.kReverse);
  }

  public void ahrsReset() {
    ahrs.reset();
  }

  public double getLeftPercentOutput() {
    return leftMaster.getMotorOutputPercent();
  }

  public double getRightPercentOutput() {
    return rightMaster.getMotorOutputPercent();
  }

  public double getLeftRawEncoderPosition() {
    return leftMaster.getSelectedSensorPosition(0);   
  }

  public double getRightRawEncoderPosition() {
    return rightMaster.getSelectedSensorPosition(0);
  }

  public double getLeftEncoderDistance() {
    return Util.rotationsToDistance(Util.getEncoderRotations(getLeftRawEncoderPosition()), RobotMap.driveBaseWheelsDiameter);
  }

  public double getRightEncoderDistance() {
    return Util.rotationsToDistance(Util.getEncoderRotations(getRightRawEncoderPosition()), RobotMap.driveBaseWheelsDiameter);
  }

  public double getAngle() {
    return ahrs.getAngle();
  }

  public void zeroDriveEncoders() {
    leftMaster.setSelectedSensorPosition(0);
    rightMaster.setSelectedSensorPosition(0);
  }

  public int getEncoderTicks(final WPI_TalonSRX master) {
    return master.getSelectedSensorPosition(0);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new TeleopDrive());
  }

  public void periodic() {
    SmartDashboard.putNumber("Left Drive Encoder Ticks", getEncoderTicks(leftMaster));
    SmartDashboard.putNumber("Right Drive Encoder Ticks", getEncoderTicks(rightMaster));
  }
}