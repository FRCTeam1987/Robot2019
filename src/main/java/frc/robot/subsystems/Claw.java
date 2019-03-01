package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

public class Claw extends Subsystem {
  
  private final WPI_TalonSRX clawIntake;
  private final DigitalInput cargoProx;
  private final DigitalInput hatchProx;
  private final DoubleSolenoid hatchRelease;

  public Claw() {
    clawIntake = new WPI_TalonSRX(RobotMap.clawIntakeMotorID);
    clawIntake.setName("Claw", "intake");
    cargoProx = new DigitalInput(RobotMap.clawCargoProxID);
    hatchProx = new DigitalInput(RobotMap.clawHatchProxID);  
    hatchRelease = new DoubleSolenoid(RobotMap.hatchRetractID, RobotMap.hatchReleaseID);
    hatchRelease.setName("Claw", "hatch-release");
    retractHatchPistons();
  }

  public void setWheels(final double percent) {
    clawIntake.set(ControlMode.PercentOutput, percent);
  }

  public boolean isCargoCollected() {
    return !cargoProx.get();
  }

  public boolean isHatchCollected() {   //untested - output to shuffleboard to check
    return hatchProx.get(); 
  }

  public void releaseHatch() {
    hatchRelease.set(Value.kReverse);
  }

  public void retractHatchPistons() {
    hatchRelease.set(Value.kForward);
  }

  public void periodic() {
    SmartDashboard.putBoolean("Cargo Collected", isCargoCollected());
  }

  @Override
  public void initDefaultCommand() {
  }
}