package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.commands.arm.SetArmAngle;
import frc.robot.commands.armavator.SetArmSide;
import frc.robot.commands.claw.CollectHatch;
import frc.robot.commands.elevator.GoToElevatorHeight;
import frc.robot.subsystems.Arm.ArmSetpoint;
import frc.robot.subsystems.Arm.ArmSide;
import frc.robot.subsystems.Elevator.ElevatorHeight;

public class SandstormHatch extends CommandGroup {

  public SandstormHatch() {
    addSequential(new CollectHatch());
    // addSequential(new WaitCommand(1));
    addSequential(new GoToElevatorHeight(ElevatorHeight.FLIP));
    addParallel(new SetArmAngle(ArmSetpoint.HATCH, ArmSide.FRONT));
    addParallel(new GoToElevatorHeight(ElevatorHeight.LEVEL1HATCH));
    addSequential(new SetArmSide(ArmSide.FRONT));

  }
}
