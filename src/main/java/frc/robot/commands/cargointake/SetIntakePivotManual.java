package frc.robot.commands.cargointake;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SetIntakePivotManual extends Command {
  
  private final double m_percent;

  public SetIntakePivotManual(final double percent) {
    requires(Robot.cargoIntake);
    m_percent = percent;
  }

  @Override
  protected void initialize() {
    Robot.cargoIntake.setIntakePivotPercent(m_percent);
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
    Robot.cargoIntake.setIntakePivotPercent(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
