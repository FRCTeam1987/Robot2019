package frc.robot.commands.arm;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.subsystems.Arm.ArmSetpoint;

public class SetArmPosition extends InstantCommand {

  private ArmSetpoint m_armAngle;

  public SetArmPosition(final ArmSetpoint armAngle) {
    super();
    m_armAngle = armAngle;
    requires(Robot.arm);
  }

  @Override
  protected void initialize() {
    Robot.arm.setArmSetpoint(m_armAngle);
  }

}
