package frc.robot.commands.claw;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RumbleWhenHasHatch extends Command {
  
  public RumbleWhenHasHatch() {
    requires(Robot.claw);
    setTimeout(1.5);
  }

  @Override
  protected void initialize() {
    if (Robot.claw.isInReleaseHatchPosition()) {
      Robot.claw.collectHatch();
    } else {
      Robot.claw.releaseHatch();
    }
  }

  @Override
  protected void execute() {
    if (Robot.claw.isHatchCollected()) {
      Robot.m_oi.rumbleDriver(1);
    }
    else {
      Robot.m_oi.rumbleDriver(0);
    }
  }

  @Override
  protected boolean isFinished() {
    return isTimedOut();
  }

  @Override
  protected void end() {
    Robot.m_oi.rumbleDriver(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
