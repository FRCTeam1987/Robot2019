package frc.robot.commands.climber;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;


public class ServoValve extends InstantCommand {
 
  private final double m_degrees;

  public ServoValve(final double degrees) {
    super();
    requires(Robot.climber);
    m_degrees = degrees;
  }
  @Override
  protected void initialize() {
    Robot.climber.setValve(m_degrees);
  }

}
