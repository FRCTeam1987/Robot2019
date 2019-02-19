/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.vision;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.util.limelight.CameraMode;


public class SetCameraMode extends InstantCommand {

  private CameraMode m_cameraMode;

  public SetCameraMode(final CameraMode cameraMode) {
    super();
    requires(Robot.vision);

    m_cameraMode = cameraMode;
  }

  @Override
  protected void initialize() {
    Robot.vision.limeFront.setCameraMode(m_cameraMode);
  }
}
