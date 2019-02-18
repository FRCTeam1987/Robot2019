/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.arm;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.Arm.ArmAngle;

public class SetArmAngle extends Command {
  
  private final ArmAngle m_angle;
  
  public SetArmAngle(final ArmAngle angle) {
    requires(Robot.arm);
    m_angle = angle;
    setTimeout(1);
  }

  @Override
  protected void initialize() {    
    switch(m_angle) {
      case HATCH:
        Robot.arm.setWristAbsolute(RobotMap.armHatchAngle);
        break;
      case CARGOCOLLECTFLOOR:
        Robot.arm.setWristAbsolute(RobotMap.armFloorCollectCargoAngle);
        break;
      case CARGOLOADINGSTATION:
        Robot.arm.setWristAbsolute(RobotMap.armLoadingStationCargoAngle);
        break;
    }

  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return Robot.arm.isWithinTolerance() || isTimedOut();
  }

  @Override
  protected void end() {
    Robot.arm.setWristPercent(0);
  }

  @Override
  protected void interrupted() {
  }
}
