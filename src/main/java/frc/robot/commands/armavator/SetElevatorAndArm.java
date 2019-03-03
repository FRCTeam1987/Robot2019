package frc.robot.commands.armavator;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.arm.SetArmAngle;
import frc.robot.commands.elevator.GoToElevatorHeight;
import frc.robot.subsystems.Arm.ArmSetpoint;
import frc.robot.subsystems.Arm.ArmSide;
import frc.robot.subsystems.Elevator.ElevatorHeight;
import frc.robot.Robot;

public class SetElevatorAndArm extends CommandGroup {

  public SetElevatorAndArm(final ArmSide desiredArmSide, final ElevatorHeight elevatorHeight, final ArmSetpoint armAngle) {
    addSequential(new ShouldGoToFlipHeight(desiredArmSide));
    addSequential(new ShouldRollArm(desiredArmSide));
    addSequential(new SetArmSide(desiredArmSide));
    addSequential(new SetArmAngle(armAngle, desiredArmSide));
    addSequential(new GoToElevatorHeight(elevatorHeight));
  }

  public SetElevatorAndArm() {
    addSequential(new ShouldGoToFlipHeight());
    addSequential(new ShouldRollArm());
    addSequential(new SetArmSide());
    addParallel(new SetArmAngle());
    addSequential(new GoToElevatorHeight());
  }
}
