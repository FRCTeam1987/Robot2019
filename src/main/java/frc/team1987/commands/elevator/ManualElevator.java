package frc.team1987.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.team1987.Robot;

public class ManualElevator extends Command {

  private final double value;

  public ManualElevator(final double value) {
    requires(Robot.elevator);
    this.value = value;
  }

  @Override
  protected void initialize() {
    Robot.elevator.setElevatorPercent(value);
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
    Robot.elevator.setElevatorPercent(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
