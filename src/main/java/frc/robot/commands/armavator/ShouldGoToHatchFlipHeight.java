package frc.robot.commands.armavator;

import edu.wpi.first.wpilibj.command.ConditionalCommand;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.elevator.GoToElevatorHeight;
import frc.robot.subsystems.Elevator.ElevatorHeight;

public class ShouldGoToHatchFlipHeight extends ConditionalCommand {

    public ShouldGoToHatchFlipHeight() {
        super(new GoToElevatorHeight(ElevatorHeight.QUICKHATCHFLIP));
    }

    @Override
    protected boolean condition() {
        return Robot.claw.isHatchCollected() && (Robot.elevator.getElevatorHeightStateInches() < RobotMap.elevatorQuickHatchFlipHeight) && Robot.arm.getArmSide() != Robot.arm.getArmSideState();
    }
}
