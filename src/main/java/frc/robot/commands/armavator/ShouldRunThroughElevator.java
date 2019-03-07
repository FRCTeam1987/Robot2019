package frc.robot.commands.armavator;

import edu.wpi.first.wpilibj.command.ConditionalCommand;
import frc.robot.Robot;
import frc.robot.commands.arm.SetArmAngle;

public class ShouldRunThroughElevator extends ConditionalCommand {
  
  public ShouldRunThroughElevator() {
    super(new SetArmAngle());

  }

  @Override
  protected boolean condition() { 
    return !(Robot.claw.isCargoCollected() || Robot.claw.isHatchCollected());
  }
}
