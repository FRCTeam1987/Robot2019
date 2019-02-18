package frc.robot.commands.claw;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.arm.SetArmAngle;
import frc.robot.commands.elevator.SetElevatorAbsolute;
import frc.robot.subsystems.Arm.ArmAngle;

public class CollectHatch extends CommandGroup {

  public CollectHatch() {
    addParallel(new SetElevatorAbsolute(15));
    addSequential(new SetArmAngle(ArmAngle.HATCH));
  }
}
