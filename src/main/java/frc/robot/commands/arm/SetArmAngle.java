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
import frc.robot.subsystems.Arm.ArmSetpoint;
import frc.robot.subsystems.Arm.ArmSide;

public class SetArmAngle extends Command {
  
  private final ArmSetpoint m_setpoint;
  private final ArmSide m_armSide;
  private double m_targetAngle;
  
  public SetArmAngle(final ArmSetpoint setpoint, final ArmSide armSide) {
    requires(Robot.arm);
    m_setpoint = setpoint;
    m_targetAngle = 0;
    m_armSide = armSide;
    setTimeout(1.5);
  }

  @Override
  protected void initialize() {    
    switch(m_setpoint) {
      case HATCH:
        m_targetAngle = RobotMap.armHatchAngle * (m_armSide == ArmSide.FRONT ? 1 : -1);
        Robot.arm.setWristAbsolute(m_targetAngle);
        break;
      case CARGOCOLLECTFLOOR:
        m_targetAngle = RobotMap.armFloorCollectCargoAngle;
        Robot.arm.setWristAbsolute(m_targetAngle);
        break;
      case CARGOLOADINGSTATION:
        m_targetAngle = RobotMap.armLoadingStationCargoAngle * (m_armSide == ArmSide.FRONT ? 1 : -1);
        Robot.arm.setWristAbsolute(m_targetAngle);
        break;
      case HOME:
        m_targetAngle = RobotMap.armHomeAngle;
        Robot.arm.setWristAbsolute(m_targetAngle);
        break;
      case CARGOSHIP:
        m_targetAngle = RobotMap.armCargoShipAngle *  (m_armSide == ArmSide.FRONT ? 1 : -1);
        Robot.arm.setWristAbsolute(m_targetAngle);
        break;
      case HATCHCOLLECT:
        m_targetAngle = RobotMap.armHatchCollectedAngle;
        Robot.arm.setWristAbsolute(m_targetAngle);
        break;
      case CARGOROCKETLVL1:
        m_targetAngle = RobotMap.armRocketLevel1CargoAngle;
        Robot.arm.setWristAbsolute(m_targetAngle);
        break;
      case CARGOROCKETLVL2:
        m_targetAngle = RobotMap.armRocketLevel2CargoAngle;
        Robot.arm.setWristAbsolute(m_targetAngle);
        break;
    }
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return Robot.arm.isWithinTolerance(m_targetAngle) || isTimedOut();
  }

  @Override
  protected void end() {
    Robot.arm.setWristPercent(0);
  }

  @Override
  protected void interrupted() {
  }
}
