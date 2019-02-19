package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.arm.ArmManual;
import frc.robot.commands.arm.SetArmAngle;
import frc.robot.commands.arm.ZeroArm;
import frc.robot.commands.armavator.FrontHatchPlace;
import frc.robot.commands.armavator.HatchCollect;
import frc.robot.commands.armavator.SetElevatorAndArm;
import frc.robot.commands.cargointake.CollectCargo;
import frc.robot.commands.cargointake.IntakeCargo;
import frc.robot.commands.cargointake.SetIntakeAngle;
import frc.robot.commands.cargointake.SetIntakePivotManual;
import frc.robot.commands.cargointake.ZeroCargoIntakePivot;
import frc.robot.commands.claw.ClawIntakeCargo;
import frc.robot.commands.claw.Place;
import frc.robot.commands.claw.ShootCargo;
import frc.robot.commands.climber.ClimberManual;
// import frc.robot.commands.drive.DriveDistance;
// import frc.robot.commands.drive.ToggleShifter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.elevator.ElevatorManual;
import frc.robot.commands.elevator.GoToElevatorHeight;
import frc.robot.commands.elevator.SetElevatorAbsolute;
import frc.robot.commands.elevator.ZeroElevator;
import frc.robot.commands.vision.AimRobot;
import frc.robot.commands.vision.AutoCamera;
import frc.robot.subsystems.Arm.ArmSetpoint;
import frc.robot.subsystems.Arm.ArmSide;
import frc.robot.subsystems.Elevator.ElevatorHeight;
import frc.robot.util.XboxDPad;
import frc.robot.util.XboxDPad.Direction;

public class OI {

  private final XboxController driver;

  private final Button cargoCollect;
  private final Button hatchCollect;
  private final Button place;
  private final Button switchClawSide;
  private final XboxDPad elevatorManualUp;
  private final XboxDPad elevatorManualDown;
  private final Button armManualForward;
  private final Button armManualBack;
  private final XboxDPad cargoIntakeManualForward;
  private final XboxDPad cargoIntakeManualBack;

