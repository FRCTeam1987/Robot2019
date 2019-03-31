package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
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
    addSequential(new GoToElevatorHeight(ElevatorHeight.FLIP));
    addSequential(new SetArmAngle(ArmSetpoint.HATCH, ArmSide.FRONT));
    addSequential(new SetArmSide(ArmSide.FRONT));
  }
}
