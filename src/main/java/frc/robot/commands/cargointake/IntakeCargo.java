package frc.robot.commands.cargointake;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class IntakeCargo extends Command {


  public IntakeCargo() {
    requires(Robot.cargoIntake);
  }

  @Override
  protected void initialize() {
    Robot.cargoIntake.setIntakePivot(90); //arbitrary number
    Robot.cargoIntake.setRoller(.70);
  }
  
  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return Robot.cargoIntake.isCargoCollected();
  }

  @Override
  protected void end() {
    Robot.cargoIntake.setRoller(0.0);
    Robot.cargoIntake.setIntakePivot(0.0);
  }

  @Override
  protected void interrupted() {
  }
}
