package frc.robot.commands.arm;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Arm.ArmSide;

public class RezeroArm extends Command {

  private ArmSide m_currentArmSide;

  public RezeroArm(final ArmSide currentArmSide) {
    requires(Robot.arm);
    m_currentArmSide = currentArmSide;
  }

  @Override
  protected void initialize() {
    if (m_currentArmSide == ArmSide.FRONT) {
      Robot.arm.setWristPercent(-0.4);
    }
    else {
      Robot.arm.setWristPercent(0.3);
    }
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return Robot.arm.getWristHome();
  }
  
  @Override
  protected void end() {
    Robot.arm.setWristPercent(0);
  }
}
