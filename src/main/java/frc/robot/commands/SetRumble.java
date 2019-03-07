package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;


public class SetRumble extends InstantCommand {

  private double m_rumblePercent;
  public SetRumble(final double rumblePercent) {
    super();
    m_rumblePercent = rumblePercent;
  }

  @Override
  protected void initialize() {
    Robot.m_oi.getDriver().setRumble(RumbleType.kLeftRumble, m_rumblePercent);
    Robot.m_oi.getDriver().setRumble(RumbleType.kRightRumble, m_rumblePercent);
  }

}
