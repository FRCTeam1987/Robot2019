package frc.team1987.commands.cargointake;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team1987.commands.arm.SetArmPosition;
import frc.team1987.commands.claw.ClawCollectCargo;
import frc.team1987.commands.elevator.SetElevatorAbsolute;

public class CollectCargo extends CommandGroup {
//all numbers are arbitrary
  public CollectCargo() {
    addParallel(new SetElevatorAbsolute(0)); 
    addSequential(new SetArmPosition(90));
    addSequential(new IntakeCargo());
    addSequential(new ClawCollectCargo());
  }
}
