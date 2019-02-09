package frc.team1987.commands.claw;

import edu.wpi.first.wpilibj.command.Command;
import frc.team1987.Robot;

public class PlaceHatch extends Command {
  public PlaceHatch() {
    requires(Robot.claw);
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
    return Robot.claw.isHatchCollected();
  }

  @Override
  protected void end() {
    Robot.claw.retractHatchPistons();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
