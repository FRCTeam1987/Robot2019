package frc.robot.commands.cargointake;

import edu.wpi.first.wpilibj.command.ConditionalCommand;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.arm.SetArmAngle;
import frc.robot.commands.armavator.SetElevatorAndArm;
import frc.robot.subsystems.Arm.ArmSetpoint;
import frc.robot.subsystems.Arm.ArmSide;
import frc.robot.subsystems.Elevator.ElevatorHeight;

public class IsArmInTheWay extends ConditionalCommand {

  public IsArmInTheWay() {
    super(new SetArmAndElevatorForCargoCollect());
  }

  @Override
  public boolean condition() {
    if (Robot.arm.getArmSide() == ArmSide.BACK) 
        return false;
    else 
        return Robot.elevator.getInches() < RobotMap.elevatorFlipHeight;
  }
}
