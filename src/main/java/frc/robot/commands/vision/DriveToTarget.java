package frc.robot.commands.vision;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.util.limelight.LedMode;

public class DriveToTarget extends Command {

  private final double kP = -0.1;
  private double driveError; 
  private double driveAdjust;
  private double leftPercent;
  private double rightPercent;

  public DriveToTarget() {
    requires(Robot.vision);
    requires(Robot.drive);
  }

  @Override
  protected void initialize() {
    driveAdjust = 0;
    leftPercent = 0;
    rightPercent = 0;
    Robot.vision.getActiveLimelight().setLedMode(LedMode.ON);
    Robot.drive.setLowGear();
  }

  @Override
  protected void execute() {
    driveError = Robot.vision.getActiveLimelight().getTy();

    driveAdjust = kP * driveError;

    leftPercent += driveAdjust;
    rightPercent -= driveAdjust;

    Robot.drive.tankDrive(leftPercent, rightPercent);
  }

  @Override
  protected boolean isFinished() {
    return Robot.vision.isInRange();
  }

  @Override
  protected void end() {
    Robot.drive.tankDrive(0, 0);
  }

  @Override
  protected void interrupted() {
  }
}
