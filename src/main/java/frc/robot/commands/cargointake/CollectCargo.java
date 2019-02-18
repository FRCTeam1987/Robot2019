package frc.robot.commands.cargointake;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.arm.SetArmAngle;
import frc.robot.commands.elevator.SetElevatorAbsolute;
import frc.robot.subsystems.Arm.ArmAngle;

public class CollectCargo extends CommandGroup {

  public CollectCargo() {
    addParallel(new SetElevatorAbsolute(0)); //change
    addSequential(new SetArmAngle(ArmAngle.HATCH)); //change
    addSequential(new IntakeCargo());
  }
}
