package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.SetRumble;
import frc.robot.commands.StopAll;
import frc.robot.commands.arm.ArmManual;
import frc.robot.commands.armavator.SetElevatorAndArm;
import frc.robot.commands.armavator.SmartSetRobotState;
import frc.robot.commands.cargointake.CollectCargo;
import frc.robot.commands.cargointake.IntakeCargo;
import frc.robot.commands.cargointake.SetIntakePivotManual;
import frc.robot.commands.claw.ToggleHatchManipulator;
import frc.robot.commands.claw.Place;
import frc.robot.commands.climber.ClimberManual;
import frc.robot.commands.climber.EngageVacuum;
import frc.robot.commands.drive.ToggleShifter;
import frc.robot.commands.elevator.ElevatorManual;
import frc.robot.commands.vision.DriveByAssist;
import frc.robot.subsystems.Elevator.ElevatorHeight;
import frc.robot.util.XboxDPad;

public class OI {

  private final XboxController driver;
  private final XboxController xboxCoDriver;

  // Driver
  private final Button goToElevatorHeight;
  private final Button shifter;
  private final Button cargoCollect;
  private final Button place;
  private final Button armManualForward;
  private final Button armManualBack;
  private final Button aimRobot;
  private final Button collectHatch;
  private final XboxDPad elevatorManualUp;
  private final XboxDPad elevatorManualDown;
  private final XboxDPad cargoIntakeManualForward;
  private final XboxDPad cargoIntakeManualBack;

  // Co-driver
  private final Button stopAll;
  private final Button deployClimber;
  private final Button climb;
  private final Button engageSuction;

  // Xbox Co-driver
  private final XboxDPad level1Set;
  private final XboxDPad level2Set;
  private final XboxDPad cargoshipSet;

