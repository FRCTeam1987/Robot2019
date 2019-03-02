package frc.robot.commands.armavator;

import edu.wpi.first.wpilibj.command.ConditionalCommand;
import frc.robot.Robot;
import frc.robot.commands.elevator.GoToElevatorHeight;
import frc.robot.subsystems.Arm.ArmSide;
import frc.robot.subsystems.Elevator.ElevatorHeight;

public class ShouldGoToFlipHeight extends ConditionalCommand {
  
  private ArmSide m_desiredArmSide;
  private boolean m_isDefault;
  
  public ShouldGoToFlipHeight(final ArmSide desiredArmSide) { 
    super(new GoToElevatorHeight(ElevatorHeight.FLIP));
    m_desiredArmSide = desiredArmSide;
    m_isDefault = false;
  }

  public ShouldGoToFlipHeight() {
    super(new GoToElevatorHeight(ElevatorHeight.FLIP));
    m_isDefault = true;
  }

  @Override
  protected boolean condition() {
    if (m_isDefault) {
      m_desiredArmSide = Robot.arm.getArmSideState();
    }
    
    return Robot.arm.getArmSideState() != m_desiredArmSide;
  }
}
