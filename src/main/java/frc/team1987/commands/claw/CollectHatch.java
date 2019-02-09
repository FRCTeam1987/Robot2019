package frc.team1987.commands.claw;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team1987.commands.arm.SetArmPosition;
import frc.team1987.commands.elevator.SetElevatorAbsolute;

public class CollectHatch extends CommandGroup {
//all arbitrary numbers
  public CollectHatch() {
    addParallel(new SetElevatorAbsolute(15));
    addSequential(new SetArmPosition(90));
  }
}
