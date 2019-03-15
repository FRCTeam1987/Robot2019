package frc.robot.commands.claw;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.RobotMap;
import frc.robot.commands.elevator.SetElevatorAbsolute;

public class SetHatchTrue extends CommandGroup {

  public SetHatchTrue() {
    addSequential(new SetHatchState(true));
    addSequential(new SetElevatorAbsolute(RobotMap.elevatorCollectHatchHeight));
    addSequential(new WaitCommand(1));
    addSequential(new SetElevatorAbsolute(RobotMap.elevatorLevel1HatchHeight));
  }
}
