package frc.robot.commands.armavator;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.arm.SetArmAngle;
import frc.robot.commands.elevator.GoToElevatorHeight;
import frc.robot.subsystems.Arm.ArmSetpoint;
import frc.robot.subsystems.Arm.ArmSide;
import frc.robot.subsystems.Elevator.ElevatorHeight;

public class FrontHatchPlace extends CommandGroup {

  public FrontHatchPlace() {
    addSequential(new GoToElevatorHeight(ElevatorHeight.FLIP));
    addSequential(new SetArmAngle(ArmSetpoint.HATCH, ArmSide.FRONT));
    addSequential(new SetArmSide(ArmSide.FRONT));
    addSequential(new GoToElevatorHeight(ElevatorHeight.LEVEL1HATCH));
  }
}
