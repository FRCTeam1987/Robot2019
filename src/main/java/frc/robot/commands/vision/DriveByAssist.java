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
    if (!Robot.vision.limeFront.hasTarget()) {
      m_steer = 0;
      return;
    }

    m_area = Robot.vision.limeFront.getTa();
      
      // if (m_area < 2 && m_area > 0) {
      //   kPSteer = 0.05;
      // }
      // else if (m_area > 2 && m_area < 3) {
      //   kPSteer = 0.06;
      // }
      // else if (m_area > 3 && m_area < 6) {
      //   kPSteer = 0.04;
      // }
      // else if (m_area > 6) {
      //   kPSteer = 0.04;
      // }
      // else {
      //   kPSteer = RobotMap.kLimelightSteer;
      // }

      kPSteer = 0.04;
       
      double steer = Robot.vision.limeFront.getTx() * kPSteer;
      m_steer = steer;
    
      double drive = Robot.m_oi.getThrottle() * 0.75;
      m_drive = drive;

  }

  @Override
  protected void initialize() {
    Robot.vision.setVisionMode();
    m_drive = 0;
    m_steer = 0;
  }

  @Override
  protected void execute() {
    updateLimelightTracking();
    Robot.drive.arcadeDrive(m_drive, m_steer);
  }

  @Override
  protected boolean isFinished() {
    return Robot.vision.isAimed() && Robot.vision.isInRange();
  }

  @Override
  protected void end() {
    Robot.vision.setDriverCameraMode();
    Robot.drive.tankDrive(0, 0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
