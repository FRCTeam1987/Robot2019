package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ElevatorManual extends Command {

private final double m_percent;

  public ElevatorManual(final double percent) {
    requires(Robot.elevator);
    m_percent = percent; 
  }

  @Override
  protected void initialize() {
    Robot.elevator.setElevatorPercent(m_percent);
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
