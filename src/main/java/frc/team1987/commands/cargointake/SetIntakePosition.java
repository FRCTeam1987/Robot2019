package frc.team1987.commands.cargointake;

import edu.wpi.first.wpilibj.command.Command;
import frc.team1987.Robot;

public class SetIntakePosition extends Command {
  
  private final double m_angle;

  public SetIntakePosition(final double angle) {
    requires(Robot.cargoIntake);
    m_angle = angle;
    setTimeout(1);
  }

  @Override
  protected void initialize() {
    Robot.cargoIntake.setIntakePivot(m_angle);
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return Robot.cargoIntake.isWithinTolerance(m_angle) || isTimedOut();
  }

  @Override
  protected void end() {
    Robot.cargoIntake.setIntakePivotPercent(0);
  }

  @Override
  protected void interrupted() {
  }
}
