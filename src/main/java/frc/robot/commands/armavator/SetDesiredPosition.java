package frc.robot.commands.armavator;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.commands.arm.SetArmPosition;
import frc.robot.commands.elevator.SetElevatorHeight;
import frc.robot.subsystems.Arm.ArmSetpoint;
import frc.robot.subsystems.Arm.ArmSide;
import frc.robot.subsystems.Elevator.ElevatorHeight;

public class SetDesiredPosition extends CommandGroup {

  private ElevatorHeight m_elevatorHeight;
  private ArmSetpoint m_armAngle;
  private ArmSide m_armSide;

  public SetDesiredPosition(final ElevatorHeight elevatorHeight, final ArmSetpoint armAngle) {
    m_elevatorHeight = elevatorHeight;
    m_armAngle = armAngle;
    m_armSide = Robot.arm.getArmSideButton();

    addParallel(new SetElevatorHeight(m_elevatorHeight));
    addParallel(new SetArmPosition(m_armAngle));
    addParallel(new SetArmSide(m_armSide));
  }
}
