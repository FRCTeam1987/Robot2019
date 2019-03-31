package frc.robot.commands.claw;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class PlaceHatch extends Command {
  public PlaceHatch() {
    requires(Robot.claw);
    setTimeout(1);
  }

  @Override
  protected void initialize() {
    Robot.claw.releaseHatch();
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
    Robot.claw.collectHatch();
  }

  @Override
  protected void interrupted() {
  }
}
