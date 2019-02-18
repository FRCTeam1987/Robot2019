package frc.robot.commands.armavator;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.arm.SetArmAngle;
import frc.robot.commands.elevator.GoToElevatorHeight;
import frc.robot.commands.elevator.SetElevatorAbsolute;
import frc.robot.subsystems.Arm.ArmAngle;
import frc.robot.subsystems.Arm.ArmSide;
import frc.robot.subsystems.Elevator.ElevatorHeight;

public class SetElevatorAndArm extends CommandGroup {

  private final ArmSide m_desiredArmSide;
  private final ElevatorHeight m_elevatorHeight;
  private final ArmAngle m_armAngle;

  public SetElevatorAndArm(final ArmSide desiredArmSide, final ElevatorHeight elevatorHeight, final ArmAngle armAngle) {
    m_desiredArmSide = desiredArmSide;
    m_elevatorHeight = elevatorHeight;
    m_armAngle = armAngle;

    addSequential(new ShouldGoToFlipHeight(m_desiredArmSide));
    addSequential(new ShouldRollArm(m_desiredArmSide));
    addSequential(new SetArmSide(m_desiredArmSide));
    addSequential(new SetArmAngle(m_armAngle));
    addSequential(new GoToElevatorHeight(elevatorHeight));
  }
}
