/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import frc.robot.commands.claw.PlaceHatch;
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
  private final Button shootCargo;
  private final Button releaseHatch;
  private final Button switchClawSide;
  private final XboxDPad elevatorManualUp;
  private final XboxDPad elevatorManualDown;
  

  public OI() {
    driver = new XboxController(RobotMap.driverID);
    cargoCollect = new JoystickButton(driver, RobotMap.cargoCollectButton);
    shootCargo = new JoystickButton(driver, RobotMap.shootCargoButton);
    releaseHatch = new JoystickButton(driver, RobotMap.releaseHatchButton);
    switchClawSide = new JoystickButton(driver, RobotMap.switchClawSideButton);
    elevatorManualUp = new XboxDPad(driver, Direction.Up);
    elevatorManualDown = new XboxDPad(driver, Direction.Down);

    // elevatorManualUp.whileHeld(new ElevatorManual(0.4));
    // elevatorManualDown.whileHeld(new ElevatorManual(-0.4));
    shootCargo.whenPressed(new ShootCargo(0.8));
    releaseHatch.whenPressed(new PlaceHatch());

    // SmartDashboard.putData("drive-toggle-shifter", new ToggleShifter());
    // SmartDashboard.putData("drive-drive-distance", new DriveDistance(15.07));
    // SmartDashboard.putData("claw-place-hatch", new PlaceHatch());
    SmartDashboard.putData("claw-shoot-cargo", new ShootCargo(0.5));

    SmartDashboard.putData("Zero Elevator", new ZeroElevator());

    SmartDashboard.putData("Set Elevator 40%", new ElevatorManual(.4));

    SmartDashboard.putData("Set Elevator Absolute 1", new SetElevatorAbsolute(1));
    SmartDashboard.putData("Set Elevator Absolute 3", new SetElevatorAbsolute(3));
    SmartDashboard.putData("Set Elevator Absolute 10", new SetElevatorAbsolute(10));
    SmartDashboard.putData("Set Elevator Absolute 30", new SetElevatorAbsolute(30));

    SmartDashboard.putData("Aim Robot", new AimRobot());
    SmartDashboard.putData("Driver Camera", new AutoCamera());

    SmartDashboard.putData("Set Climber Manual 0", new ClimberManual(0));
    SmartDashboard.putData("Set Climber Manual .3", new ClimberManual(0.3));
    SmartDashboard.putData("Set Climber Manual .6", new ClimberManual(0.6));
    SmartDashboard.putData("Set Climber Manual .8", new ClimberManual(0.8));
    SmartDashboard.putData("Set Climber Manual 1.0", new ClimberManual(1.0));
  }

  public XboxController getDriver() {
		return driver;
	}
}