  public OI() {
    driver = new XboxController(RobotMap.driverID);
    cargoCollect = new JoystickButton(driver, RobotMap.cargoCollectButton);
    hatchCollect = new JoystickButton(driver, RobotMap.hatchCollectButton);
    place = new JoystickButton(driver, RobotMap.placeButton);
    switchClawSide = new JoystickButton(driver, RobotMap.switchClawSideButton);
    elevatorManualUp = new XboxDPad(driver, XboxDPad.Direction.Up);
    elevatorManualDown = new XboxDPad(driver, XboxDPad.Direction.Down);
    armManualForward = new JoystickButton(driver, RobotMap.armFrontButton);
    armManualBack = new JoystickButton(driver, RobotMap.armBackButton);
    cargoIntakeManualForward = new XboxDPad(driver, XboxDPad.Direction.Left);
    cargoIntakeManualBack = new XboxDPad(driver, XboxDPad.Direction.Right);

    elevatorManualUp.whileHeld(new ElevatorManual(0.4));
    elevatorManualDown.whileHeld(new ElevatorManual(-0.4));

    armManualForward.whileHeld(new ArmManual(-0.4));
    armManualBack.whileHeld(new ArmManual(0.4));

    hatchCollect.whenPressed(new HatchCollect());

    cargoIntakeManualForward.whileHeld(new SetIntakePivotManual(0.4));
    cargoIntakeManualBack.whileHeld(new SetIntakePivotManual(-0.4));

    cargoCollect.whenPressed(new CollectCargo());
    place.whenPressed(new Place(1));



    // SmartDashboard.putData("drive-toggle-shifter", new ToggleShifter());
    // SmartDashboard.putData("drive-drive-distance", new DriveDistance(15.07));
    // SmartDashboard.putData("claw-place-hatch", new PlaceHatch());
    // SmartDashboard.putData("claw-shoot-cargo", new ShootCargo(1));

    // SmartDashboard.putData("Intake Cargo", new IntakeCargo());
    // SmartDashboard.putData("Claw Intake Cargo", new ClawIntakeCargo(0.8));

    // SmartDashboard.putData("Zero Elevator", new ZeroElevator());
    // SmartDashboard.putData("Zero Cargo Intake", new ZeroCargoIntakePivot());

    // SmartDashboard.putData("Go to Intake Angle", new SetIntakeAngle(RobotMap.cargoIntakeAngle));
    // SmartDashboard.putData("Go to Intake Home", new SetIntakeAngle(RobotMap.cargoIntakeHomeAngle));

    SmartDashboard.putData("Front Hatch Place Set", new FrontHatchPlace());
    // SmartDashboard.putData("Go to Flip Height", new GoToElevatorHeight(ElevatorHeight.FLIP));
    SmartDashboard.putData("Collect Hatch", new HatchCollect());

    // SmartDashboard.putData("Go To Back Level 1 Hatch Height", new SetElevatorAndArm(ArmSide.BACK, ElevatorHeight.LEVEL1HATCH, ArmSetpoint.HATCH));
    SmartDashboard.putData("Go To Front Level 1 Hatch Height", new SetElevatorAndArm(ArmSide.FRONT, ElevatorHeight.LEVEL1HATCH, ArmSetpoint.HATCH));
    SmartDashboard.putData("Go To Front Level 2 Hatch Height", new SetElevatorAndArm(ArmSide.FRONT, ElevatorHeight.LEVEL2HATCH, ArmSetpoint.HATCH));
    // SmartDashboard.putData("Go To Back Level 2 Hatch  Height", new SetElevatorAndArm(ArmSide.BACK, ElevatorHeight.LEVEL2HATCH, ArmSetpoint.HATCH));
    SmartDashboard.putData("Go To Front Level 2 Cargo Height", new SetElevatorAndArm(ArmSide.FRONT, ElevatorHeight.LEVEL2CARGOROCKET, ArmSetpoint.CARGOROCKETLVL2));
    SmartDashboard.putData("Go To Front Level 1 Cargo Height", new SetElevatorAndArm(ArmSide.FRONT, ElevatorHeight.LEVEL1CARGOROCKET, ArmSetpoint.CARGOROCKETLVL1));
    SmartDashboard.putData("Go To Cargo Ship Cargo Height", new SetElevatorAndArm(ArmSide.FRONT, ElevatorHeight.CARGOSHIP, ArmSetpoint.CARGOSHIP));


    SmartDashboard.putData("Full Send Cargo", new ShootCargo(1));
    SmartDashboard.putData("Collect Cargo", new CollectCargo());

    // SmartDashboard.putData("Go To Elevator Home", new GoToElevatorHeight(ElevatorHeight.HOME));
    // SmartDashboard.putData("Set Elevator Absolute 1", new SetElevatorAbsolute(1));
    // SmartDashboard.putData("Set Elevator Absolute 3", new SetElevatorAbsolute(3));
    // SmartDashboard.putData("Set Elevator Absolute 5 Inches", new SetElevatorAbsolute(5));
    // SmartDashboard.putData("Set Elevator Absolute 10 Inches", new SetElevatorAbsolute(10));
    // SmartDashboard.putData("Set Elevator Absolute 30", new SetElevatorAbsolute(30));

    // SmartDashboard.putData("Set Arm Angle 0 Degrees", new SetArmAngle(ArmSetpoint.HOME, ArmSide.FRONT));
    // SmartDashboard.putData("Set Arm Angle Hatch Front", new SetArmAngle(ArmSetpoint.HATCH, ArmSide.FRONT));
    // SmartDashboard.putData("Set Arm Angle Hatch Back", new SetArmAngle(ArmSetpoint.HATCH, ArmSide.BACK));

    SmartDashboard.putData("Zero Arm Wrist", new ZeroArm());
  }

  public XboxController getDriver() {
		return driver;
	}
}
