/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

public class OI {

  private final XboxController driver;


  public OI() {
    driver = new XboxController(RobotMap.driverID);

  }
  
  public XboxController getDriver() {
		return driver;
	}
}
