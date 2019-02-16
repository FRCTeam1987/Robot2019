/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.util.Util;

public class CargoIntake extends Subsystem {

  private final WPI_TalonSRX intakePivot;
  private final WPI_TalonSRX cargoRoller;

  private final DigitalInput cargoIntakeProx; 

  public CargoIntake() {
    intakePivot = new WPI_TalonSRX(RobotMap.intakePivotMotorID);
    intakePivot.setName("CargoIntake", "pivot");
    cargoRoller = new WPI_TalonSRX(RobotMap.cargoRollerMotorID);
    cargoRoller.setName("CargoIntake", "roller");

    cargoIntakeProx = new DigitalInput(RobotMap.cargoIntakeProxID);

    configIntakePivot(intakePivot);
    zeroCargoIntakePivot();
  }

  public void configIntakePivot(final WPI_TalonSRX motor) {
    intakePivot.configMotionAcceleration(443); //Fix value
    intakePivot.configMotionCruiseVelocity(443);
    intakePivot.setNeutralMode(NeutralMode.Brake);

    intakePivot.config_kF(0, 0.0);
    intakePivot.config_kP(0, 0.0);
    intakePivot.config_kI(0, 0.0);
    intakePivot.config_kD(0, 0.0);

    Util.configTalonSRXWithEncoder(motor, false);
  }

  public void setRoller(final double rollerPercent) {
    cargoRoller.set(ControlMode.PercentOutput, rollerPercent);
  }

  public void zeroCargoIntakePivot() {
    intakePivot.setSelectedSensorPosition(0);
  }

  public int getTicks() {
    return intakePivot.getSelectedSensorPosition();
  }

  public boolean isWithinTolerance(final double desiredAngle) {
    return Util.isWithinTolerance(getTicks(), desiredAngle, RobotMap.wristTolerance);
  }

  private int degreesToTicks(final double degrees) {
    return (int) ((degrees / 360.0) * RobotMap.ticksPerRotation);
  }

  public void setIntakePivot(final double degrees) {
    final int ticksAbsolute = degreesToTicks(degrees);
    intakePivot.set(ControlMode.MotionMagic, ticksAbsolute);
  }

  public void setIntakePivotPercent(final double percent) {
    intakePivot.set(ControlMode.PercentOutput, percent);
  }

  public boolean isCargoCollected() {
    return cargoIntakeProx.get();
  }

  @Override
  public void initDefaultCommand() {
    
  }
}