  public OI() {
    // Driver
    driver = new XboxController(RobotMap.driverID);
    goToElevatorHeight = new JoystickButton(driver, RobotMap.elevatorToHeightButton);
    cargoCollect = new JoystickButton(driver, RobotMap.cargoCollectButton);
    place = new JoystickButton(driver, RobotMap.placeButton);
    armManualForward = new JoystickButton(driver, RobotMap.armManualForwardButton);
    armManualBack = new JoystickButton(driver, RobotMap.armManualBackButton);
    shifter = new JoystickButton(driver, RobotMap.toggleShifterButton);
    aimRobot = new JoystickButton(driver, RobotMap.aimRobotButton);
    collectHatch = new JoystickButton(driver, RobotMap.collectHatchButton);
    elevatorManualUp = new XboxDPad(driver, XboxDPad.Direction.Up);
    elevatorManualDown = new XboxDPad(driver, XboxDPad.Direction.Down);
    cargoIntakeManualForward = new XboxDPad(driver, XboxDPad.Direction.Left);
    cargoIntakeManualBack = new XboxDPad(driver, XboxDPad.Direction.Right);

    // Xbox Co-driver
    xboxCoDriver = new XboxController(RobotMap.xboxCoDriverID);
    level1Set = new XboxDPad(xboxCoDriver, XboxDPad.Direction.Down);
    level2Set = new XboxDPad(xboxCoDriver, XboxDPad.Direction.Up);
    cargoshipSet = new XboxDPad(xboxCoDriver, XboxDPad.Direction.Right);
    stopAll = new JoystickButton(xboxCoDriver, RobotMap.stopAllButton);
    deployClimber = new JoystickButton(xboxCoDriver, RobotMap.deployClimberButton);
    climb = new JoystickButton(xboxCoDriver, RobotMap.climbButton);
    engageSuction = new JoystickButton(xboxCoDriver, RobotMap.engageSuctionButton);

    // Driver Buttons
    goToElevatorHeight.whenPressed(new SetElevatorAndArm());
    // collectHatch.whenPressed(new ManipulateHatch());
    collectHatch.whenPressed(new ToggleHatchManipulator());
    shifter.whenPressed(new ToggleShifter());
    // cargoCollect.whenPressed(new ManipulateHatch());
    cargoCollect.whenPressed(new CollectCargo());
    place.whenPressed(new Place(1));
    armManualForward.whileHeld(new ArmManual(0.4));
    armManualBack.whileHeld(new ArmManual(-0.4));
    aimRobot.whileHeld(new DriveByAssist());
    elevatorManualUp.whileHeld(new ElevatorManual(0.7));
    elevatorManualDown.whileHeld(new ElevatorManual(-0.7));
    cargoIntakeManualForward.whileHeld(new SetIntakePivotManual(0.6)); // was .4
    cargoIntakeManualBack.whileHeld(new SetIntakePivotManual(-0.6)); // was -.4

    // Co-Driver buttons
    // switchPotentialClawSide.whenPressed(new SetArmSide(ArmSide.FRONT));
    // switchPotentialClawSide.whenReleased(new SetArmSide(ArmSide.BACK));
    // level1HatchSet.whenPressed(new SetRobotState(ElevatorHeight.LEVEL1HATCH, ArmSetpoint.HATCH));
    // level2HatchSet.whenPressed(new SetRobotState(ElevatorHeight.LEVEL2HATCH, ArmSetpoint.HATCH));
    // cargoShipCargoSet.whenPressed(new SetRobotState(ElevatorHeight.CARGOSHIP, ArmSetpoint.CARGOSHIP));
    // level1CargoSet.whenPressed(new SetRobotState(ElevatorHeight.LEVEL1CARGOROCKET, ArmSetpoint.CARGOROCKETLVL1));
    // level2CargoSet.whenPressed(new SetRobotState(ElevatorHeight.LEVEL2CARGOROCKET, ArmSetpoint.CARGOROCKETLVL2));
    // loadingStationCargoSet.whenPressed(new SetRobotState(ElevatorHeight.LOADINGSTATIONCARGO, ArmSetpoint.CARGOLOADINGSTATION));
    // defenseSet.whenPressed(new SetRobotState(ElevatorHeight.HOME, ArmSetpoint.HOME));
    // yeetOntoHab.whenPressed(new SetRobotState(ElevatorHeight.FULLSENDLVL2, ArmSetpoint.FULLSENDLVL2));
    // stopAll.whenPressed(new StopAll());
    // goToDefense.whenPressed(new GoToDefense());

    // Xbox Co-driver buttons
    level1Set.whenPressed(new SmartSetRobotState(ElevatorHeight.LEVEL1));
    level2Set.whenPressed(new SmartSetRobotState(ElevatorHeight.LEVEL2));
    cargoshipSet.whenPressed(new SmartSetRobotState(ElevatorHeight.CARGOSHIP));
    stopAll.whenPressed(new StopAll());
    deployClimber.whileHeld(new ClimberManual(-1));
    climb.whileHeld(new ClimberManual(1));    
    engageSuction.whenPressed(new EngageVacuum());

    // SmartDashboard puts
    // SmartDashboard.putData("Re-zero Arm: Above Zero Sensor", new
    // RezeroArm(ArmSide.FRONT));
    // SmartDashboard.putData("Re-zero Arm: Below Zero Sensor", new
    // RezeroArm(ArmSide.BACK));

    // SmartDashboard.putData("Set Arm Angle Hatch", new
    // SetArmAngle(ArmSetpoint.HATCH, ArmSide.FRONT));
    // SmartDashboard.putData("Set Elevator Hatch Height", new
    // GoToElevatorHeight(ElevatorHeight.LEVEL1HATCH));
    SmartDashboard.putData("Bring Climber Carraige Up", new ClimberManual(0.8));
    SmartDashboard.putData("Bring Climber Carraige Down", new ClimberManual(-0.8));
    // SmartDashboard.putData("Aim Robot Automagically", new AutoAimbot(120));
    SmartDashboard.putData("Intake Cargo", new IntakeCargo());
    // SmartDashboard.putData("Pivot 90 Degrees", new DrivePivot(90));
    // SmartDashboard.putData("Drive 1 Foot", new DriveDistance(1));

    // SmartDashboard.putData("claw-hatch-collect", new CollectHatch());
    // SmartDashboard.putData("claw-hatch-place", new PlaceHatch());
    // SmartDashboard.putData("Set Arm -45", new SetArmAngleWithDegree(-45.0));
    // SmartDashboard.putData("Set Arm 45", new SetArmAngleWithDegree(45.0));
    // SmartDashboard.putData("Set Arm 90", new SetArmAngleWithDegree(90.0));
    // SmartDashboard.putData("Set Arm 120", new SetArmAngleWithDegree(120.0));

    // SmartDashboard.putData("cargo-intake-collect", new SetIntakeAngle(RobotMap.cargoIntakeAngle));
    // SmartDashboard.putData("cargo-intake-home", new SetIntakeAngle(RobotMap.cargoIntakeHomeAngle));

    // SmartDashboard.putData("claw-new-place-hatch", new NewPlaceHatch());
    // SmartDashboard.putData("claw-new-collect-hatch", new NewCollectHatch());
    // SmartDashboard.putData("claw-manipulate-hatch", new ManipulateHatch());

    // SmartDashboard.putData("Set Intake to Home", new SetIntakeAngle(RobotMap.cargoIntakeHomeAngle));
    // SmartDashboard.putData("Set Intake Out", new SetIntakeAngle(RobotMap.cargoIntakeAngle));

    // SmartDashboard.putData("Move Servo 180", new ServoValve(180));
    // SmartDashboard.putData("Move Servo 0", new ServoValve(0));
    // SmartDashboard.putData("Move Servo 90", new ServoValve(90));

    SmartDashboard.putData("Engage Venturi", new EngageVacuum());

    // SmartDashboard.putData("cargo-intake-off-floor", new CollectCargoFromGround());
    SmartDashboard.putData("Rumble Controller", new SetRumble(2));
    



  }

  public XboxController getDriver() {
		return driver;
  }
  
  public XboxController getCoDriver() {
    return xboxCoDriver;
  }

  public double getThrottle() {
    return driver.getTriggerAxis(Hand.kRight);
  }

  public void rumbleDriver(final int shouldRumble) {
    driver.setRumble(RumbleType.kLeftRumble, shouldRumble);
    driver.setRumble(RumbleType.kRightRumble, shouldRumble);
  }
}
