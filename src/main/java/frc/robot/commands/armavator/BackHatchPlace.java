package frc.robot.commands.armavator;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.arm.SetArmAngle;
import frc.robot.commands.elevator.GoToElevatorHeight;
import frc.robot.subsystems.Arm.ArmSetpoint;
import frc.robot.subsystems.Arm.ArmSide;
import frc.robot.subsystems.Elevator.ElevatorHeight;

public class BackHatchPlace extends CommandGroup {

  public BackHatchPlace() {
    addSequential(new GoToElevatorHeight(ElevatorHeight.QUICKHATCHFLIP));
    addSequential(new SetArmAngle(ArmSetpoint.HATCH, ArmSide.BACK));
    addSequential(new SetArmSide(ArmSide.BACK));
    addSequential(new GoToElevatorHeight(ElevatorHeight.LEVEL1HATCH));
  }
}
