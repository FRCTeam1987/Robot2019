/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.vision;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.commands.SetRumble;

public class TeleAimRobot extends CommandGroup {
  /**
   * Add your docs here.
   */
  public TeleAimRobot() {
    addSequential(new AimRobot());
    addSequential(new SetRumble(0.8));
    addSequential(new WaitCommand(1));
    addSequential(new SetRumble(0));
  }
}
