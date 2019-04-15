/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.arm;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ArmManual extends Command {
  
  private double m_percent;
  public ArmManual(final double percent) {
    requires(Robot.arm);
    m_percent = percent;
  }

  @Override
  protected void initialize() {
    Robot.arm.setWristPercent(m_percent);
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.arm.setWristAbsolute(Robot.arm.getArmAngle());
    // Robot.arm.setWristPercent(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
