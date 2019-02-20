package frc.robot.commands.armavator;

import edu.wpi.first.wpilibj.command.ConditionalCommand;
import frc.robot.Robot;
import frc.robot.commands.arm.SetArmAngle;
import frc.robot.subsystems.Arm.ArmSetpoint;
import frc.robot.subsystems.Arm.ArmSide;
import frc.robot.subsystems.Elevator.ElevatorHeight;

public class ShouldRollArm extends ConditionalCommand {
  
  private ArmSide m_desiredArmSide;

  public ShouldRollArm(final ArmSide desiredArmSide) {
    super(new SetArmAngle(desiredArmSide == ArmSide.FRONT ? ArmSetpoint.HATCH : ArmSetpoint.HATCH, desiredArmSide));   
    m_desiredArmSide = desiredArmSide;
  }

  public ShouldRollArm() {
    super(new SetArmAngle());
    
  }

  @Override
  public boolean condition() {
    m_desiredArmSide = Robot.arm.getArmSide();
    return Robot.arm.getArmSide() != m_desiredArmSide;
  }
}
