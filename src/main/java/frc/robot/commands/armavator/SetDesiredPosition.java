package frc.robot.commands.armavator;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.arm.SetArmPositionState;
import frc.robot.commands.elevator.SetElevatorHeightState;
import frc.robot.subsystems.Arm.ArmSetpoint;
import frc.robot.subsystems.Elevator.ElevatorHeight;

public class SetDesiredPosition extends CommandGroup {

  private ElevatorHeight m_elevatorHeight;
  private ArmSetpoint m_armAngle;

  public SetDesiredPosition(final ElevatorHeight elevatorHeight, final ArmSetpoint armAngle) {
    m_elevatorHeight = elevatorHeight;
    m_armAngle = armAngle;

    addParallel(new SetElevatorHeightState(m_elevatorHeight));
    addParallel(new SetArmPositionState(m_armAngle));
  }
}
