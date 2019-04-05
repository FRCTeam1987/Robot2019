package frc.robot.commands.climber;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class EngageVacuum extends InstantCommand {

  public EngageVacuum() {
    super();
    requires(Robot.climber);
  }

  @Override
  protected void initialize() {
    Robot.climber.setValve(true);
    Robot.climber.setVenturi(true);
  }
}
