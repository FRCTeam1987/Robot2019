package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.Elevator.ElevatorHeight;

public class GoToElevatorHeight extends Command {
  
  private final ElevatorHeight m_elevatorHeight;

  public GoToElevatorHeight(final ElevatorHeight elevatorHeight) {
    requires(Robot.elevator);
    m_elevatorHeight = elevatorHeight;
  }

  @Override
  protected void initialize() {
    switch(m_elevatorHeight) {
      case CARGOGROUNDCOLLECT:
        Robot.elevator.setElevatorAbsolute(RobotMap.elevatorGroundCollectHeight);
        break;
      case LEVEL1HATCH:
        Robot.elevator.setElevatorAbsolute(RobotMap.elevatorLevel1HatchHeight);
        break;
      case LEVEL1CARGO:
        Robot.elevator.setElevatorAbsolute(RobotMap.elevatorLevel1CargoHeight);
        break;
      case LEVEL2HATCH:
        Robot.elevator.setElevatorAbsolute(RobotMap.elevatorLevel2HatchHeight);
        break;
      case LEVEL2CARGO:
        Robot.elevator.setElevatorAbsolute(RobotMap.elevatorLevel2CargoHeight);
        break;
      case LOADINGSTATIONCARGO:
        Robot.elevator.setElevatorAbsolute(RobotMap.elevatorCargoLoadingStationHeight);
        break;
      case FLIP:
        Robot.elevator.setElevatorAbsolute(RobotMap.elevatorFlipHeight);
        break;
    }
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
  }
}
