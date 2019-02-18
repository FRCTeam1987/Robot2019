package frc.robot.commands.armavator;

import edu.wpi.first.wpilibj.command.ConditionalCommand;
import frc.robot.Robot;
import frc.robot.commands.arm.SetArmAngle;
import frc.robot.subsystems.Arm.ArmAngle;
import frc.robot.subsystems.Arm.ArmSide;

public class ShouldRollArm extends ConditionalCommand {
  
  private ArmSide m_desiredArmSide;

  public ShouldRollArm(final ArmSide desiredArmSide) {
    super(new SetArmAngle(desiredArmSide == ArmSide.FRONT ? ArmAngle.HATCH : ArmAngle.HATCH));   
    m_desiredArmSide = desiredArmSide;
  }

  @Override
  public boolean condition() {
    return Robot.arm.getArmSide() != m_desiredArmSide;
  }

  
}
