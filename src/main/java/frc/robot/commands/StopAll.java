package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class StopAll extends InstantCommand {

  public StopAll() {
    super();
    requires(Robot.arm);
    requires(Robot.cargoIntake);
    requires(Robot.claw);
    requires(Robot.climber);
    requires(Robot.drive);
    requires(Robot.elevator);
  }

  @Override
  protected void initialize() {
    Robot.arm.setWristPercent(0);
    Robot.cargoIntake.setIntakePivotPercent(0);
    Robot.cargoIntake.setRoller(0);
    Robot.claw.setWheels(0);
    Robot.climber.setWinchMotor(0);
    Robot.elevator.setElevatorPercent(0);
    Robot.drive.tankDrive(0, 0);
  }

}
