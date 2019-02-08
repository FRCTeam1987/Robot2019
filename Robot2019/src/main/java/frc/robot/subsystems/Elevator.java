package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.Util;

public class Elevator extends Subsystem {     //POSSIBLE BRAKE IN ELEVATOR????

  private final WPI_TalonSRX elevator;    

  public Elevator() {
    elevator = new WPI_TalonSRX(RobotMap.elevatorMotorID);

    configElevator(elevator);
    zeroElevatorEncoders();
  }

  public void configElevator(final WPI_TalonSRX motor) {
    elevator.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1); //need default timeout???
    elevator.configMotionAcceleration(443); //change once we know gearing reduction
    elevator.configMotionCruiseVelocity(443); //this too

    Util.configTalonSRXWithEncoder(elevator);
  }

  public int getTicks() {
    return elevator.getSelectedSensorPosition();
  }

  public void zeroElevatorEncoders() {
    elevator.setSelectedSensorPosition(0);
  }

  public void setElevatorAbsolute(final double desiredInches) {     //UNTESTED PID
    final int ticksAbsolute = Util.distanceToTicks(desiredInches, RobotMap.elevatorShaftDiameter);  //are we using shaft diameter??

    if (Util.isWithinTolerance(getTicks(), ticksAbsolute, 4096)) {    //arbitrary number 
      elevator.config_kF(0, 0.9, 0);
      elevator.config_kP(0, 0.8, 0);
      elevator.config_kI(0, 0.0, 0);
      elevator.config_kD(0, 0.0, 0);
    }
    else if (ticksAbsolute > getTicks()) {
      elevator.config_kF(0, 0.4, 0);
      elevator.config_kP(0, 0.18, 0);
      elevator.config_kI(0, 0.0, 0);
      elevator.config_kD(0, 0.0, 0);
    }
    else {
      elevator.config_kF(0, 0.3, 0);
      elevator.config_kP(0, 0.1, 0);
      elevator.config_kI(0, 0.0, 0);
      elevator.config_kD(0, 0.0, 0);
    }

    elevator.set(ControlMode.MotionMagic, ticksAbsolute);
  }

  public boolean isWithinTolerance(final double desiredDegrees) {
    return Util.isWithinTolerance(getTicks(), desiredDegrees, RobotMap.elevatorTolerance);
  }

  @Override
  public void initDefaultCommand() {

  }
}
