package frc.robot.commands.drive;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;

public class DrivePath extends Command {
  
  private Trajectory m_toFollow;
  private EncoderFollower leftFollower;
  private EncoderFollower rightFollower;
  private boolean isReversed;

  public DrivePath(final Waypoint[] path, final boolean isReversed) {
    requires(Robot.drive);
    // try {
    //   m_toFollow = PathfinderFRC.getTrajectory(pathName);
    // }
    // catch(IOException e) {
    //   System.out.println("=======================");
		//   System.out.println("=======================");
		//   System.out.println("=======================");
		//   System.out.println("Could not get path");
	  // 	System.out.println("=======================");
	  // 	System.out.println("=======================");
	  // 	System.out.println("=======================");
    // }

    EncoderFollower[] followers = Robot.drive.setupPath(makeTrajectory(path));
    leftFollower = followers[0];
    rightFollower = followers[1];
  }

  protected Trajectory makeTrajectory(final Waypoint[] path) {
		// String hash = WaypointsHash(path);
		// System.out.println(hash);
		// File cacheFile = new File(cacheFilename(hash));
		// if(cacheFile.exists()) {
		// 	// load the trajectory from the cache
		// 	System.out.println("Reading cached trajectory");
		// 	return Pathfinder.readFromFile(cacheFile);
		File pathFile = new File(pathName("test"));
		// }
		// else {
			// this path isn't cached - generate it first
			System.out.println("Creating new trajectory...");
			// if(this.driveMode == DriveMode.DRIVEPATHLOW) {
			// 	Drive.DrivetrainProfiling.setProfile(Drive.low);
			// 	System.out.println("drive profile low");
			// }
			// else if(this.driveMode == DriveMode.DRIVEPATHSTRAIGHT) {
			// 	Drive.DrivetrainProfiling.setProfile(Drive.straight);
			// 	System.out.println("drive profile straight");
			// }
			// else if(this.driveMode == DriveMode.DRIVEPATHTURNS) {
			// 	Drive.DrivetrainProfiling.setProfile(Drive.turns);
			// 	System.out.println("drive profile turns");
			// } else {
			// 	System.out.println("=======================");
			// 	System.out.println("=======================");
			// 	System.out.println("=======================");
			// 	System.out.println("did not find drive profile: " + this.driveMode);
			// 	System.out.println("=======================");
			// 	System.out.println("=======================");
			// 	System.out.println("=======================");
			// }
			
//			Drive.DrivetrainProfiling.setProfile(Drive.straight);
			// System.out.println("profile max acceleration: " + Drive.DrivetrainProfiling.max_acceleration);
			
	        Trajectory.Config cfg = new Trajectory.Config(Trajectory.FitMethod.HERMITE_QUINTIC, Trajectory.Config.SAMPLES_HIGH,
//	                RobotMap.period, driveProfile.getMaxVelocity(), driveProfile.getMaxAcceleration(), driveProfile.getMaxJerk());
	        		RobotMap.period, RobotMap.maxHighGearVelocity, RobotMap.maxHighGearAcceleration, RobotMap.maxHighGearJerk);
	        
					Trajectory toFollow = Pathfinder.generate(path, cfg);
						        
	        // Cache the trajectory for next time
	        // Pathfinder.writeToFile(cacheFile, toFollow);
					// System.out.println("Saving new trajectory cache");
					
					Pathfinder.writeToCSV(pathFile, toFollow);

	        return toFollow;
    
	}
	
	protected static String pathName(final String pathName) {
		return "/home/lvuser/paths/" + pathName + ".csv";
	}

  // protected static String WaypointsHash(Waypoint[] waypoints) {
	// 	try {
	// 		String str = "";
	// 		for (int i=0; i<waypoints.length; i++) {
	// 			str = str.concat(String.format("%.2f %.2f %.2f\n",
	// 					waypoints[i].x, waypoints[i].y, waypoints[i].angle));
	// 		}

	// 		MessageDigest md = MessageDigest.getInstance("SHA-256");
	// 		md.update(str.getBytes());
	// 		return DatatypeConverter.printHexBinary(md.digest()).toUpperCase();
	// 	}
	// 	catch(NoSuchAlgorithmException e) {
  //     return "";
  //   }
		// }

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
