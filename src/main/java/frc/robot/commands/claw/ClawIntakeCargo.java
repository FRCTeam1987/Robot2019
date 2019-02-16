package frc.robot.commands.claw;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ClawIntakeCargo extends Command {
  
  private final double m_percent = 0.5;

  public ClawIntakeCargo() {
    requires(Robot.claw);
  }

  @Override
  protected void initialize() {
    Robot.claw.setWheels(m_percent);
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return Robot.claw.isCargoCollected();
  }

  @Override
  protected void end() {
    Robot.claw.setWheels(0);
  }

  @Override
  protected void interrupted() {
  }
}
