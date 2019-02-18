package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.arm.ArmManual;
import frc.robot.commands.arm.SetArmAngle;
import frc.robot.commands.arm.ZeroArm;
import frc.robot.commands.cargointake.IntakeCargo;
import frc.robot.commands.claw.ClawIntakeCargo;
import frc.robot.commands.claw.ShootCargo;
import frc.robot.commands.climber.ClimberManual;
// import frc.robot.commands.drive.DriveDistance;
// import frc.robot.commands.drive.ToggleShifter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.elevator.ElevatorManual;
import frc.robot.commands.elevator.SetElevatorAbsolute;
import frc.robot.commands.elevator.ZeroElevator;
import frc.robot.commands.vision.AimRobot;
import frc.robot.commands.vision.AutoCamera;
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

    elevatorManualUp.whileHeld(new ElevatorManual(0.4));
    elevatorManualDown.whileHeld(new ElevatorManual(-0.4));

    armManualForward.whileHeld(new ArmManual(-0.4));
    armManualBack.whileHeld(new ArmManual(0.4));

    // SmartDashboard.putData("drive-toggle-shifter", new ToggleShifter());
    // SmartDashboard.putData("drive-drive-distance", new DriveDistance(15.07));
    // SmartDashboard.putData("claw-place-hatch", new PlaceHatch());
    SmartDashboard.putData("claw-shoot-cargo", new ShootCargo(1));

    SmartDashboard.putData("Intake Cargo", new IntakeCargo());
    SmartDashboard.putData("Claw Intake Cargo", new ClawIntakeCargo(0.8));

    // SmartDashboard.putData("Zero Elevator", new ZeroElevator());

    // SmartDashboard.putData("Set Elevator 40%", new ElevatorManual(.4));

    // SmartDashboard.putData("Set Elevator Absolute 1", new SetElevatorAbsolute(1));
    // SmartDashboard.putData("Set Elevator Absolute 3", new SetElevatorAbsolute(3));
    // SmartDashboard.putData("Set Elevator Absolute 5", new SetElevatorAbsolute(5));
    // SmartDashboard.putData("Set Elevator Absolute 10", new SetElevatorAbsolute(10));
    // SmartDashboard.putData("Set Elevator Absolute 30", new SetElevatorAbsolute(30));

    SmartDashboard.putData("Zero Arm Wrist", new ZeroArm());
    SmartDashboard.putData("Arm 0 Degrees", new SetArmAngle(0));
    // SmartDashboard.putData("Arm 45 Degrees", new SetArmAngle(45));
    // SmartDashboard.putData("Arm 90 Degrees", new SetArmAngle(90));
    // SmartDashboard.putData("Arm -45 Degrees", new SetArmAngle(-45));
    // SmartDashboard.putData("Arm -90 Degrees", new SetArmAngle(-90));



    // SmartDashboard.putData("Aim Robot", new AimRobot());
    // SmartDashboard.putData("Driver Camera", new AutoCamera());

    // SmartDashboard.putData("Set Climber Manual 0", new ClimberManual(0));
    // SmartDashboard.putData("Set Climber Manual .3", new ClimberManual(0.3));
    // SmartDashboard.putData("Set Climber Manual .6", new ClimberManual(0.6));
    // SmartDashboard.putData("Set Climber Manual .8", new ClimberManual(0.8));
    // SmartDashboard.putData("Set Climber Manual 1.0", new ClimberManual(1.0));
  }

  public XboxController getDriver() {
		return driver;
	}
}
