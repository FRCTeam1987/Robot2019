package frc.robot.commands.vision;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.util.limelight.CameraMode;
import frc.robot.util.limelight.LedMode;

public class AutoCamera extends InstantCommand {

  public AutoCamera() {
    super();
    requires(Robot.vision);
  }

  @Override
  protected void initialize() {
    Robot.vision.limeFront.setCameraMode(CameraMode.DRIVERCAMERA);
    Robot.vision.limeFront.setPipeline(1);
    Robot.vision.limeBack.setCameraMode(CameraMode.DRIVERCAMERA);
    Robot.vision.limeBack.setPipeline(1);
    Robot.vision.limeFront.setLedMode(LedMode.OFF);
    Robot.vision.limeBack.setLedMode(LedMode.OFF);
  }
}
