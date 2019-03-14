package frc.robot.commands.armavator;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.subsystems.Arm.ArmSetpoint;
import frc.robot.subsystems.Arm.ArmSide;
import frc.robot.subsystems.Elevator.ElevatorHeight;

public class SetRobotState extends InstantCommand {
 
  private ArmSide m_armSide;
  private ArmSetpoint m_armSetpoint;
  private ElevatorHeight m_elevatorHeight;
  
  public SetRobotState(final ElevatorHeight desiredElevatorHeight, final ArmSetpoint desiredArmSetpoint) {
    super();
    m_armSetpoint = desiredArmSetpoint;
    m_elevatorHeight = desiredElevatorHeight;
  }

  @Override
  protected void initialize() {
    m_armSide = Robot.arm.getArmSideState();
    Robot.arm.setArmSide(m_armSide);
    Robot.arm.setArmSetpoint(m_armSetpoint);
    Robot.elevator.setElevatorHeight(m_elevatorHeight);
  }
}
