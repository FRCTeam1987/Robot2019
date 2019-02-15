package frc.robot.commands.claw;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class PlaceHatch extends Command {
  public PlaceHatch() {
    requires(Robot.claw);
    setTimeout(0.5); // temporary? no hatch prox sensors...
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
    // return !Robot.claw.isHatchCollected();
  }

  @Override
  protected void end() {
    Robot.claw.retractHatchPistons();
  }

  @Override
  protected void interrupted() {
  }
}
