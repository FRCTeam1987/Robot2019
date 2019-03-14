package frc.robot.commands.cargointake;

import edu.wpi.first.wpilibj.command.ConditionalCommand;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.Arm.ArmSide;

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
