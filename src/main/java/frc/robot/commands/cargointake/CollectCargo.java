package frc.robot.commands.cargointake;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;
import frc.robot.commands.arm.SetArmAngle;
import frc.robot.commands.armavator.SetElevatorAndArm;
import frc.robot.commands.elevator.GoToElevatorHeight;
import frc.robot.subsystems.Arm.ArmSetpoint;
import frc.robot.subsystems.Arm.ArmSide;
import frc.robot.subsystems.Elevator.ElevatorHeight;

public class CollectCargo extends CommandGroup {

  public CollectCargo() {
    addSequential(new SetElevatorAndArm(ArmSide.FRONT, ElevatorHeight.QUICKCARGOFLIP, ArmSetpoint.CARGOCOLLECTFLOOR));
    addSequential(new SetIntakeAngle(RobotMap.cargoIntakeAngle));
    addSequential(new GoToElevatorHeight(ElevatorHeight.CARGOGROUNDCOLLECT));
    addSequential(new IntakeCargo());
    addSequential(new SetElevatorAndArm(ArmSide.FRONT, ElevatorHeight.CARGOSHIP, ArmSetpoint.CARGOSHIP));
    addSequential(new SetIntakeAngle(RobotMap.cargoIntakeHomeAngle));
  }
}
