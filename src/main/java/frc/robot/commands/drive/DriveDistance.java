package frc.robot.commands.drive;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.DriveMode;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.util.Util;

public class DriveDistance extends Command {

  private final double targetDistance;
  private final double tolerance = 1 / 12;
  private boolean isBrake;
  private boolean isHighGear;

  public DriveDistance(final double feet) {
    requires(Robot.drive);
    this.targetDistance = feet;
    isBrake = false;
    isHighGear = true;
    Robot.drive.setPID(DriveMode.STRAIGHT);
  }

  @Override
  protected void initialize() {
    isBrake = Robot.drive.isBrake();
    isHighGear = Robot.drive.isHighGear();
    
    Robot.drive.zeroDriveEncoders();
    Robot.drive.setHighGear();
    Robot.drive.setBrake();

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
    end();
  }
}
