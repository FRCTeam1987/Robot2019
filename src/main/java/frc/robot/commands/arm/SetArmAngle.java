/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.arm;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SetArmAngle extends Command {
  
  private final double m_angle;
  
  public SetArmAngle(final double angle) {
    // requires(Robot.arm);
    m_angle = angle;
    setTimeout(1);
  }

  @Override
  protected void initialize() {
    // Robot.arm.setWristAbsolute(m_angle);
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return false;//Robot.arm.isWithinTolerance(m_angle) || isTimedOut();
  }

  @Override
  protected void end() {
    // Robot.arm.setWristPercent(0);
  }

  @Override
  protected void interrupted() {
  }
}
