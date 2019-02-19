package frc.robot.commands.claw;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.arm.SetArmAngle;
import frc.robot.commands.elevator.SetElevatorAbsolute;
import frc.robot.subsystems.Arm.ArmSetpoint;
import frc.robot.subsystems.Arm.ArmSide;

public class CollectHatch extends CommandGroup {

  private final ArmSide m_desiredArmSide;
  
  public CollectHatch(final ArmSide desiredArmSide) {
    m_desiredArmSide = desiredArmSide;
    addParallel(new SetElevatorAbsolute(15));
    addSequential(new SetArmAngle(ArmSetpoint.HATCH, m_desiredArmSide));
  }
}
