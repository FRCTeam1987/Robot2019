package frc.robot.commands.claw;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.arm.SetArmAngle;
import frc.robot.commands.elevator.SetElevatorAbsolute;

public class CollectHatch extends CommandGroup {

  public CollectHatch() {
    addParallel(new SetElevatorAbsolute(15));
    addSequential(new SetArmAngle(90));
  }
}
