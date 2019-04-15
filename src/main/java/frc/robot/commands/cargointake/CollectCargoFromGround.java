package frc.robot.commands.cargointake;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;
import frc.robot.commands.SetRumble;
import frc.robot.commands.arm.SetArmAngle;
import frc.robot.commands.claw.CollectHatch;
import frc.robot.commands.elevator.GoToElevatorHeight;
import frc.robot.subsystems.Arm.ArmSetpoint;
import frc.robot.subsystems.Arm.ArmSide;
import frc.robot.subsystems.Elevator.ElevatorHeight;

public class CollectCargoFromGround extends CommandGroup {

  public CollectCargoFromGround() {
    addSequential(new CollectHatch());
    addParallel(new GoToElevatorHeight(ElevatorHeight.PREPFLOORCARGO));
    addSequential(new DelaySetIntakeAngle(0.5, RobotMap.cargoIntakeAngle));
    addParallel(new GoToElevatorHeight(ElevatorHeight.CARGOGROUNDCOLLECT));
    addParallel(new SetArmAngle(ArmSetpoint.CARGOCOLLECTFLOOR, ArmSide.FRONT));
    addSequential(new IntakeCargo());
    addSequential(new GoToElevatorHeight(ElevatorHeight.CARGOSHIP));
    addParallel(new SetArmAngle(ArmSetpoint.CARGOSHIP, ArmSide.FRONT));
    addParallel(new SetIntakeAngle(RobotMap.cargoIntakeHomeAngle));
  }
}
