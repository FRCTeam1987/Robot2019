package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.Elevator.ElevatorHeight;

public class GoToElevatorHeight extends Command {
  
  private ElevatorHeight m_elevatorHeight;
  private double m_targetInches;
  private boolean m_isDefault;

  public GoToElevatorHeight(final ElevatorHeight elevatorHeight) {
    requires(Robot.elevator);
    m_elevatorHeight = elevatorHeight;
    m_targetInches = 0;
    setTimeout(1.5);
    m_isDefault = false;
  }

  public GoToElevatorHeight() {
    m_elevatorHeight = ElevatorHeight.HOME;
    m_targetInches = 0;
    m_isDefault = true;
    setTimeout(1.5);
  }

  @Override
  protected void initialize() {
    if (m_isDefault) {
      m_elevatorHeight = Robot.elevator.getElevatorHeightState();
    }
    
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
      case QUICKHATCHFLIP:
        m_targetInches = RobotMap.elevatorQuickHatchFlipHeight;
        Robot.elevator.setElevatorAbsolute(m_targetInches);
        break;
      case QUICKCARGOFLIP:
        m_targetInches = RobotMap.elevatorQuickCargoFlipHeight;
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
  }

  @Override
  protected void interrupted() {
    end();
  }
}
