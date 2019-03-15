package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.Arm.ArmSide;
import frc.robot.util.Util;
import frc.robot.util.limelight.Limelight;

public class Vision extends Subsystem {

  public Limelight limeFront;
  public Limelight limeBack;

  public Vision() {
    limeFront = new Limelight("front");
    limeBack = new Limelight("back");
  }

  public Limelight getActiveLimelight() {   //needs to be tested
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

  public boolean isAimed() {   
    return Util.isWithinTolerance(getActiveLimelight().getTx(), 0.0, 0.2);
  }

  public boolean isInRange() {
    return Util.isWithinTolerance(getActiveLimelight().getTa(), RobotMap.limelightHatchTargetArea, 0.2);
  }

  @Override
  public void periodic() {
    // SmartDashboard.putBoolean("Has target", limeFront.hasTarget());
  }

  @Override
  public void initDefaultCommand() {
  }
}
