package frc.robot.commands.claw;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;


public class ToggleHatchManipulator extends InstantCommand {

  public ToggleHatchManipulator() {
    super();
    requires(Robot.claw);
  }

  @Override
  protected void initialize() {
    if (Robot.claw.isInReleaseHatchPosition()) {
      Robot.claw.collectHatch();
    } else {
      Robot.claw.releaseHatch();
    }
  }

}
