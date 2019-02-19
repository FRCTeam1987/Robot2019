package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.subsystems.Elevator.ElevatorHeight;


public class SetElevatorHeight extends InstantCommand {

  private ElevatorHeight m_elevatorHeight;

  public SetElevatorHeight(final ElevatorHeight newElevatorHeight) {
    super();
    m_elevatorHeight = newElevatorHeight;
  }

  @Override
  protected void initialize() {
    Robot.elevator.setElevatorHeight(m_elevatorHeight);
  }

}
