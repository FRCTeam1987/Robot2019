/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.claw;

import edu.wpi.first.wpilibj.command.ConditionalCommand;
import frc.robot.Robot;

public class ManipulateHatch extends ConditionalCommand {
  public ManipulateHatch() {
    super(new NewPlaceHatch(), new NewCollectHatch());
  }

  @Override
    protected boolean condition() {
      return Robot.claw.isHatchCollected();
    }
}
