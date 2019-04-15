/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.claw;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.util.DigitalDebouncer;

public class NewCollectHatch extends Command {

  private final double secondsUntilActuateFingers = 0.25;
  private final double secondsForFingerActuation = 0.25;
  private final double secondsForRumble = secondsForFingerActuation / 2;
  private DigitalDebouncer isHatchReadyToCollect;
  private double timeActuated;

  public NewCollectHatch() {
    requires(Robot.claw);
    isHatchReadyToCollect = new DigitalDebouncer(secondsUntilActuateFingers);
    timeActuated = -1.0;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.claw.releaseHatch();
    isHatchReadyToCollect.periodic(Robot.claw.isHatchCollected());
    timeActuated = -1.0;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    final boolean isHatchSensed = Robot.claw.isHatchCollected();
    final double currentTime = Timer.getFPGATimestamp();
    isHatchReadyToCollect.periodic(isHatchSensed);
    if (timeActuated < 0.0 && isHatchReadyToCollect.get()) {
      Robot.m_oi.rumbleDriver(1);
      Robot.claw.collectHatch();
      timeActuated = currentTime;
    } else if (timeActuated > 0.0 && !isHatchSensed) {
      timeActuated = -1.0;
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isHatchReadyToCollect.get() && Timer.getFPGATimestamp() + secondsForFingerActuation > timeActuated;
  }

  @Override
  protected void end() {
    Robot.arm.setWristAbsolute(Robot.arm.getArmAngle());
    Robot.m_oi.rumbleDriver(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
