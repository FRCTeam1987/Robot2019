package frc.robot.commands.arm;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.Arm.ArmSetpoint;
import frc.robot.subsystems.Arm.ArmSide;

public class SetArmAngle extends Command {
  
  private ArmSetpoint m_setpoint;
  private ArmSide m_armSide;
  private double m_targetAngle;
  private boolean m_isDefault;
  
  public SetArmAngle(final ArmSetpoint setpoint, final ArmSide armSide) {
    requires(Robot.arm);
    m_setpoint = setpoint;
    m_targetAngle = 0;
    m_armSide = armSide;
    setTimeout(2.0);
    m_isDefault = false;
  }

  public SetArmAngle() {
    requires(Robot.arm);
    setTimeout(2.0);
    m_isDefault = true;
  }

  @Override
  protected void initialize() {
    if (m_isDefault) {
      m_setpoint = Robot.arm.getArmSetpointState();
      m_armSide = Robot.arm.getArmSideState();
    }
    
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
    if (isTimedOut()) {
      System.out.println("SetArmAngle timedout");
    }
    // else {
    //   Robot.claw.setRumble(0.5);  //might need to change
    // }
  }

  @Override
  protected void interrupted() {
  }
}
