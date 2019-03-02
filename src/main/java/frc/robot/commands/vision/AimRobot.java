package frc.robot.commands.vision;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.util.limelight.LedMode;

public class AimRobot extends Command {
  
  private final double kP = -0.1;

  private double leftPercent;
  private double rightPercent;

  //minimum motor percent
  private final double minCommand = 0.2;
  
  public AimRobot() {
    requires(Robot.vision);
    requires(Robot.drive);
    leftPercent = 0.0;
    rightPercent = 0.0;
  }

  @Override
  protected void initialize() {
    leftPercent = 0.0;
    rightPercent = 0.0;
    Robot.vision.getActiveLimelight().setLedMode(LedMode.ON);
    Robot.drive.setLowGear();
  }

  @Override
  protected void execute() {
    double steeringAdjust = 0.0;
    double target = Robot.vision.getActiveLimelight().getTx();
    double headingError = -target;

    if (target > 1.0) {
      steeringAdjust = kP * headingError - minCommand;
    }
    else if (target < 1.0) {
      steeringAdjust = kP * headingError + minCommand;
    }

    leftPercent += steeringAdjust;
    rightPercent -= steeringAdjust;

    Robot.drive.tankDrive(leftPercent, -rightPercent);

    SmartDashboard.putNumber("Limelight Steering Adjust", steeringAdjust);
  }

  @Override
  protected boolean isFinished() {
    return Robot.vision.isAimed();
  }

  @Override
  protected void end() {
    Robot.drive.tankDrive(0, 0);
    Robot.vision.getActiveLimelight().setLedMode(LedMode.OFF);
  }

  @Override
  protected void interrupted() {
  }
}
