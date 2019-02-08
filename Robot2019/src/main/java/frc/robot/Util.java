/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Util {

    public static void configTalonSRXWithEncoder(final WPI_TalonSRX master) {
        final ErrorCode errorCode = master.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    
        SmartDashboard.putString(master.getSubsystem() + "_" + master.getName() + "_status", errorCode.toString());
    
        master.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1);   //need default timeout?
    }

    public static boolean isWithinTolerance(final double value, final double target, final double tolerance){
        return value > target - Math.abs(tolerance) && value < target + Math.abs(tolerance);
    }

    public static int distanceToTicks(final double distance, final double diameter) {       //NEED TO FINISH
        return 1;
    }

    public static double ticksToRotations(final double encoderTicks) {
        return encoderTicks / RobotMap.ticksPerRotation;
    }
}
