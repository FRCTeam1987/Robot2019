/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class SetElevatorAbsolute extends Command {

  private double m_inches;

  public SetElevatorAbsolute(final double inches) {
    requires(Robot.elevator);
    setTimeout(RobotMap.setElevatorAbsoluteTimeout);
    m_inches = inches;
  }

  @Override
  protected void initialize() {
    Robot.elevator.setElevatorAbsolute(m_inches);
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return Robot.elevator.isWithinTolerance(m_inches) || isTimedOut();
  }

  @Override
  protected void end() {
    Robot.elevator.setElevatorPercent(0);
  }

  @Override
  protected void interrupted() {
  }
}
