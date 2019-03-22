/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SetRumble extends Command {
  
  public SetRumble(final double timeout) {
    setTimeout(timeout);
  }

  @Override
  protected void initialize() {
    Robot.m_oi.getDriver().setRumble(RumbleType.kLeftRumble, 1);
    Robot.m_oi.getDriver().setRumble(RumbleType.kRightRumble, 1);
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return isTimedOut();
  }

  @Override
  protected void end() {
    Robot.m_oi.getDriver().setRumble(RumbleType.kLeftRumble, 0);
    Robot.m_oi.getDriver().setRumble(RumbleType.kRightRumble, 0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
