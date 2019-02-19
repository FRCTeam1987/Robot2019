package frc.robot.commands.armavator;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.arm.SetArmAngle;
import frc.robot.subsystems.Arm.ArmSetpoint;
import frc.robot.subsystems.Arm.ArmSide;

public class HatchCollect extends CommandGroup {
 
  public HatchCollect() {
      addSequential(new SetArmAngle(ArmSetpoint.HATCHCOLLECT, ArmSide.FRONT));
  }
}
