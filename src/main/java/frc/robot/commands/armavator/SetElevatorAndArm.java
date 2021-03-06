package frc.robot.commands.armavator;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.arm.SetArmAngle;
import frc.robot.commands.elevator.GoToElevatorHeight;
import frc.robot.subsystems.Arm.ArmSetpoint;
import frc.robot.subsystems.Elevator.ElevatorHeight;

public class SetElevatorAndArm extends CommandGroup {

  public SetElevatorAndArm(final ElevatorHeight elevatorHeight, final ArmSetpoint armAngle) {
    addSequential(new SetRobotState(elevatorHeight, armAngle));
    // addSequential(new ShouldGoToFlipHeight());
    // addSequential(new ShouldRollArm());
    addSequential(new SetArmSide());
    addParallel(new SetArmAngle());
    addParallel(new GoToElevatorHeight());
  }

  public SetElevatorAndArm() {
    // addSequential(new ShouldGoToCargoFlipHeight());
    // addSequential(new ShouldGoToHatchFlipHeight());
    // addSequential(new ShouldRollArm());
    addParallel(new SetArmAngle());
    addParallel(new GoToElevatorHeight());
  }
}
