/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.Util;

public class CargoIntake extends Subsystem {

  private final WPI_TalonSRX intakePivot;
  private final WPI_TalonSRX cargoRoller;

  private final DigitalInput cargoIntakeProx; 

  public CargoIntake() {
    intakePivot = new WPI_TalonSRX(RobotMap.intakePivotMotorID);
    cargoRoller = new WPI_TalonSRX(RobotMap.cargoRollerMotorID);

    cargoIntakeProx = new DigitalInput(RobotMap.cargoIntakeProxID);

    configIntakePivot(intakePivot);
  }

  public void configIntakePivot(final WPI_TalonSRX motor) {
    intakePivot.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1);
    intakePivot.configMotionAcceleration(443); //Fix value
    intakePivot.configMotionCruiseVelocity(443);

    Util.configTalonSRXWithEncoder(motor, false);
  }

  public void setRoller(final double rollerPercent) {
    cargoRoller.set(ControlMode.PercentOutput, rollerPercent);
  }

  public int degreesToTicks(final double degrees) {
    return (int) ((degrees / 360.0) * RobotMap.ticksPerRotation);
  }

  public void setIntakePivot(final double degrees) {
    final int ticksAbsolute = degreesToTicks(degrees);
    intakePivot.set(ControlMode.MotionMagic, ticksAbsolute);
  }

  public boolean isCargoCollected() {
    return cargoIntakeProx.get();
  }

  @Override
  public void initDefaultCommand() {
    
  }
}
