/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team1987.commands.drive;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import frc.team1987.Robot;
import frc.team1987.RobotMap;
import frc.team1987.util.Util;

public class DriveDistance extends Command {

  private final double targetDistance;
  private final double tolerance = 1.0; //arbitrary
  private boolean isBrake;
  private boolean isHighGear;

  public DriveDistance(final double targetDistance) {
    requires(Robot.drive);
    this.targetDistance = targetDistance;
    isBrake = false;
    isHighGear = true;
  }

  @Override
  protected void initialize() {
    isBrake = Robot.drive.isBrake();
    isHighGear = Robot.drive.isHighGear();
    
    Robot.drive.zeroDriveEncoders();
    Robot.drive.setLowGear();
    Robot.drive.setBrake();
    // Robot.drive.setPID(DriveMode.straight);

    final double distanceTicks = Util.distanceToTicks(targetDistance, RobotMap.driveBaseWheelsDiameter);

    Robot.drive.set(ControlMode.Position, distanceTicks, -distanceTicks);
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return Util.isWithinTolerance(Math.abs(Robot.drive.getLeftEncoderDistance()), Math.abs(targetDistance), tolerance);
  }

  @Override
  protected void end() {
    if(!isBrake)
      Robot.drive.setCoast();

    if(isHighGear)
      Robot.drive.setHighGear();

    Robot.drive.set(ControlMode.PercentOutput, 0.0, 0.0);
  }

  @Override
  protected void interrupted() {
  }
}
