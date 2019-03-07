package frc.robot.commands.vision;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.util.limelight.CameraMode;
import frc.robot.util.limelight.LedMode;

public class AimRobot extends Command {
  
  private double m_limelightDrive;
  private double m_limelightSteer;

  public AimRobot() {
    requires(Robot.vision);
    requires(Robot.drive);
    m_limelightDrive = 0;
    m_limelightSteer = 0;
  }

  protected void updateLimelightTracking() {
    if (!Robot.vision.getActiveLimelight().hasTarget()) {
      m_limelightDrive = 0;
      m_limelightSteer = 0;
      return;
    }

    double steer = Robot.vision.getActiveLimelight().getTx() * RobotMap.kLimelightSteer;
    m_limelightSteer = steer;

    // double drive = (RobotMap.limelightHatchTargetArea - Robot.vision.getActiveLimelight().getTa()) * RobotMap.kLimelightDrive;
    double drive = Robot.vision.getActiveLimelight().getTy() * RobotMap.kLimelightDrive;


    if (drive > RobotMap.limelightMaxDrive) {
      drive = RobotMap.limelightMaxDrive;
    }

    m_limelightDrive = drive;
  }

  @Override
  protected void initialize() {
    Robot.vision.getActiveLimelight().setCameraMode(CameraMode.VISION);
    Robot.vision.getActiveLimelight().setPipeline(0);
    Robot.vision.getActiveLimelight().setLedMode(LedMode.ON);
    Robot.drive.setLowGear();
  }

  @Override
  protected void execute() {
    updateLimelightTracking();

    if (Robot.vision.getActiveLimelight().hasTarget()) {
      Robot.drive.arcadeDrive(m_limelightDrive, m_limelightSteer);
    }
  }

  @Override
  protected boolean isFinished() {
    return Robot.vision.isAimed() && Robot.vision.isInRange();
  }

  @Override
  protected void end() {
    Robot.drive.tankDrive(0, 0);
    Robot.vision.getActiveLimelight().setLedMode(LedMode.OFF);
    Robot.vision.getActiveLimelight().setPipeline(9);
  }

  @Override
  protected void interrupted() {
  }
}
