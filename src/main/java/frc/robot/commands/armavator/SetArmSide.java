package frc.robot.commands.armavator;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.subsystems.Arm.ArmSide;

public class SetArmSide extends InstantCommand {

  private ArmSide m_armSide;
  private boolean m_isDefault;

  public SetArmSide(final ArmSide armSide) {
    super();
    m_armSide = armSide;
    m_isDefault = false;
  }
  
  public SetArmSide() {
    m_isDefault = true;
  }

  @Override
  protected void initialize() {
    if (m_isDefault) {
      m_armSide = Robot.arm.getArmSideState();
    } 



    Robot.arm.setArmSide(m_armSide);
  }

}
