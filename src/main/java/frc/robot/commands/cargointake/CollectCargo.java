package frc.robot.commands.cargointake;

import edu.wpi.first.wpilibj.command.ConditionalCommand;
import frc.robot.Robot;
import frc.robot.subsystems.Elevator.ElevatorHeight;

public class CollectCargo extends ConditionalCommand {

    public CollectCargo() {
        super(new CollectCargoFromLoadingStation(), new CollectCargoFromGround());
    }

    protected boolean condition() {
        return Robot.elevator.getElevatorHeightState() == ElevatorHeight.LOADINGSTATIONCARGO;
    }
}
