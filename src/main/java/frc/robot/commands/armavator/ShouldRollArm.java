package frc.robot.commands.armavator;

import edu.wpi.first.wpilibj.command.ConditionalCommand;
import frc.robot.Robot;
import frc.robot.commands.arm.SetArmAngle;
import frc.robot.subsystems.Arm.ArmSetpoint;
import frc.robot.subsystems.Arm.ArmSide;

public class ShouldRollArm extends ConditionalCommand {
  
  private ArmSide m_desiredArmSide;
  private boolean m_isDefault;

  public ShouldRollArm(final ArmSide desiredArmSide) {
    super(new SetArmAngle(desiredArmSide == ArmSide.FRONT ? ArmSetpoint.HATCH : ArmSetpoint.HATCH, desiredArmSide));   
    m_desiredArmSide = desiredArmSide;
    m_isDefault = false;
  }

  public ShouldRollArm() {
    super(new SetArmAngle());
    m_isDefault = true;
  }

  @Override
  public boolean condition() {
    if (m_isDefault) {
      m_desiredArmSide = Robot.arm.getArmSideState();
    }
    
    return Robot.arm.getArmSideState() != m_desiredArmSide;
  }
}
