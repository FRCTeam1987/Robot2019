package frc.team1987.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.team1987.Robot;

public class EnableCompressor extends InstantCommand {

  public EnableCompressor() {
    super();
  }

  @Override
  protected void initialize() {
    Robot.compressor.setClosedLoopControl(true);
  }

}
