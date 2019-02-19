package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.util.Util;

public class Elevator extends Subsystem {

  private final WPI_TalonSRX elevator;    
  private ElevatorHeight elevatorHeight;
  private final DigitalInput elevatorHome;
  private final DigitalInput elevatorMax;
  private final DigitalInput elevatorMin;

  public Elevator() {
    elevator = new WPI_TalonSRX(RobotMap.elevatorMotorID);
    elevator.setName("Elevator", "winch");
   
    elevatorHeight = ElevatorHeight.HOME;

    elevatorHome = new DigitalInput(RobotMap.elevatorHomeID);
    elevatorMax = new DigitalInput(RobotMap.elevatorMaxID);
    elevatorMin = new DigitalInput(RobotMap.elevatorMinID);

    configElevator(elevator);
    zeroElevator();
  }

  public void configElevator(final WPI_TalonSRX motor) {
    elevator.configFactoryDefault();
    // elevator.configMotionAcceleration(443); //change once we know gearing reduction
    // elevator.configMotionCruiseVelocity(443); //this too


    elevator.setInverted(true);
    elevator.config_kF(0, 0.0);
    elevator.config_kP(0, 0.4);
    elevator.config_kI(0, 0.0);
    elevator.config_kD(0, 0.3);

    Util.configTalonSRXWithEncoder(elevator, false);
  }

  public double getInches() {
    return Util.ticksToDistance(getTicks(), RobotMap.elevatorPulleyDiameter);
  }

  public int getTicks() {
    return elevator.getSelectedSensorPosition();
  }

  public void zeroElevator() {
    elevator.setSelectedSensorPosition(0);
  }

  public void zeroElevatorAtHome() { //not tested
    if (elevatorHome.get() && Util.isWithinTolerance(getInches(), RobotMap.elevatorHomeHeight, 0.2)) {  //double check and change to robot map
      zeroElevator();
    }
  }

  public boolean isAtMax() {         //not tested
    return elevatorMax.get();
  }

  // public boolean isAtMin() {         //not tested
  //   return elevatorMin.get();
  // }

  public boolean isAtLimit() {
    // return (isAtMax() || isAtMin()); 
    return isAtMax(); //temp until we get min tach
  }

  public boolean isCurrentPositionOutOfRange() {
    return isPositionOutOfRange(getInches());
  }

  public void stopWhenOutOfRange() {
    if (isAtLimit() || isCurrentPositionOutOfRange()) {
      SmartDashboard.putBoolean("Elevator In Range", false);
      setElevatorPercent(0.0);
    }
    else {
      SmartDashboard.putBoolean("Elevator In Range", true);
    }
  }

  public boolean isPositionOutOfRange(final double desiredInches) {    //not tested
    return desiredInches > RobotMap.elevatorMax || desiredInches < RobotMap.elevatorMin;  //need to define max and min in robotmap
  }

  public void setElevatorAbsolute(final double desiredInches) {
    final int ticksAbsolute = Util.distanceToTicks(desiredInches, RobotMap.elevatorPulleyDiameter);  

    if (isPositionOutOfRange(desiredInches)) {        //not tested
      SmartDashboard.putBoolean("Request In Bounds", false);
      return;
    }
    else {
      SmartDashboard.putBoolean("Request In Bounds", true);
    }

    elevator.set(ControlMode.Position, ticksAbsolute);
  }

  public void setElevatorPercent(final double percent) {
    elevator.set(ControlMode.PercentOutput, percent);
  }

  public boolean isWithinTolerance(final double targetInches) {
    return Util.isWithinTolerance(getInches(), targetInches, RobotMap.elevatorTolerance);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Elevator Inches", getInches());
    SmartDashboard.putString("Desired Elevator Height", getElevatorHeight().toString());
    SmartDashboard.putBoolean("Max elevator tripped", elevatorMax.get());
    stopWhenOutOfRange();
    zeroElevatorAtHome();
  }

  @Override
  public void initDefaultCommand() {
  }

  public enum ElevatorHeight {
    CARGOGROUNDCOLLECT,
    LEVEL1HATCH,
    CARGOSHIP,
    LEVEL1CARGOROCKET,
    LEVEL2HATCH,
    LEVEL2CARGOROCKET,
    LOADINGSTATIONCARGO,
    FLIP,
    HOME
  }

  public void setElevatorHeight(final ElevatorHeight newElevatorHeight) {
    elevatorHeight = newElevatorHeight;
  }

  public ElevatorHeight getElevatorHeight() {
    return elevatorHeight;
  }
}
