package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class Claw extends Subsystem {
  
  private final WPI_TalonSRX clawIntake;
  private final DigitalInput cargoProx;
  private final Solenoid hatchFingers;

  public boolean m_hasHatch;

  public Claw() {
    clawIntake = new WPI_TalonSRX(RobotMap.clawIntakeMotorID);
    clawIntake.setName("Claw", "intake");
    cargoProx = new DigitalInput(RobotMap.clawCargoProxID);
    // hatchProx = new DigitalInput(RobotMap.clawHatchProxID);  
    // hatchSonar = new Ultrasonic(RobotMap.clawHatchSonarPingID, RobotMap.clawHatchSonarEchoID, Unit.kInches);    
    hatchFingers = new Solenoid(RobotMap.hatchRetractID);
    hatchFingers.setName("Claw", "hatch-release");

    m_hasHatch = false;
  }

  public void setWheels(final double percent) {
    clawIntake.set(ControlMode.PercentOutput, percent);
  }

  public boolean isCargoCollected() {
    return !cargoProx.get();
  }

  public boolean isHatchCollected() {
    return m_hasHatch;
  }

  public void setHatchCollected(final boolean hasHatch) {
    this.m_hasHatch = hasHatch;
  }

  public void releaseHatch() {
    hatchFingers.set(true);
  }

  public void collectHatch() {
    hatchFingers.set(false);
  }

  public void periodic() {
    SmartDashboard.putBoolean("Cargo Collected", isCargoCollected());
  }

  @Override
  public void initDefaultCommand() {
  }

  public void setRumble(final double power) {
    Robot.m_oi.getDriver().setRumble(RumbleType.kLeftRumble, power);
    Robot.m_oi.getDriver().setRumble(RumbleType.kRightRumble, power);
  }
}