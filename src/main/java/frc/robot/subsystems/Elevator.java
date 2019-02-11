package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.util.Util;

public class Elevator extends Subsystem {     //POSSIBLE BRAKE IN ELEVATOR????

  private final WPI_TalonSRX elevator;    

  public Elevator() {
    super("elevator");
    elevator = new WPI_TalonSRX(RobotMap.elevatorMotorID);

    configElevator(elevator);
    zeroElevatorEncoders();
  }

  public void configElevator(final WPI_TalonSRX motor) {
    elevator.configFactoryDefault();
    // elevator.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1); //need default timeout???
    elevator.configMotionAcceleration(443); //change once we know gearing reduction
    elevator.configMotionCruiseVelocity(443); //this too
    elevator.setName("elevator", "motor");


    elevator.config_kF(0, 0.0);
    elevator.config_kP(0, 0.1);
    elevator.config_kI(0, 0.0);
    elevator.config_kD(0, 0.0);

    Util.configTalonSRXWithEncoder(elevator, false);

    setElevatorPercent(0);
  }

  public int getTicks() {
    return elevator.getSelectedSensorPosition();
  }

  public void zeroElevatorEncoders() {
    elevator.setSelectedSensorPosition(0);
  }

  public void setElevatorAbsolute(final double desiredInches) {     //UNTESTED PID
    final int ticksAbsolute = Util.distanceToTicks(desiredInches, RobotMap.elevatorPulleyDiameter);  
    // elevator.set(ControlMode.Position, ticksAbsolute);
    SmartDashboard.putNumber("Elevator Ticks Target", ticksAbsolute);
  }

  public void setElevatorPercent(final double percent) {
    elevator.set(ControlMode.PercentOutput, percent);
  }

  public boolean isWithinTolerance(final double desiredDegrees) {
    return Util.isWithinTolerance(getTicks(), desiredDegrees, RobotMap.elevatorTolerance);
  }

  public void periodic() {
    SmartDashboard.putNumber("Elevator Ticks", getTicks());
  }

  @Override
  public void initDefaultCommand() {

  }
}
