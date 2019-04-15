package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class Claw extends Subsystem {
  
  private final WPI_TalonSRX clawIntake;
  private final DigitalInput cargoProx;
  private final DigitalInput topHatchSwitch;
  private final DigitalInput bottomHatchSwitch;
  private final DoubleSolenoid hatchFingers;

  public Claw() {
    clawIntake = new WPI_TalonSRX(RobotMap.clawIntakeMotorID);
    clawIntake.setName("Claw", "intake");
    cargoProx = new DigitalInput(RobotMap.clawCargoProxID);
    topHatchSwitch = new DigitalInput(RobotMap.clawTopHatchSwitchID);
    bottomHatchSwitch = new DigitalInput(RobotMap.clawBottomHatchSwitchID);
    hatchFingers = new DoubleSolenoid(RobotMap.hatchFingersOutID, RobotMap.hatchFingersInID);
    hatchFingers.setName("Claw", "hatch-release");
  }

  public void setWheels(final double percent) {
    clawIntake.set(ControlMode.PercentOutput, percent);
  }

  public boolean isCargoCollected() {
    return !cargoProx.get();
  }

  public boolean isHatchCollected() {
    return !topHatchSwitch.get();
  }

  public void setHatchCollected(final boolean hasHatch) {
    // this.m_hasHatch = hasHatch;
  }

  public void releaseHatch() {
    hatchFingers.set(Value.kReverse);
  }

  public void collectHatch() {
    hatchFingers.set(Value.kForward);
  }

  public boolean isInReleaseHatchPosition() {
    return hatchFingers.get() == Value.kReverse;
  }

  public void periodic() {
    SmartDashboard.putBoolean("Cargo Collected", isCargoCollected());
    SmartDashboard.putBoolean("Both Hatch Collected", isHatchCollected());

  }

  @Override
  public void initDefaultCommand() {
  }
}