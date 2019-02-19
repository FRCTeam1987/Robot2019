package frc.robot.commands.vision;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.util.limelight.CameraMode;

public class SandstormDrive extends Command {
  
  public SandstormDrive() {
  }

  @Override
  protected void initialize() {
    Robot.vision.limeFront.setCameraMode(CameraMode.DRIVERCAMERA);
    Robot.vision.limeBack.setCameraMode(CameraMode.DRIVERCAMERA);
  }

  @Override
  protected void execute() {
    Robot.drive.xboxDrive(Robot.m_oi.getDriver());
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
