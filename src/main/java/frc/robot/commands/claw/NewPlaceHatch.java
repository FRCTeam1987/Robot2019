/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.claw;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class NewPlaceHatch extends Command {
  public NewPlaceHatch() {
    requires(Robot.claw);
    setTimeout(0.5);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.claw.releaseHatch();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isTimedOut();
  }

  @Override
  protected void interrupted() {
  }
}
