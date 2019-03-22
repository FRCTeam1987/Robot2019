package frc.robot.commands.armavator;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.elevator.GoToElevatorHeight;
import frc.robot.subsystems.Elevator.ElevatorHeight;

public class Level2HatchPlace extends CommandGroup {

  public Level2HatchPlace() {
    // addSequential(new GoToElevatorHeight(ElevatorHeight.QUICKHATCHFLIP));
    // addSequential(new SetArmAngle(ArmSetpoint.HATCH, ArmSide.FRONT));
    // addSequential(new SetArmSide(ArmSide.BACK));
    addSequential(new GoToElevatorHeight(ElevatorHeight.HABLEVEL2));
  }
}
