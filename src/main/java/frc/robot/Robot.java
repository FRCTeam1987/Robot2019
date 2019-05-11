package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.auto.SandstormHatch;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.CargoIntake;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Vision;
import frc.robot.util.limelight.LedMode;


public class Robot extends TimedRobot {
  public static final Drive drive = new Drive();
  public static final Elevator elevator = new Elevator();
  public static final Arm arm = new Arm();
  public static final CargoIntake cargoIntake = new CargoIntake();
  public static final Claw claw = new Claw();
  public static final Climber climber = new Climber();     
  public static final Vision vision = new Vision();
  
  public static OI m_oi;

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  @Override
  public void robotInit() {
    m_oi = new OI();
    m_chooser.setDefaultOption("Level 2 Hatch Place", new SandstormHatch());
    // m_chooser.addOption("Drive Forward 5 feet", new DriveDistance(5));
    m_chooser.addOption("Nothing! (for rezeroing)", null);
    SmartDashboard.putData("Auto mode", m_chooser);
    Robot.drive.ahrsReset();
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void disabledInit() {
    Robot.vision.limeFront.setLedMode(LedMode.OFF);
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }

    Robot.vision.setDriverCameraMode();
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    Robot.vision.setDriverCameraMode();
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void testPeriodic() {
  }
}