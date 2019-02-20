package frc.robot.commands.armavator;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.subsystems.Arm.ArmSide;

public class SetArmSide extends InstantCommand {

  private ArmSide m_armSide;

  public SetArmSide(final ArmSide armSide) {
    super();
    m_armSide = armSide;
  }
  
  public SetArmSide() {
    m_armSide = ArmSide.FRONT;

  }

  @Override
  protected void initialize() {
    m_armSide = Robot.arm.getArmSide();
    Robot.arm.setArmSide(m_armSide);
  }

}
