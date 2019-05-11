package frc.robot.commands.armavator;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.subsystems.Arm.ArmSetpoint;
import frc.robot.subsystems.Arm.ArmSide;
import frc.robot.subsystems.Elevator.ElevatorHeight;

public class SmartSetRobotState extends InstantCommand {

  // private ArmSide m_armSide;
  // private ArmSetpoint m_armSetpoint;
  private ElevatorHeight m_elevatorHeight;
  
  public SmartSetRobotState(final ElevatorHeight desiredElevatorHeight) {
    super();
    m_elevatorHeight = desiredElevatorHeight;
  }

  @Override
  protected void initialize() {
    switch(m_elevatorHeight) {
      case LEVEL1:
        if (Robot.claw.isCargoCollected()) {
          Robot.arm.setArmSide(ArmSide.FRONT);
          Robot.arm.setArmSetpoint(ArmSetpoint.CARGOROCKETLVL1);
          Robot.elevator.setElevatorHeight(ElevatorHeight.LEVEL1CARGOROCKET);
        }
        else {
          Robot.arm.setArmSide(ArmSide.FRONT);
          Robot.arm.setArmSetpoint(ArmSetpoint.HATCH);
          Robot.elevator.setElevatorHeight(ElevatorHeight.LEVEL1HATCH);
        }
        break;
      case LEVEL2:
        if (Robot.claw.isCargoCollected()) {
          Robot.arm.setArmSide(ArmSide.FRONT);
          Robot.arm.setArmSetpoint(ArmSetpoint.CARGOROCKETLVL2);
          Robot.elevator.setElevatorHeight(ElevatorHeight.LEVEL2CARGOROCKET);
        }
        else {
          Robot.arm.setArmSide(ArmSide.FRONT);
          Robot.arm.setArmSetpoint(ArmSetpoint.HATCH);
          Robot.elevator.setElevatorHeight(ElevatorHeight.LEVEL2HATCH);
        }
        break; 
      case CARGOSHIP:
        Robot.arm.setArmSide(ArmSide.FRONT);
        Robot.arm.setArmSetpoint(ArmSetpoint.CARGOSHIP);
        Robot.elevator.setElevatorHeight(ElevatorHeight.CARGOSHIP);
        break;
      default:
        Robot.arm.setArmSide(ArmSide.FRONT);
        Robot.arm.setArmSetpoint(ArmSetpoint.HATCH);
        Robot.elevator.setElevatorHeight(ElevatorHeight.LEVEL1HATCH);
        break;
    }
  }
}
