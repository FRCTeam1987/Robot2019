package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.util.Util;

public class Arm extends Subsystem {

  private final WPI_TalonSRX wrist;

  public Arm() {
    wrist = new WPI_TalonSRX(RobotMap.armMotorID);
    configWrist();
  }

  public void configWrist() {
    wrist.configMotionCruiseVelocity(443);
    wrist.configMotionAcceleration(443);
    Util.configTalonSRXWithEncoder(wrist, false);
    wrist.config_kF(0, 0.0, 0);
    wrist.config_kP(0, 0.975, 0);
    wrist.config_kI(0, 0.0, 0);
    wrist.config_kD(0, 0.4, 0);
    setBrake();
  }

  public void setWristAbsolute(final double desiredDegrees) {
    final int ticksAbsolute = degreesToTicks(desiredDegrees);

    wrist.set(ControlMode.MotionMagic, ticksAbsolute);
  }

  public boolean isWithinTolerance(final double desiredDegrees) {
    return Util.isWithinTolerance(getArmTicks(), degreesToTicks(desiredDegrees), RobotMap.wristTolerance);
  }

  public void setWristPercent(final double percent) {
    wrist.set(ControlMode.PercentOutput, percent);
  }

  public void zeroWristEncoder() {
    wrist.setSelectedSensorPosition(0);
  }

  public void setBrake() {
    wrist.setNeutralMode(NeutralMode.Brake);
  }

  public void setCoast() {
    wrist.setNeutralMode(NeutralMode.Coast);
  }

  private int getRawTicks() {
    return wrist.getSelectedSensorPosition();
  }
  private int degreesToTicks(final double degrees) {
    return (int) (((degrees / 360) * 4096) * 4.4444);
  }

  public double getArmTicks() {
    return getRawTicks() * RobotMap.wristGearboxReduction;
  }

  @Override
  public void initDefaultCommand() {
    // setDefaultCommand(new MySpecialCommand());
  }
}