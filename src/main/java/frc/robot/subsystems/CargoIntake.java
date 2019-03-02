package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.util.Util;

public class CargoIntake extends Subsystem {

  private final WPI_TalonSRX intakePivot;
  private final WPI_TalonSRX cargoRoller;

  private final DigitalInput cargoHome;

  public CargoIntake() {
    intakePivot = new WPI_TalonSRX(RobotMap.intakePivotMotorID);
    intakePivot.setName("CargoIntake", "pivot");
    cargoRoller = new WPI_TalonSRX(RobotMap.cargoRollerMotorID);
    cargoRoller.setName("CargoIntake", "roller");
    configIntakePivot(intakePivot);
    cargoHome = new DigitalInput(RobotMap.cargoIntakeHomeID);
  }

  public void configIntakePivot(final WPI_TalonSRX motor) {
    intakePivot.setNeutralMode(NeutralMode.Brake);

    intakePivot.config_kF(0, 0.0);
    intakePivot.config_kP(0, 2.0);
    intakePivot.config_kI(0, 0.0);
    intakePivot.config_kD(0, 0.5);

    Util.configTalonSRXWithEncoder(motor, false);
  }

  public void setRoller(final double rollerPercent) {
    cargoRoller.set(ControlMode.PercentOutput, rollerPercent);
  }

  public int getTicks() {
    return intakePivot.getSelectedSensorPosition();
  }

  public boolean isWithinTolerance(final double desiredAngle) {
    return Util.isWithinTolerance(getTicks(), Util.degreesToTicks(desiredAngle), Util.degreesToTicks(RobotMap.cargoIntakeTolerance));
  }

  public void setIntakePivot(final double degrees) {
    final int ticksAbsolute = Util.degreesToTicks(degrees);
    // intakePivot.set(ControlMode.MotionMagic, ticksAbsolute);
    intakePivot.set(ControlMode.Position, ticksAbsolute);
  }

  public void setIntakePivotPercent(final double percent) {
    intakePivot.set(ControlMode.PercentOutput, percent);
  }

  public boolean isCargoHomed() {
    return cargoHome.get();
  }

  public void zeroCargoIntakePivot() {
    if (cargoHome.get()) {
      intakePivot.setSelectedSensorPosition(0);
    }
  }

  public void periodic() {
    SmartDashboard.putNumber("Intake Pivot Degrees", Util.ticksToDegrees(getTicks())); 
    zeroCargoIntakePivot();
    SmartDashboard.putBoolean("Is Cargo Homed?", isCargoHomed());
  }

  @Override
  public void initDefaultCommand() {
    
  }
}
