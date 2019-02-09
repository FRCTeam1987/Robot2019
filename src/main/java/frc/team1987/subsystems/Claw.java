package frc.team1987.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team1987.RobotMap;

public class Claw extends Subsystem {

  private final WPI_TalonSRX intake;
  private final DigitalInput cargoProx;
  private final DigitalInput hatchProxTop;
  private final DigitalInput hatchProxBottom;
  private final DoubleSolenoid hatchRelease;

  public Claw() {

    intake = new WPI_TalonSRX(RobotMap.clawIntakeMotorID);
    cargoProx = new DigitalInput(RobotMap.clawCargoProxID);
    hatchProxTop = new DigitalInput(RobotMap.clawHatchProxTopID);
    hatchProxBottom = new DigitalInput(RobotMap.clawHatchProxBottomID);
    hatchRelease = new DoubleSolenoid(RobotMap.hatchRetractID, RobotMap.hatchReleaseID);
  }

  public void setWheels(final double percent) {
    intake.set(ControlMode.PercentOutput, percent);
  }

  public boolean isCargoCollected() {
    return cargoProx.get();
  }

  public boolean isHatchCollected() {
    return hatchProxTop.get() && hatchProxBottom.get();
  }

  public void releaseHatch() {
    hatchRelease.set(Value.kReverse);
  }

  public void retractHatchPistons() {
    hatchRelease.set(Value.kForward);
  }

  @Override
  public void initDefaultCommand() {
  }
}
