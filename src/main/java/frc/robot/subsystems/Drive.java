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
import frc.robot.DriveMode;
import frc.robot.RobotMap;
import frc.robot.util.Util;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;
import frc.robot.commands.drive.XboxDrive;

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

  private double lastGyroError;

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
    
    master.setSafetyEnabled(false);

    Arrays.asList(slaves).forEach(slave -> {
      slave.configFactoryDefault();
      slave.follow(master);
    });
    setCoast();

    setPID(DriveMode.STRAIGHT);
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

  public void arcadeDrive(final double speed, final double rotation) {
    robotDrive.arcadeDrive(speed, rotation);
  }

  public void set(final ControlMode controlMode, final double left, final double right) {
    leftMaster.set(controlMode, left);
    rightMaster.set(controlMode, right);
  }

  public void setBrake() {
    leftMaster.setNeutralMode(NeutralMode.Brake);
    rightMaster.setNeutralMode(NeutralMode.Brake);
    isBrake = true;
  }

  public void setCoast() {
		leftMaster.setNeutralMode(NeutralMode.Coast);
    rightMaster.setNeutralMode(NeutralMode.Coast);
    isBrake = false;
  }

  public boolean isBrake() {
    return isBrake;
  }

  public boolean isHighGear() {
    return shifter.get() == Value.kForward;
  }

  public void setHighGear() {
    shifter.set(Value.kForward);
    setCoast();
  }
  
  public void setLowGear() {
    shifter.set(Value.kReverse);
    setBrake();
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

  public int getLeftRawEncoderPosition() {
    return leftMaster.getSelectedSensorPosition(0);   
  }

  public int getRightRawEncoderPosition() {
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

  public double getGyroRate() {
		return ahrs.getRate();
	}

  public void zeroDriveEncoders() {
    leftMaster.setSelectedSensorPosition(0);
    rightMaster.setSelectedSensorPosition(0);
  }

  public int getEncoderTicks(final WPI_TalonSRX master) {
    return master.getSelectedSensorPosition(0);
  }

  public void setPID(final DriveMode driveMode) {
    if (isHighGear()) {
      switch (driveMode) {
      case STRAIGHT:
        leftMaster.config_kP(0, RobotMap.driveStraightHighKP, 0);
        leftMaster.config_kI(0, 0, 0);
        leftMaster.config_kD(0, RobotMap.driveStraightHighKD, 0);
        rightMaster.config_kP(0, RobotMap.driveStraightHighKP, 0);
        rightMaster.config_kI(0, 0, 0);
        rightMaster.config_kD(0, RobotMap.driveStraightHighKD, 0);
        System.out.println("High Gear Straight");
        leftMaster.configMotionCruiseVelocity(1100);
        rightMaster.configMotionCruiseVelocity(1100);
        leftMaster.configMotionAcceleration(1100);
        rightMaster.configMotionAcceleration(1100);
        break;
      case PIVOT:
        leftMaster.config_kP(0, RobotMap.drivePivotHighKP, 0);
        leftMaster.config_kI(0, 0, 0);
        leftMaster.config_kD(0, RobotMap.drivePivotHighKD, 0);
        rightMaster.config_kP(0, RobotMap.drivePivotHighKP, 0);
        rightMaster.config_kI(0, 0, 0);
        rightMaster.config_kD(0, RobotMap.drivePivotHighKD, 0);
        break;
      }

    } else {
      switch (driveMode) {
        case STRAIGHT:
          leftMaster.config_kP(0, RobotMap.driveStraightLowKP, 0);
          leftMaster.config_kI(0, 0, 0);
          leftMaster.config_kD(0, RobotMap.driveStraightLowKD, 0);
          rightMaster.config_kP(0, RobotMap.driveStraightLowKP, 0);
          rightMaster.config_kI(0, 0, 0);
          rightMaster.config_kD(0, RobotMap.driveStraightLowKD, 0);
          System.out.println("Low Gear Straight");
          leftMaster.configMotionCruiseVelocity(1100);
          rightMaster.configMotionCruiseVelocity(1100);
          leftMaster.configMotionAcceleration(1100);
          rightMaster.configMotionAcceleration(1100);
          break;
        case PIVOT:
          leftMaster.config_kP(0, RobotMap.drivePivotLowKP, 0);
          leftMaster.config_kI(0, 0, 0);
          leftMaster.config_kD(0, RobotMap.drivePivotLowKD, 0);
          rightMaster.config_kP(0, RobotMap.drivePivotLowKP, 0);
          rightMaster.config_kI(0, 0, 0);
          rightMaster.config_kD(0, RobotMap.drivePivotLowKD, 0);
          break;
      }
    }
  }

  public EncoderFollower[] setupPath(Trajectory toFollow) {
    EncoderFollower left = new EncoderFollower();
    EncoderFollower right = new EncoderFollower();
    TankModifier modifier = new TankModifier(toFollow).modify(RobotMap.wheelBaseDiameter);
    left = new EncoderFollower(modifier.getLeftTrajectory());
    right = new EncoderFollower(modifier.getRightTrajectory());
    left.configureEncoder(getLeftRawEncoderPosition(), (int)RobotMap.ticksPerRotation, RobotMap.driveBaseWheelsDiameter);
    right.configureEncoder(getRightRawEncoderPosition(), (int)RobotMap.ticksPerRotation, RobotMap.driveBaseWheelsDiameter);
    left.configurePIDVA(RobotMap.pathHighGearKP, RobotMap.pathHighGearKI, RobotMap.pathHighGearKD, RobotMap.pathHighGearKV, RobotMap.pathHighGearKA);
    right.configurePIDVA(RobotMap.pathHighGearKP, RobotMap.pathHighGearKI, RobotMap.pathHighGearKD, RobotMap.pathHighGearKV, RobotMap.pathHighGearKA);

    return new EncoderFollower[] {
      left,
      right
    };
  }

  public void followPath(EncoderFollower left, EncoderFollower right, boolean isReversed) { //not finished
    double l;
    double r;
    
    if (!isReversed){
      l = left.calculate(leftMaster.getSelectedSensorPosition(0));
      r = right.calculate(rightMaster.getSelectedSensorPosition(0));
    }

    else {
      l = left.calculate(leftMaster.getSelectedSensorPosition(0));
      r = right.calculate(rightMaster.getSelectedSensorPosition(0));
    }
  
    double gyroHeading = getAngle();
    double angleSetpoint = Pathfinder.r2d(left.getHeading());
    double angleDifference = Pathfinder.boundHalfDegrees(angleSetpoint - gyroHeading);

    double turn = RobotMap.pathGP * angleDifference + (RobotMap.pathGD *
    ((angleDifference - lastGyroError) / RobotMap.period));

    lastGyroError = angleDifference;

    if(!isReversed) {
      this.tankDrive(l + turn, r - turn);
    }
    else {
      this.tankDrive(-l + turn, -r - turn);
    }


  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new XboxDrive());
  }

  public void periodic() {
    // SmartDashboard.putNumber("Left Drive Encoder Ticks", getEncoderTicks(leftMaster));
    // SmartDashboard.putNumber("Right Drive Encoder Ticks", getEncoderTicks(rightMaster));
    SmartDashboard.putNumber("Left Drive Distance", getLeftEncoderDistance());
    SmartDashboard.putNumber("Right Drive Distance", getRightEncoderDistance());
    SmartDashboard.putNumber("Gyro Angle", getAngle());
  }
}