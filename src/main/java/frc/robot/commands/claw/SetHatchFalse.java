package frc.robot.commands.claw;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class SetHatchFalse extends InstantCommand {
  public SetHatchFalse() {

  }
  
  @Override
  protected void initialize() {
    Robot.claw.setHatchCollected(false);
  }
}
