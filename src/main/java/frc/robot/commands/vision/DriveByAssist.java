package frc.robot.commands.vision;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class DriveByAssist extends Command {
  
  private double m_steer;
  private double m_drive;
  private double m_area;

  private double kPSteer;
  
  public DriveByAssist() {
    requires(Robot.vision);
    requires(Robot.drive);
  }

  protected void updateLimelightTracking() {
    if (!Robot.vision.getActiveLimelight().hasTarget()) {
      m_steer = 0;
      return;
    }

    m_area = Robot.vision.getActiveLimelight().getTa();

    if (Robot.vision.getActiveLimelight() == Robot.vision.limeFront) {
      
      if (m_area < 2 && m_area > 0) {
        kPSteer = 0.05;
      }
      else if (m_area > 2 && m_area < 3) {
        kPSteer = 0.06;
      }
      else if (m_area > 3 && m_area < 6) {
        kPSteer = 0.09;
      }
      else if (m_area > 6) {
        kPSteer = 0.125;
      }
      else {
        kPSteer = RobotMap.kLimelightSteer;
      }
       
      double steer = Robot.vision.getActiveLimelight().getTx() * kPSteer;
      m_steer = steer;
    
      double drive = Robot.m_oi.getThrottle() * RobotMap.kLimelightDrive;
      m_drive = drive;

    }

    double drive = Robot.m_oi.getThrottle() * RobotMap.kLimelightDrive;
    m_drive = drive;
  }

  @Override
  protected void initialize() {
    Robot.vision.setVisionMode();
    Robot.drive.setLowGear();
    // System.out.println("DriveByAssist Running");
  }

  @Override
  protected void execute() {
    updateLimelightTracking();
    Robot.drive.arcadeDrive(m_drive, m_steer);
  }

  @Override
  protected boolean isFinished() {
    return Robot.vision.isAimed();
  }

  @Override
  protected void end() {
    Robot.vision.setDriverCameraMode();
    Robot.drive.tankDrive(0, 0);
    System.out.println("DriveByAssist Done");
  }

  @Override
  protected void interrupted() {
    end();
  }
}
