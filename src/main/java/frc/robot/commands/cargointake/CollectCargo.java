package frc.robot.commands.cargointake;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.arm.SetArmAngle;
import frc.robot.commands.claw.ClawIntakeCargo;
import frc.robot.commands.elevator.SetElevatorAbsolute;

public class CollectCargo extends CommandGroup {

  public CollectCargo() {
    addParallel(new SetElevatorAbsolute(0));
    addSequential(new SetArmAngle(90));
    addSequential(new IntakeCargo());
    addSequential(new ClawIntakeCargo());
  }
}
