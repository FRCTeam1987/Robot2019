package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.Util;

/**
 * Add your docs here.
 */
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
    // setBrake();
  }

  public void setWristAbsolute(final double desiredDegrees) {
    final int ticksAbsolute = degreesToTicks(desiredDegrees);

    wrist.set(ControlMode.MotionMagic, ticksAbsolute);
  }

  public boolean isWithinTolerance(final double desiredDegrees) {
    return Util.isWithinTolerance(wristGearboxReduction(getTicks()), degreesToTicks(desiredDegrees), RobotMap.wristTolerance);
  }

  private int getTicks() {
    return wrist.getSelectedSensorPosition();
  }
  private int degreesToTicks(final double degrees) {
    return (int) (((degrees / 360) * 4096) * 4.4444);
  }

  private double wristGearboxReduction(final int rawTicks) {
    return rawTicks * 4.44444;
  }

  public void setBrake() {
    wrist.setNeutralMode(NeutralMode.Brake);
  }

  public void setCoast() {
    wrist.setNeutralMode(NeutralMode.Coast);
  }

  @Override
  public void initDefaultCommand() {
    // setDefaultCommand(new MySpecialCommand());
  }
}
