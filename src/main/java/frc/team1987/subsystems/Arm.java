package frc.team1987.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team1987.RobotMap;
import frc.team1987.util.Util;

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

  public void setWristPercent(final double percent) {
    wrist.set(ControlMode.PercentOutput, percent);
  }

  public boolean isWithinTolerance(final double desiredDegrees) {
    return Util.isWithinTolerance(wristGearboxReduction(), degreesToTicks(desiredDegrees), RobotMap.wristTolerance);
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

  private int getTicks() {
    return wrist.getSelectedSensorPosition();
  }

  private int degreesToTicks(final double degrees) {
    return (int) (((degrees / 360) * 4096) * 4.4444);
  }

  private double wristGearboxReduction() {
    return getTicks() * RobotMap.wristGearboxReduction;
  }

  @Override
  public void initDefaultCommand() {
    // setDefaultCommand(new MySpecialCommand());
  }
}
