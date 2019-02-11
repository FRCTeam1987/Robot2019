package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.util.Util;
import frc.robot.util.limelight.Limelight;

public class Vision extends Subsystem {

  public Limelight limeFront = new Limelight("front");
  public Limelight limeBack = new Limelight("back");

  public Vision() {

  }

  public boolean isAimed() {   
    return Util.isWithinTolerance(limeFront.getTx(), 0.0, 0.2) || Util.isWithinTolerance(limeBack.getTx(), 0.0, 0.2);
  }

  @Override
  public void periodic() {
    }

  @Override
  public void initDefaultCommand() {
  }
}
