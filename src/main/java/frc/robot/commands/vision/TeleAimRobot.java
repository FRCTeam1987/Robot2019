package frc.robot.commands.vision;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.SetRumble;

public class TeleAimRobot extends CommandGroup {
  /**
   * Add your docs here.
   */
  public TeleAimRobot() {
    addSequential(new AutoAimbot());
    addSequential(new SetRumble(1));
  }
}
