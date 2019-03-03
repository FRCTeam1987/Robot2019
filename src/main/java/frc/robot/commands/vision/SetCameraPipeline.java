/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.vision;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;


public class SetCameraPipeline extends InstantCommand {

  private final int m_pipeline;

  public SetCameraPipeline(final int pipline) {
    super();
    requires(Robot.vision);

    m_pipeline = pipline;
  }

  @Override
  protected void initialize() {
    Robot.vision.limeFront.setPipeline(m_pipeline);
    Robot.vision.limeBack.setPipeline(m_pipeline);

  }

}
