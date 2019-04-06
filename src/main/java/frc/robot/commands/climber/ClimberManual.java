package frc.robot.commands.climber;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
    Robot.drive.log();
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.climber.setWinchMotor(0);
    Robot.drive.log();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
