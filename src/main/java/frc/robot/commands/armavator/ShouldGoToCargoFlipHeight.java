package frc.robot.commands.armavator;

import edu.wpi.first.wpilibj.command.ConditionalCommand;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.elevator.GoToElevatorHeight;
import frc.robot.subsystems.Elevator.ElevatorHeight;

public class ShouldGoToCargoFlipHeight extends ConditionalCommand {

    public ShouldGoToCargoFlipHeight() {
        super(new GoToElevatorHeight(ElevatorHeight.QUICKCARGOFLIP));
    }

    @Override
    protected boolean condition() {
        return Robot.claw.isCargoCollected() && (Robot.elevator.getElevatorHeightStateInches() < RobotMap.elevatorQuickCargoFlipHeight) && Robot.arm.getArmSide() != Robot.arm.getArmSideState();
    }
}
