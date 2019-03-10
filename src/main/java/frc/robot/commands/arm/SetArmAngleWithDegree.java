/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.arm;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class SetArmAngleWithDegree extends Command {

  private double m_degree;

  public SetArmAngleWithDegree(final double degree) {
    requires(Robot.arm);
    setTimeout(RobotMap.defaultTimeout);
    m_degree = degree;
  }

  @Override
  protected void initialize() {
    Robot.arm.setWristAbsolute(m_degree);
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return Robot.arm.isWithinTolerance(m_degree) || isTimedOut();
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
