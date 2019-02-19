package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.Elevator.ElevatorHeight;

public class GoToElevatorHeight extends Command {
  
  private final ElevatorHeight m_elevatorHeight;

  private double m_targetInches;

  public GoToElevatorHeight(final ElevatorHeight elevatorHeight) {
    requires(Robot.elevator);
    m_elevatorHeight = elevatorHeight;
    m_targetInches = 0;
    setTimeout(1.5);
  }

  public GoToElevatorHeight() {
    m_elevatorHeight = Robot.elevator.getElevatorHeight();
    m_targetInches = 0;
    setTimeout(1.5);
  }

  @Override
  protected void initialize() {
    switch(m_elevatorHeight) {
      case CARGOGROUNDCOLLECT:
        m_targetInches = RobotMap.elevatorGroundCollectHeight;
        Robot.elevator.setElevatorAbsolute(m_targetInches);
        break;
      case CARGOSHIP:
        m_targetInches = RobotMap.elevatorCargoShipHeight;
        Robot.elevator.setElevatorAbsolute(m_targetInches);
        break;
      case LEVEL1HATCH:
        m_targetInches = RobotMap.elevatorLevel1HatchHeight;
        Robot.elevator.setElevatorAbsolute(m_targetInches);
        break;
      case LEVEL1CARGOROCKET:
        m_targetInches = RobotMap.elevatorLevel1CargoHeight;
        Robot.elevator.setElevatorAbsolute(m_targetInches);
        break;
      case LEVEL2HATCH:
        m_targetInches = RobotMap.elevatorLevel2HatchHeight;
        Robot.elevator.setElevatorAbsolute(m_targetInches);
        break;
      case LEVEL2CARGOROCKET:
        m_targetInches = RobotMap.elevatorLevel2CargoHeight;
        Robot.elevator.setElevatorAbsolute(m_targetInches);
        break;
      case LOADINGSTATIONCARGO:
        m_targetInches = RobotMap.elevatorCargoLoadingStationHeight;
        Robot.elevator.setElevatorAbsolute(m_targetInches);
        break;
      case FLIP:
        m_targetInches = RobotMap.elevatorFlipHeight;
        Robot.elevator.setElevatorAbsolute(m_targetInches);
        break;
      case HOME:
        m_targetInches = RobotMap.elevatorHomeHeight;
        Robot.elevator.setElevatorAbsolute(m_targetInches);
        break;
    }
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return Robot.elevator.isWithinTolerance(m_targetInches) || isTimedOut();
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
