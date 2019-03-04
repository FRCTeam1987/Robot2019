package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.StopAll;
import frc.robot.commands.arm.ArmManual;
import frc.robot.commands.arm.SetArmAngle;
import frc.robot.commands.armavator.FrontHatchPlace;
import frc.robot.commands.armavator.SetArmSide;
import frc.robot.commands.armavator.QuickSet;
// import frc.robot.commands.armavator.SetRobotState;
import frc.robot.commands.armavator.SetElevatorAndArm;
import frc.robot.commands.armavator.SetRobotState;
import frc.robot.commands.cargointake.CollectCargo;
import frc.robot.commands.cargointake.IntakeCargo;
import frc.robot.commands.cargointake.SetIntakeAngle;
import frc.robot.commands.cargointake.SetIntakePivotManual;
import frc.robot.commands.claw.ClawIntakeCargo;
import frc.robot.commands.claw.Place;
import frc.robot.commands.drive.ToggleShifter;
// import frc.robot.commands.drive.DriveDistance;
// import frc.robot.commands.drive.ToggleShifter;
import frc.robot.commands.elevator.ElevatorManual;
import frc.robot.commands.elevator.SetElevatorAbsolute;
import frc.robot.commands.vision.AimRobot;
import frc.robot.commands.vision.SetCameraMode;
import frc.robot.commands.vision.SetCameraPipeline;
import frc.robot.subsystems.Arm.ArmSetpoint;
import frc.robot.subsystems.Arm.ArmSide;
import frc.robot.subsystems.Elevator.ElevatorHeight;
import frc.robot.util.XboxDPad;
import frc.robot.util.limelight.CameraMode;

public class OI {


  private final XboxController driver;
  private final XboxController coDriver;

  //Driver
  private final Button goToElevatorHeight;
  private final Button shifter;
  private final Button cargoCollect;
  private final Button place;
  private final Button armManualForward;
  private final Button armManualBack;
  private final Button collectCargoFromLoadingStation;
  private final XboxDPad elevatorManualUp;
  private final XboxDPad elevatorManualDown;
  private final XboxDPad cargoIntakeManualForward;
  private final XboxDPad cargoIntakeManualBack;

  //Co-driver
  private final Button switchPotentialClawSide;
  private final Button level1HatchSet;
  private final Button level2HatchSet;
  private final Button cargoShipCargoSet;
  private final Button level1CargoSet;
  private final Button level2CargoSet;
  private final Button loadingStationCargoSet;
  private final Button stopAll;


