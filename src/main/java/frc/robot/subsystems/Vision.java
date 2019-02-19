package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.subsystems.Arm.ArmSide;
import frc.robot.util.Util;
import frc.robot.util.limelight.LedMode;
import frc.robot.util.limelight.Limelight;

public class Vision extends Subsystem {

  public static Limelight limeFront;
  public static Limelight limeBack;

  public Vision() {
    limeFront = new Limelight("front");
    limeBack = new Limelight("back");
  }

  public Limelight getLimelight() {   //needs to be tested
    if (Robot.arm.getArmSide() == ArmSide.FRONT) {
      return limeFront;
    }
    else {
      return limeBack;
    }
  }

  public boolean isAimed() {   
    return Util.isWithinTolerance(getLimelight().getTx(), 0.0, 0.2);
  }

  public boolean isInRange() {
    return Util.isWithinTolerance(getLimelight().getTy(), 0.0, 0.1);
  }

  @Override
  public void periodic() {
  }

  @Override
  public void initDefaultCommand() {
  }
}
