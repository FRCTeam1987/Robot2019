/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.cargointake;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;
import frc.robot.commands.arm.SetArmAngleWithDegree;
import frc.robot.commands.elevator.SetElevatorAbsolute;

public class SetArmAndElevatorForCargoCollect extends CommandGroup {
  
  public SetArmAndElevatorForCargoCollect() {
    addSequential(new SetElevatorAbsolute(RobotMap.elevatorFlipHeight));
    addSequential(new SetArmAngleWithDegree(RobotMap.armFloorCollectCargoAngle));
  }
}