  public OI() {
    //Driver
    driver = new XboxController(RobotMap.driverID);
    shifter = new JoystickButton(driver, RobotMap.toggleShifterButton);
    goToElevatorHeight = new JoystickButton(driver, RobotMap.elevatorToHeightButton);
    cargoCollect = new JoystickButton(driver, RobotMap.cargoCollectButton);
    place = new JoystickButton(driver, RobotMap.placeButton);
    armManualForward = new JoystickButton(driver, RobotMap.armManualForwardButton);
    armManualBack = new JoystickButton(driver, RobotMap.armManualBackButton);
    collectCargoFromLoadingStation = new JoystickButton(driver, RobotMap.collectCargoFromLoadingStationButton);
    elevatorManualUp = new XboxDPad(driver, XboxDPad.Direction.Up);
    elevatorManualDown = new XboxDPad(driver, XboxDPad.Direction.Down);
    cargoIntakeManualForward = new XboxDPad(driver, XboxDPad.Direction.Left);
    cargoIntakeManualBack = new XboxDPad(driver, XboxDPad.Direction.Right);

    //Co-Driver
    coDriver = new XboxController(RobotMap.coDriverID);
    switchPotentialClawSide = new JoystickButton(coDriver, RobotMap.switchPotentialClawSideButton);
    level1HatchSet = new JoystickButton(coDriver, RobotMap.level1HatchSetButton);
    level2HatchSet = new JoystickButton(coDriver, RobotMap.level2HatchSetButton);
    cargoShipCargoSet = new JoystickButton(coDriver, RobotMap.cargoShipCargoSetButton);
    level1CargoSet = new JoystickButton(coDriver, RobotMap.level1CargoSetButton);
    level2CargoSet = new JoystickButton(coDriver, RobotMap.level2CargoSetButton);
    loadingStationCargoSet = new JoystickButton(coDriver, RobotMap.loadingStationCargoSetButton);
    stopAll = new JoystickButton(coDriver, RobotMap.stopAllButton);

    //Driver Buttons
    goToElevatorHeight.whenPressed(new SetElevatorAndArm());
    shifter.whenPressed(new ToggleShifter());
    cargoCollect.whenPressed(new CollectCargo());
    place.whenPressed(new Place(1));
    armManualForward.whileHeld(new ArmManual(0.4));
    armManualBack.whileHeld(new ArmManual(-0.4));
    collectCargoFromLoadingStation.whenPressed(new ClawIntakeCargo(1));
    elevatorManualUp.whileHeld(new ElevatorManual(0.4));
    elevatorManualDown.whileHeld(new ElevatorManual(-0.4));
    cargoIntakeManualForward.whileHeld(new SetIntakePivotManual(0.4));
    cargoIntakeManualBack.whileHeld(new SetIntakePivotManual(-0.4));

    //Co-Driver buttons
    switchPotentialClawSide.whenPressed(new SetArmSide(ArmSide.FRONT));
    switchPotentialClawSide.whenReleased(new SetArmSide(ArmSide.BACK));
    level1HatchSet.whenPressed(new SetRobotState(ElevatorHeight.LEVEL1HATCH, ArmSetpoint.HATCH));
    level2HatchSet.whenPressed(new SetRobotState(ElevatorHeight.LEVEL2HATCH, ArmSetpoint.HATCH));
    cargoShipCargoSet.whenPressed(new SetRobotState(ElevatorHeight.CARGOSHIP, ArmSetpoint.CARGOSHIP));
    level1CargoSet.whenPressed(new SetRobotState(ElevatorHeight.LEVEL1CARGOROCKET, ArmSetpoint.CARGOROCKETLVL1));
    level2CargoSet.whenPressed(new SetRobotState(ElevatorHeight.LEVEL2CARGOROCKET, ArmSetpoint.CARGOROCKETLVL2));
    loadingStationCargoSet.whenPressed(new SetRobotState(ElevatorHeight.LOADINGSTATIONCARGO, ArmSetpoint.CARGOLOADINGSTATION));
    stopAll.whenPressed(new StopAll());

    //SmartDashboard puts
    SmartDashboard.putData("Front Hatch Place", new FrontHatchPlace());
    SmartDashboard.putData("Go to Intake Position", new SetIntakeAngle(RobotMap.cargoIntakeAngle));
    SmartDashboard.putData("Go to Intake Home", new SetIntakeAngle(RobotMap.cargoIntakeHomeAngle));
    // SmartDashboard.putData("Set camera mode to driver camera", new SetCameraMode(CameraMode.DRIVERCAMERA));
    // SmartDashboard.putData("Set camera mode to vision processing", new SetCameraMode(CameraMode.VISION));
    // SmartDashboard.putData("Set camera pipeline to 9", new SetCameraPipeline(9)); //works
    // SmartDashboard.putData("Set camera pipeline to 0", new SetCameraPipeline(0));
    // SmartDashboard.putData("Set Elevator out of Bounds", new SetElevatorAbsolute(22.1));
    SmartDashboard.putData("Set Arm Front Hatch Angle", new SetArmAngle(ArmSetpoint.HATCH, ArmSide.FRONT));
    SmartDashboard.putData("Set Arm Back Hatch Angle", new SetArmAngle(ArmSetpoint.HATCH, ArmSide.BACK));
    SmartDashboard.putData("Quick Set Front", new QuickSet());
    SmartDashboard.putData("Intake Cargo", new IntakeCargo());
    SmartDashboard.putData("Vision adjust angle to target", new AimRobot());
    SmartDashboard.putData("Set to Vision Pipeline", new SetCameraPipeline(0));
    SmartDashboard.putData("Set Camera Mode Vision", new SetCameraMode(CameraMode.VISION));
  }

  public XboxController getDriver() {
		return driver;
  }
  
  public XboxController getCoDriver() {
    return coDriver;
  }
}
