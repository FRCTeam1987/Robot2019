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
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import java.util.Arrays;
import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.Util;
import frc.robot.commands.drive.TeleopDrive;

public class Drive extends Subsystem {
  
  private final WPI_TalonSRX leftMaster;
  private final WPI_TalonSRX leftSlave1;
  private final WPI_VictorSPX leftSlave2;
  private final WPI_TalonSRX rightMaster;
  private final WPI_TalonSRX rightSlave1;
  private final WPI_VictorSPX rightSlave2;
  private final DifferentialDrive robotDrive;
  private final AHRS ahrs;

  public Drive() {
    leftMaster = new WPI_TalonSRX(RobotMap.leftMasterID);
    leftSlave1 = new WPI_TalonSRX(RobotMap.leftSlave1ID);
    leftSlave2 = new WPI_VictorSPX(RobotMap.leftSlave2ID);
    rightMaster = new WPI_TalonSRX(RobotMap.rightMasterID);
    rightSlave1 = new WPI_TalonSRX(RobotMap.rightSlave1ID);
    rightSlave2 = new WPI_VictorSPX(RobotMap.rightSlave2ID);
    leftMaster.setName("drive", "left-master");
    leftSlave1.setName("drive", "left-slave1");
    leftSlave2.setName("drive", "left-slave2");
    rightMaster.setName("drive", "right-master");
    rightSlave1.setName("drive", "right-slave1");
    rightSlave2.setName("drive", "right-slave2");

    robotDrive = new DifferentialDrive(leftMaster, rightMaster);
    ahrs = new AHRS(SPI.Port.kMXP);

    configSideOfDrive(leftMaster, leftSlave1, leftSlave2);
    configSideOfDrive(rightMaster, rightSlave1, rightSlave2);

    zeroDriveEncoders();
    ahrsReset();
  }

  public void configSideOfDrive(final WPI_TalonSRX master, final BaseMotorController... slaves) {
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

    Util.configTalonSRXWithEncoder(master);
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

  public void setBrake() {
    leftMaster.setNeutralMode(NeutralMode.Brake);
    rightMaster.setNeutralMode(NeutralMode.Brake);
  }

  public void setCoast() {
		leftMaster.setNeutralMode(NeutralMode.Coast);
		rightMaster.setNeutralMode(NeutralMode.Coast);
  }
  
  public void ahrsReset() {
    ahrs.reset();
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
}