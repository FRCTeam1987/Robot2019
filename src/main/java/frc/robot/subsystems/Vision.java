package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.util.limelight.Limelight;

public class Vision extends Subsystem {

  public static Limelight limeFront = new Limelight("front");
  public static Limelight limeBack = new Limelight("back");

  public Vision() {

  }

  @Override
  public void periodic() {

    }

  @Override
  public void initDefaultCommand() {
  }
}
