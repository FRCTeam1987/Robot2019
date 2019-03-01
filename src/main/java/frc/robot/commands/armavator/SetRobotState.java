package frc.robot.commands.armavator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Arm.ArmSetpoint;
import frc.robot.subsystems.Arm.ArmSide;
import frc.robot.subsystems.Elevator.ElevatorHeight;

public class SetRobotState extends Command {
  
  private ArmSide m_armSide;
  private ArmSetpoint m_armSetpoint;
  private ElevatorHeight m_elevatorHeight;

  public SetRobotState() {
    m_armSide = ArmSide.FRONT;
    m_armSetpoint = ArmSetpoint.HOME;
    m_elevatorHeight = ElevatorHeight.HOME;
  }

  @Override
  protected void initialize() { 
   Robot.arm.setArmSide(m_armSide);
   Robot.arm.setArmSetpoint(m_armSetpoint);
   Robot.elevator.setElevatorHeight(m_elevatorHeight);
  
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
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
