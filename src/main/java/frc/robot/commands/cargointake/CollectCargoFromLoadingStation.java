package frc.robot.commands.cargointake;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.SetRumble;
import frc.robot.commands.armavator.SetElevatorAndArm;
import frc.robot.commands.claw.ClawIntakeCargo;
import frc.robot.subsystems.Arm.ArmSetpoint;
import frc.robot.subsystems.Elevator.ElevatorHeight;

public class CollectCargoFromLoadingStation extends CommandGroup {

  public CollectCargoFromLoadingStation() {
    addSequential(new SetElevatorAndArm());
    addSequential(new ClawIntakeCargo(0.9));
    addSequential(new SetRumble(1));
    addSequential(new SetElevatorAndArm(ElevatorHeight.CARGOSHIP, ArmSetpoint.CARGOSHIP));
  }
}
