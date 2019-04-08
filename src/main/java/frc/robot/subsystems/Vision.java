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
  public Limelight limeBack;

  public Vision() {
    limeFront = new Limelight("back");
    limeBack = new Limelight("front");
  }

  public Limelight getActiveLimelight() {
    if (Robot.arm.getArmSideState() == ArmSide.FRONT) {
      return limeFront;
    }
    else {
      return limeBack;
    }
  }

  public boolean isLimelightActive(final Limelight limelight) {
    return getActiveLimelight() == limelight ? true : false;
  }

  public void setDriverCameraMode() {
    Robot.vision.getActiveLimelight().setCameraMode(CameraMode.DRIVERCAMERA);
    Robot.vision.getActiveLimelight().setPipeline(9);
    Robot.vision.getActiveLimelight().setLedMode(LedMode.OFF);
  }

  public void setVisionMode() {
    Robot.vision.getActiveLimelight().setCameraMode(CameraMode.VISION);
    Robot.vision.getActiveLimelight().setPipeline(0);
    Robot.vision.getActiveLimelight().setLedMode(LedMode.ON);
  }

  public boolean isAimed() {   
    return Util.isWithinTolerance(getActiveLimelight().getTx(), 0.0, 2) && this.getActiveLimelight().hasTarget();
  }

  public boolean isInAreaRange() {
    return Util.isWithinTolerance(getActiveLimelight().getTa(), RobotMap.limelightHatchTargetArea, 0.2);
  }

  public boolean isInRange() {
    return Util.isWithinTolerance(getActiveLimelight().getTy(), 0.0, 5);
  }

  @Override
  public void periodic() {
    // SmartDashboard.putBoolean("Has target", limeFront.hasTarget());
  }

  @Override
  public void initDefaultCommand() {
  }
}
