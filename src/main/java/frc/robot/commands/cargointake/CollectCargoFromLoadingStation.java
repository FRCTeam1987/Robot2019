package frc.robot.commands.cargointake;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.SetRumble;
import frc.robot.commands.armavator.SetElevatorAndArm;
import frc.robot.commands.claw.ClawIntakeCargo;
import frc.robot.commands.claw.CollectHatch;
import frc.robot.subsystems.Arm.ArmSetpoint;
import frc.robot.subsystems.Elevator.ElevatorHeight;

public class CollectCargoFromLoadingStation extends CommandGroup {

  public CollectCargoFromLoadingStation() {
    addSequential(new CollectHatch());
    addParallel(new SetElevatorAndArm());
    addParallel(new CollectHatch());
    addSequential(new ClawIntakeCargo(1));
    addParallel(new SetRumble(1));
    addParallel(new SetElevatorAndArm(ElevatorHeight.CARGOSHIP, ArmSetpoint.CARGOSHIP));
  }
}
