package frc.team1987.commands.cargointake;

import edu.wpi.first.wpilibj.command.Command;
import frc.team1987.Robot;

public class IntakeCargo extends Command {
  public IntakeCargo() {
    requires(Robot.cargoIntake);
  }

  @Override
  protected void initialize() {
    Robot.cargoIntake.setIntakePivot(90); //arbitrary number
    Robot.cargoIntake.setRoller(0.7);
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
    Robot.cargoIntake.setRoller(0);
    Robot.cargoIntake.setIntakePivot(0); //arbitraty number 
  }

  @Override
  protected void interrupted() {
  }
}
