package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.util.Util;

public class Climber extends Subsystem {
  private final WPI_TalonSRX winchMaster;
  private final WPI_TalonSRX winchSlave;

  public Climber() {
    winchMaster = new WPI_TalonSRX(RobotMap.winchMasterID);
    winchMaster.setName("Climber", "winch-master");
    winchSlave = new WPI_TalonSRX(RobotMap.winchSlaveID);
    winchSlave.setName("Climber", "winch-slave");

    configWinch(winchMaster, winchSlave, false); // may need to change inversion
  }
  
  public void configWinch(final WPI_TalonSRX master, final WPI_TalonSRX slave, final boolean isEncoderInverted) {
    final double secondsFromNeutralToFull = 0.15; // this number might change
    final double peakForwardPercent = 1.0;
    final double peakReversePercent = -1.0;
    final double nominalPercent = 0.0;

    master.configFactoryDefault();
    master.configPeakOutputForward(peakForwardPercent);
    master.configPeakOutputReverse(peakReversePercent);
    master.configNominalOutputForward(nominalPercent);
    master.configNominalOutputReverse(nominalPercent);
    master.setNeutralMode(NeutralMode.Brake); // might change to coast
    master.configOpenloopRamp(secondsFromNeutralToFull);

    slave.configFactoryDefault();
    slave.follow(master);

    Util.configTalonSRXWithEncoder(master, isEncoderInverted);

    zeroWinchEncoder();
  }

  public void zeroWinchEncoder() {
    winchMaster.setSelectedSensorPosition(0);
  }

  public int getEncoderTicks(final WPI_TalonSRX master) {
    return master.getSelectedSensorPosition();
  }

  public double getEncoderVelocity(final WPI_TalonSRX master) {
    return master.getSelectedSensorVelocity();
  }

  public double getEncoderRPM(final WPI_TalonSRX master) {
    return getEncoderVelocity(master) * (600.0 / RobotMap.ticksPerRotation);
  }

  public void setWinchMotor(final double percent) {
    winchMaster.set(ControlMode.PercentOutput, percent);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Climber RPM", getEncoderRPM(winchMaster));
  }

  @Override
  public void initDefaultCommand() {
  }
}
