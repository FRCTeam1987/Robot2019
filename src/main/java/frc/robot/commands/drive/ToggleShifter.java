/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

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
