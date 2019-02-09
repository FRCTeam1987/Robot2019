package frc.team1987.commands.claw;

import edu.wpi.first.wpilibj.command.Command;
import frc.team1987.Robot;

public class ShootCargo extends Command {
 
private double m_percent;

  public ShootCargo(final double percent) {
    requires(Robot.claw);
    m_percent = percent;
    setTimeout(0.5);
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
    return isTimedOut();
  }

  @Override
  protected void end() {
    Robot.claw.setWheels(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
