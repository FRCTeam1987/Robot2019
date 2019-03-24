package frc.robot.commands.drive;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.util.Util;

public class DrivePivot extends Command {
  
	private final double angleOffset;
	private double initialAngle;
	private double targetAngle;
  private final double tolerance = 1.0;
	private final double kp = 0.02;
	private final double kd = 0.025;
	private boolean isBrake;
  private boolean isHighGear;
  
  public DrivePivot(final double angleOffset) {
    requires(Robot.drive);
    this.angleOffset = angleOffset;
    initialAngle = 0;
    targetAngle = 0;
    isBrake = false;
    isHighGear = true;

    setTimeout(2);
  }

  @Override
  protected void initialize() {
    initialAngle = Robot.drive.getAngle();
    targetAngle = initialAngle + angleOffset;
    isBrake = Robot.drive.isBrake();
    isHighGear = Robot.drive.isHighGear();
    Robot.drive.setLowGear();
  }

  @Override
  protected void execute() {
    final double currentAngle = Robot.drive.getAngle();
    final double gyroRate = Robot.drive.getGyroRate();
    final double deltaAngle = targetAngle - currentAngle;
    double turn = Util.limit(kp * deltaAngle + kd * gyroRate);
      
    turn = Math.copySign(Math.max(Math.abs(turn), Math.abs(0.16)),turn);
    	
    if(Util.isWithinTolerance(currentAngle, targetAngle, tolerance + 7)) {

    	if(Math.abs(gyroRate) < 0.025) {
    		turn = Math.copySign(Math.abs(turn) + 0.04, turn);
      } 
  
      else if (Math.abs(gyroRate) > 0.15) {
    		turn = Math.copySign(Math.abs(turn) - 0.04, turn);
    	}
    }

    	Robot.drive.set(ControlMode.PercentOutput, turn, turn);
  }

  @Override
  protected boolean isFinished() {
    return Util.isWithinTolerance(Robot.drive.getAngle(), targetAngle, tolerance) && 
    Util.isWithinTolerance(Robot.drive.getGyroRate(), 0, 0.1) || 
    isTimedOut();
  }

  @Override
  protected void end() {
    Robot.drive.set(ControlMode.PercentOutput, 0.0, 0.0);

          if(!isBrake)
            Robot.drive.setCoast();
          
          if(isHighGear)
            Robot.drive.setHighGear();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
