package frc.robot.commands.claw;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CollectHatch extends Command {
  public CollectHatch() {
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
    return false;
  }

  @Override
  protected void end() {
    Robot.claw.collectHatch();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
