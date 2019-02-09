/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team1987.commands.arm;

import edu.wpi.first.wpilibj.command.Command;
import frc.team1987.Robot;

public class SetArmPosition extends Command {

  private final double m_angle;
  
  public SetArmPosition(final double angle) {
    requires(Robot.arm);
    m_angle = angle;
    setTimeout(1.0);
  }

  @Override
  protected void initialize() {
    Robot.arm.setWristAbsolute(m_angle);
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return Robot.arm.isWithinTolerance(m_angle) || isTimedOut();
  }

  @Override
  protected void end() {
    Robot.arm.setWristPercent(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
