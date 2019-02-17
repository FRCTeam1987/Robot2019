package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.util.Util;

public class Arm extends Subsystem {

  private final WPI_TalonSRX wrist;

  public Arm() {
    wrist = new WPI_TalonSRX(RobotMap.armMotorID);
    wrist.setName("Arm", "wrist");
    configWrist();
    zeroWristEncoder();
  }

  public void configWrist() {
    // wrist.configMotionCruiseVelocity(443);
    // wrist.configMotionAcceleration(443);
    Util.configTalonSRXWithEncoder(wrist, false);
    wrist.config_kF(0, 0.0, 0);
    wrist.config_kP(0, 0.975, 0);
    wrist.config_kI(0, 0.0, 0);
    wrist.config_kD(0, 0.4, 0);
    setBrake();
  }

  public void setWristAbsolute(final double desiredDegrees) {
    final int ticksAbsolute = Util.degreesToTicks(desiredDegrees, RobotMap.wristGearboxReduction);

    wrist.set(ControlMode.MotionMagic, ticksAbsolute);
  }

  public void setWristPercent(final double percent) {
    wrist.set(ControlMode.PercentOutput, percent);
  }

  public boolean isWithinTolerance(final double desiredDegrees) {
    return Util.isWithinTolerance(getArmTicks(), Util.degreesToTicks(desiredDegrees, RobotMap.wristGearboxReduction), RobotMap.wristTolerance);
  }

  public boolean isArmFront() {
    if (getArmAngle() >= 0) {
      return true;
    }
    else if (getArmAngle() < 0) {
      return false;
    }
    else {
      return true;
    }
  }

  public void zeroWristEncoder() {
    wrist.setSelectedSensorPosition(0);
  }
  
  public int getArmTicks() {
    return (int)(getRawTicks() * RobotMap.wristGearboxReduction);
  }

  public double getArmAngle() {
    return Util.ticksToDegrees((int)getArmTicks());
  }

  private int getRawTicks() {
    return wrist.getSelectedSensorPosition();
  }

  public void setBrake() {
    wrist.setNeutralMode(NeutralMode.Brake);
  }

  public void setCoast() {
    wrist.setNeutralMode(NeutralMode.Coast);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Arm Angle", getArmAngle());
    // SmartDashboard.putBoolean("Is Arm in Front", isArmFront());
    // SmartDashboard.putNumber("Arm Ticks", getRawTicks());
  }

  @Override
  public void initDefaultCommand() {
    // setDefaultCommand(new MySpecialCommand());
  }
}