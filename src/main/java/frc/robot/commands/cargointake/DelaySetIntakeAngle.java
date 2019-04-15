/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.cargointake;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.RobotMap;

public class DelaySetIntakeAngle extends CommandGroup {
  /**
   * Add your docs here.
   */
  public DelaySetIntakeAngle(final double delay, final double setpoint) {
    addSequential(new WaitCommand(delay));
    addSequential(new SetIntakeAngle(RobotMap.cargoIntakeAngle));
  }
}
