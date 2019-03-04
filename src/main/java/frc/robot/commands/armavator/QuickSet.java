/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.armavator;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.subsystems.Arm.ArmSetpoint;
import frc.robot.subsystems.Arm.ArmSide;
import frc.robot.subsystems.Elevator.ElevatorHeight;
import frc.robot.commands.arm.SetArmAngle;
import frc.robot.commands.elevator.GoToElevatorHeight;

public class QuickSet extends CommandGroup {
  /**
   * Add your docs here.
   */
  public QuickSet() {
    addSequential(new GoToElevatorHeight(ElevatorHeight.QUICKHATCHFLIP));
    addSequential(new SetArmAngle(ArmSetpoint.HATCH, ArmSide.BACK));
    addSequential(new GoToElevatorHeight(ElevatorHeight.LEVEL1HATCH));
    addSequential(new SetArmSide(ArmSide.BACK));
  }
}
