package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class ToggleShifter extends InstantCommand {
  /**
   * Add your docs here.
   */
  public ToggleShifter() {
    super();
    requires(Robot.drive);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    if(Robot.drive.isHighGear() == true) {
      Robot.drive.setLowGear();
    } else {
      Robot.drive.setHighGear();
    }
  }

}
