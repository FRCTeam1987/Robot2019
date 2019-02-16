package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.util.Util;

public class Elevator extends Subsystem {     //POSSIBLE BRAKE IN ELEVATOR????

  private final WPI_TalonSRX elevator;    

  public Elevator() {
    elevator = new WPI_TalonSRX(RobotMap.elevatorMotorID);
    elevator.setName("Elevator", "winch");

    configElevator(elevator);
    zeroElevatorEncoder();
  }

  public void configElevator(final WPI_TalonSRX motor) {
    elevator.configFactoryDefault();
    // elevator.configMotionAcceleration(443); //change once we know gearing reduction
    // elevator.configMotionCruiseVelocity(443); //this too


    elevator.setInverted(true);
    elevator.config_kF(0, 0.0);
    elevator.config_kP(0, 0.4);
    elevator.config_kI(0, 0.0);
    elevator.config_kD(0, 0.0);

    Util.configTalonSRXWithEncoder(elevator, false);
  }

  public double getInches() {
    return Util.ticksToDistance(getTicks(), RobotMap.elevatorPulleyDiameter);
  }

  public int getTicks() {
    return elevator.getSelectedSensorPosition();
  }

  public void zeroElevatorEncoder() {
    elevator.setSelectedSensorPosition(0);
  }

  public void setElevatorAbsolute(final double desiredInches) {     //UNTESTED PID
    final int ticksAbsolute = Util.distanceToTicks(desiredInches, RobotMap.elevatorPulleyDiameter);  
    elevator.set(ControlMode.Position, ticksAbsolute);
    SmartDashboard.putNumber("Elevator Ticks Target", ticksAbsolute);
  }

  public void setElevatorPercent(final double percent) {
    elevator.set(ControlMode.PercentOutput, percent);
  }

  public boolean isWithinTolerance(final double targetInches) {
    return Util.isWithinTolerance(getInches(), targetInches, RobotMap.elevatorTolerance);
  }

  public void periodic() {
    SmartDashboard.putNumber("Elevator Inches", getInches());
  }

  @Override
  public void initDefaultCommand() {

  }
}
