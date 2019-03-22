package frc.robot.commands.vision;

// import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.util.limelight.CameraMode;
import frc.robot.util.limelight.LedMode;

public class AutoAimbot extends Command {
  
  // private final double M_STARTING_SPEED = 0.5;

  private double m_drive;
  private double m_steer;
  // private double m_timeWhenFirstHadTarget;

  public AutoAimbot() {
    requires(Robot.vision);
    requires(Robot.drive);
    m_drive = 0;
    m_steer = 0;
    // m_timeWhenFirstHadTarget = 0;
  }

  protected void updateLimelightTracking() {
    if (!Robot.vision.getActiveLimelight().hasTarget()) {
      m_drive = 0;
      m_steer = 0;
      // m_timeWhenFirstHadTarget = 0;
      return;
    }
    // if (m_timeWhenFirstHadTarget <= 0.01) {
    //   m_timeWhenFirstHadTarget = Timer.getFPGATimestamp();
    // }

    double steer = Robot.vision.getActiveLimelight().getTx() * RobotMap.kLimelightSteer;
    m_steer = steer;

    // double drive = (RobotMap.limelightHatchTargetArea - Robot.vision.getActiveLimelight().getTa()) * RobotMap.kLimelightDrive;
    double drive = Robot.vision.getActiveLimelight().getTy() * RobotMap.kLimelightDrive;

    if (drive > RobotMap.limelightMaxDrive) {
      drive = RobotMap.limelightMaxDrive;
      m_steer *= 0.75;
    }

    m_drive = drive;
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
    return Robot.vision.isAimed() && Robot.vision.isInRange();
  }

  @Override
  protected void end() {
    Robot.drive.tankDrive(0, 0);
    Robot.vision.setDriverCameraMode();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
