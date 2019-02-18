package frc.robot.commands.armavator;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.subsystems.Arm.ArmSide;

public class SetArmSide extends InstantCommand {

  private final ArmSide m_armSide;

  public SetArmSide(final ArmSide armSide) {
    super();
    m_armSide = armSide;
  }

  @Override
  protected void initialize() {
    Robot.arm.setArmSide(m_armSide);
  }

}
