package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.Arm.ArmSide;
import frc.robot.util.Util;
import frc.robot.util.limelight.CameraMode;
import frc.robot.util.limelight.LedMode;
import frc.robot.util.limelight.Limelight;

public class Vision extends Subsystem {

  public Limelight limeFront;

  public Vision() {
    limeFront = new Limelight("front");
  }

  public void setDriverCameraMode() {
    Robot.vision.limeFront.setCameraMode(CameraMode.DRIVERCAMERA);
    Robot.vision.limeFront.setPipeline(9);
    Robot.vision.limeFront.setLedMode(LedMode.OFF);
  }

  public void setVisionMode() {
    Robot.vision.limeFront.setCameraMode(CameraMode.VISION);
    Robot.vision.limeFront.setPipeline(0);
    Robot.vision.limeFront.setLedMode(LedMode.ON);
  }

  public boolean isAimed() {   
    return Util.isWithinTolerance(limeFront.getTx(), 0.0, 2) && this.limeFront.hasTarget();
  }

  public boolean isInAreaRange() {
    return Util.isWithinTolerance(limeFront.getTa(), RobotMap.limelightHatchTargetArea, 0.2);
  }

  public boolean isInRange() {
    return Util.isWithinTolerance(limeFront.getTy(), 0.0, 5);
  }

  @Override
  public void periodic() {
    // SmartDashboard.putBoolean("Has target", limeFront.hasTarget());
  }

  @Override
  public void initDefaultCommand() {
  }
}
