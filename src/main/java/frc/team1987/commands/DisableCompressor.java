package frc.team1987.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team1987.Robot;

public class DisableCompressor extends InstantCommand {

  public DisableCompressor() {
    super();
  }

  @Override
  protected void initialize() {
    Robot.compressor.setClosedLoopControl(false);
    Robot.compressor.stop();
  }

}
