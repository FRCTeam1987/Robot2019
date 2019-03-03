package frc.robot.commands.cargointake;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class IntakeCargo extends Command {

  public IntakeCargo() {
    requires(Robot.cargoIntake);
    requires(Robot.claw);
  }

  @Override
  protected void initialize() {
    Robot.cargoIntake.setRoller(-0.6);
    Robot.claw.setWheels(0.7);
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
    Robot.cargoIntake.setRoller(0.0);
    Robot.claw.setWheels(0.0);
    // Robot.cargoIntake.setIntakePivot(0.0);
  }

  @Override
  protected void interrupted() {
  }
}
