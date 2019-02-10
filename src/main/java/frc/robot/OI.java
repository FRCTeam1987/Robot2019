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

    elevatorManualUp.whileHeld(new ManualElevator(0.4));
    elevatorManualDown.whileHeld(new ManualElevator(-0.4));
  }

  public XboxController getDriver() {
		return driver;
	}
}
