package frc.robot.commands.cargointake;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SetIntakeAngle extends Command {

  private final double m_angle;
  
  public SetIntakeAngle(final double angle) {
    requires(Robot.cargoIntake);
    setTimeout(1.5);
    m_angle = angle;
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
