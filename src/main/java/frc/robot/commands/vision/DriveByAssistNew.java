package frc.robot.commands.vision;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class DriveByAssistNew extends Command {
  
  private double kp = 0.02;
  private double leftCommand;
  private double rightCommand;
  private double minCommand = 0.0;
  private boolean finished = false;

  
  
  public DriveByAssistNew() {
    requires(Robot.vision);
  }

  @Override
  protected void initialize() {
    leftCommand = 0;
    rightCommand = 0;
  }

  @Override
  protected void execute() {
    Robot.vision.setVisionMode();
    
    double steeringAdjust = 0;

    double tx = Robot.vision.getActiveLimelight().getTx();

    if (Math.abs(tx) < 1 && Robot.vision.getActiveLimelight().hasTarget()) {
      finished = true;
    }

    if (tx > 0.0) {
      steeringAdjust = kp * tx - minCommand;
    }
    else if (tx < 0.0) {
      steeringAdjust = kp* tx + minCommand;
    }

    double distanceAdjust = Robot.m_oi.getThrottle() * RobotMap.kLimelightDrive;

    leftCommand += steeringAdjust - distanceAdjust;
    rightCommand -= steeringAdjust + distanceAdjust;

    Robot.drive.tankDrive(leftCommand, rightCommand);
  }

  @Override
  protected boolean isFinished() {
    return finished;
  }

  @Override
  protected void end() {
    Robot.vision.setDriverCameraMode();
    System.out.println("DriveByAssist Done");
  }

  @Override
  protected void interrupted() {

  }
}
