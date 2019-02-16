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
    Robot.vision.getLimelight().setCameraMode(CameraMode.DRIVERCAMERA);
    Robot.vision.getLimelight().setPipeline(1);
    Robot.vision.getLimelight().setLedMode(LedMode.OFF);
  }
}
