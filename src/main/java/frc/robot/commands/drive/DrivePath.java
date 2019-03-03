package frc.robot.commands.drive;

import java.io.IOException;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import jaci.pathfinder.PathfinderFRC;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;

public class DrivePath extends Command {
  
  private Trajectory m_toFollow;
  private EncoderFollower leftFollower;
  private EncoderFollower rightFollower;
  private boolean isReversed;

  public DrivePath(final String pathName, final boolean isReversed) {
    requires(Robot.drive);
    try {
      Trajectory m_toFollow = PathfinderFRC.getTrajectory(pathName);
    }
    catch(IOException e) {
      System.out.println("=======================");
		  System.out.println("=======================");
		  System.out.println("=======================");
		  System.out.println("Could not get path");
	  	System.out.println("=======================");
	  	System.out.println("=======================");
	  	System.out.println("=======================");
    }

    EncoderFollower[] followers = Robot.drive.setupPath(m_toFollow);
    leftFollower = followers[0];
    rightFollower = followers[1];
  }

  @Override
  protected void initialize() {
    Robot.drive.zeroDriveEncoders();
    Robot.drive.ahrsReset();

    leftFollower.reset();
    rightFollower.reset();

    Robot.drive.followPath(leftFollower, rightFollower, isReversed);
  }

  @Override
  protected void execute() {
    Robot.drive.followPath(leftFollower, rightFollower, isReversed);
  }

  @Override
  protected boolean isFinished() {
    return leftFollower.isFinished() && rightFollower.isFinished();
  }

  @Override
  protected void end() {
    Robot.drive.tankDrive(0, 0);
  }

  @Override
  protected void interrupted() {
    System.out.println("Drive Path interrupted!");
    end();
  }
}
