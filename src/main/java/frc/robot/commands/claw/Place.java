package frc.robot.commands.claw;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class Place extends Command {
  
  private double m_percent;

  public Place(final double percent) {
    requires(Robot.claw);
    m_percent = percent;
    setTimeout(1.5);
  }

  @Override
  protected void initialize() {
    if (Robot.claw.isCargoCollected()) {
      Robot.claw.setWheels(-m_percent);
    }
    else {
      Robot.claw.releaseHatch();
    }
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
    Robot.claw.collectHatch();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
