package frc.robot.commands.cargointake;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;
import frc.robot.commands.armavator.SetElevatorAndArm;
import frc.robot.subsystems.Arm.ArmSetpoint;
import frc.robot.subsystems.Arm.ArmSide;
import frc.robot.subsystems.Elevator.ElevatorHeight;

public class CollectCargo extends CommandGroup {

  public CollectCargo() {
    addSequential(new SetElevatorAndArm(ArmSide.FRONT, ElevatorHeight.LEVEL2HATCH, ArmSetpoint.HATCH));
    addSequential(new SetIntakeAngle(RobotMap.cargoIntakeAngle));
    addSequential(new SetElevatorAndArm(ArmSide.FRONT, ElevatorHeight.CARGOGROUNDCOLLECT, ArmSetpoint.CARGOCOLLECTFLOOR));
    addSequential(new IntakeCargo());
    addSequential(new SetElevatorAndArm(ArmSide.FRONT, ElevatorHeight.CARGOSHIP, ArmSetpoint.CARGOSHIP));
    addSequential(new SetIntakeAngle(RobotMap.cargoIntakeHomeAngle));
  }
}
