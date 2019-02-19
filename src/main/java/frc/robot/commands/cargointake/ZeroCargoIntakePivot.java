package frc.robot.commands.cargointake;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class ZeroCargoIntakePivot extends InstantCommand {
  

  public ZeroCargoIntakePivot() {
    super();
    requires(Robot.cargoIntake);
  }

  @Override
  protected void initialize() {
    Robot.cargoIntake.zeroCargoIntakePivot();
  }

}
