package frc.robot.commands.armavator;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.arm.SetArmAngle;
import frc.robot.commands.elevator.GoToElevatorHeight;
import frc.robot.subsystems.Arm.ArmSetpoint;
import frc.robot.subsystems.Arm.ArmSide;
import frc.robot.subsystems.Elevator.ElevatorHeight;
import frc.robot.Robot;

public class SetElevatorAndArm extends CommandGroup {

  private final ArmSide m_desiredArmSide;
  private final ElevatorHeight m_elevatorHeight;
  private final ArmSetpoint m_armAngle;

  public SetElevatorAndArm(final ArmSide desiredArmSide, final ElevatorHeight elevatorHeight, final ArmSetpoint armAngle) {
    m_desiredArmSide = desiredArmSide;
    m_elevatorHeight = elevatorHeight;
    m_armAngle = armAngle;

    addSequential(new ShouldGoToFlipHeight(m_desiredArmSide));
    addSequential(new ShouldRollArm(m_desiredArmSide));
    addSequential(new SetArmSide(m_desiredArmSide));
    addSequential(new SetArmAngle(m_armAngle, m_desiredArmSide));
    addSequential(new GoToElevatorHeight(m_elevatorHeight));
  }

  public SetElevatorAndArm() {
    m_desiredArmSide = Robot.arm.getArmSideButton();
    m_elevatorHeight = Robot.elevator.getElevatorHeight();
    m_armAngle = Robot.arm.getArmSetpoint();

    addSequential(new ShouldGoToFlipHeight(m_desiredArmSide));
    addSequential(new ShouldRollArm(m_desiredArmSide));
    addSequential(new SetArmSide(m_desiredArmSide));
    addSequential(new SetArmAngle(m_armAngle, m_desiredArmSide));
    addSequential(new GoToElevatorHeight(m_elevatorHeight));
  }
}
