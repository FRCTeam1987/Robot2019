package frc.robot.commands.climber;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ClimberManual extends Command {
  private final double m_percent;

  public ClimberManual(final double percent) {
    requires(Robot.climber);
    m_percent = percent;
  }

  @Override
  protected void initialize() {
    Robot.climber.setWinchMotor(m_percent);
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.climber.setWinchMotor(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
