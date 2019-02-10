package frc.team1987;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team1987.commands.claw.PlaceHatch;
import frc.team1987.commands.claw.ShootCargo;
import frc.team1987.commands.elevator.ManualElevator;
import frc.team1987.commands.elevator.SetElevatorAbsolute;
import frc.team1987.util.XboxDPad;
import frc.team1987.util.XboxDPad.Direction;

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
    shootCargo.whenPressed(new ShootCargo(0.8));
    releaseHatch.whenPressed(new PlaceHatch());

    SmartDashboard.putData("Set Elevator Absolute 3", new SetElevatorAbsolute(3));
    SmartDashboard.putData("Set Elevator Absolute 10", new SetElevatorAbsolute(10));
    SmartDashboard.putData("Set Elevator Absolute 30", new SetElevatorAbsolute(30));
  }
  
  public XboxController getDriver() {
		return driver;
	}
}
