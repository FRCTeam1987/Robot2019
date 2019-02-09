package frc.team1987.commands.claw;

import edu.wpi.first.wpilibj.command.Command;
import frc.team1987.Robot;

public class ClawCollectCargo extends Command {
  public ClawCollectCargo() {
    requires(Robot.claw);
  }

  @Override
  protected void initialize() {
    Robot.claw.setWheels(-0.4);
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
    end();
  }
}
