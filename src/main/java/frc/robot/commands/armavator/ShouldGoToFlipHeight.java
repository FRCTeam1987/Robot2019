package frc.robot.commands.armavator;

import edu.wpi.first.wpilibj.command.ConditionalCommand;
import frc.robot.Robot;
import frc.robot.commands.elevator.GoToElevatorHeight;
import frc.robot.subsystems.Arm.ArmSide;
import frc.robot.subsystems.Elevator.ElevatorHeight;

public class ShouldGoToFlipHeight extends ConditionalCommand {
  
  private ArmSide m_desiredArmSide;
  
  public ShouldGoToFlipHeight(final ArmSide desiredArmSide) { 
    super(new GoToElevatorHeight(ElevatorHeight.FLIP));
    m_desiredArmSide = desiredArmSide;
  }

  public ShouldGoToFlipHeight() {
    super(new GoToElevatorHeight(ElevatorHeight.FLIP));
    m_desiredArmSide = ArmSide.FRONT;
  }

  @Override
  protected boolean condition() {
    m_desiredArmSide = Robot.arm.getArmSide();
    return Robot.arm.getArmSide() != m_desiredArmSide;
  }
}
