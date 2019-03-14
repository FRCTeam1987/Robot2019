package frc.robot.commands.cargointake;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class SetIntakeAngle extends Command {

  private final double m_angle;
  
  public SetIntakeAngle(final double angle) {
    requires(Robot.cargoIntake);
    setTimeout(3);
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
    if (isTimedOut()) {
      System.out.println("SetIntakeAngle timedout");
    }
  }

  @Override
  protected void interrupted() {
    Robot.cargoIntake.setIntakePivot(RobotMap.cargoIntakeHomeAngle);
  }
}
