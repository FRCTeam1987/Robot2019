package frc.robot.commands.vision;

// import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;


public class AutoAimbot extends Command {
  
  private double m_drive;
  private double m_steer;
  private double m_area;
  private double kPSteer;
  private double m_maxDrive = 0.5;

  public AutoAimbot(final double timeout) {
    requires(Robot.vision);
    requires(Robot.drive);
    m_drive = 0;
    m_steer = 0;
    setTimeout(timeout);
  }

  protected void updateLimelightTracking() { //implement lose target functionality
    if (!Robot.vision.limeFront.hasTarget()) {
      m_steer = 0;
      return;
    }

    m_area = Robot.vision.limeFront.getTa();
      
      if (m_area < 2 && m_area > 0) {
        kPSteer = 0.05;
      }
      else if (m_area > 2 && m_area < 3) {
        kPSteer = 0.06;
      }
      else if (m_area > 3 && m_area < 6) {
        kPSteer = 0.07;
      }
      else if (m_area > 6) {
        kPSteer = 0.075;
      }
      else {
        kPSteer = RobotMap.kLimelightSteer;
      }
       
      double steer = Robot.vision.limeFront.getTx() * kPSteer;
      m_steer = steer;
    
      double drive = (Robot.vision.limeFront.getTy() * RobotMap.kLimelightDrive);
      m_drive = drive;

      // SmartDashboard.putNumber("Aimbot Drive Percentage", m_drive);

      if (drive > m_maxDrive)
        {
          drive = m_maxDrive;
        }
  }

  @Override
  protected void initialize() {
    Robot.vision.setVisionMode();
    Robot.drive.setLowGear();
  }

  @Override
  protected void execute() {
    updateLimelightTracking();
    Robot.drive.arcadeDrive(m_drive, m_steer);
  }

  @Override
  protected boolean isFinished() {
    return (Robot.vision.isAimed() && Robot.vision.isInRange()) || isTimedOut();
  }

  @Override
  protected void end() {
    Robot.drive.tankDrive(0, 0);
    Robot.drive.setHighGear();
    Robot.vision.setDriverCameraMode();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
