package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.util.Util;

public class Arm extends Subsystem {

  private final WPI_TalonSRX wrist;

  private final DigitalInput wristHome;

  public ArmSide armSide;
  public ArmSetpoint armSetpoint;

  public Arm() {
    wrist = new WPI_TalonSRX(RobotMap.armMotorID);
    wrist.setName("Arm", "wrist");
    configWrist();
    zeroWrist();

    wristHome = new DigitalInput(RobotMap.wristHomeID);

    armSetpoint = ArmSetpoint.HOME;
    armSide = ArmSide.FRONT;
  }

  public void configWrist() {
    wrist.configMotionCruiseVelocity(450); //was 400  
    wrist.configMotionAcceleration(450);   //was 400
    Util.configTalonSRXWithEncoder(wrist, false);
    wrist.config_kF(0, 4.8, 0);   
    wrist.config_kP(0, 4.7, 0);   
    wrist.config_kI(0, 0.0, 0);
    wrist.config_kD(0, 0.1, 0);
    wrist.setInverted(true);
    setBrake();
  }

  public void setWristAbsolute(final double desiredDegrees) {
    final int ticksAbsolute = Util.degreesToTicks(desiredDegrees);

    wrist.set(ControlMode.MotionMagic, ticksAbsolute); 
    // wrist.set(ControlMode.Position, ticksAbsolute);
  }

  public void setWristPercent(final double percent) {
    wrist.set(ControlMode.PercentOutput, percent);
  }

  public void setBrake() {
    wrist.setNeutralMode(NeutralMode.Brake);
  }

  public void setCoast() {
    wrist.setNeutralMode(NeutralMode.Coast);
  }

  public boolean isWithinTolerance(final double desiredDegrees) {
    return Util.isWithinTolerance(getTicks(), Util.degreesToTicks(desiredDegrees), Util.degreesToTicks(RobotMap.wristTolerance));
  }

  public void zeroWrist() {
    wrist.setSelectedSensorPosition(Util.degreesToTicks(-90));
  }

  private void zeroWristAtHome() {
    if (wristHome.get() && !Util.isWithinTolerance(getArmAngle(), 0.0, 3)) {
      zeroWrist();
    }
  }

  public double getArmAngle() {
    return Util.ticksToDegrees((int)getTicks());
  }

  public int getTicks() {
    return wrist.getSelectedSensorPosition();
  }

  @Override
  public void initDefaultCommand() {
    // setDefaultCommand(new MySpecialCommand());
  }

  public enum ArmSide {
    FRONT,
    BACK
  }

  public void setArmSide(final ArmSide newArmSide) {
    armSide = newArmSide;
  }

  public ArmSide getArmSide() { // might not be needed
    return (getArmAngle() >= 0) ? ArmSide.FRONT : ArmSide.BACK;
  }

  public ArmSide getArmSideState() {
    return armSide;
  }

  public enum ArmSetpoint {
    HATCH,
    HATCHCOLLECT,
    CARGOSHIP,
    CARGOROCKETLVL1,
    CARGOROCKETLVL2,
    CARGOCOLLECTFLOOR,
    CARGOLOADINGSTATION,
    HOME
  }

  public ArmSetpoint getArmSetpointState() {
    return armSetpoint;
  }

  public void setArmSetpoint(final ArmSetpoint newArmSetpoint) {
    armSetpoint = newArmSetpoint;
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Arm Angle", getArmAngle());
    SmartDashboard.putString("Desired Arm Setpoint", getArmSetpointState().toString());
    SmartDashboard.putString("Desired Arm Side", getArmSideState().toString());
    zeroWristAtHome();
    SmartDashboard.putBoolean("Wrist Home", wristHome.get());
  }
}