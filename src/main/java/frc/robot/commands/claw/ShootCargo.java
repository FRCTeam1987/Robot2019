package frc.robot.commands.claw;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ShootCargo extends Command {
 
  private final double m_percent;

  public ShootCargo(final double percent) {
    requires(Robot.claw);
    setTimeout(1);
    m_percent = percent;
  }

  @Override
  protected void initialize() {
    Robot.claw.setWheels(-m_percent);
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return isTimedOut();
  }

  @Override
  protected void end() {
    Robot.claw.setWheels(0);
  }

  @Override
  protected void interrupted() {
  }
}
