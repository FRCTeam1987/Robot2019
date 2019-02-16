package frc.robot.util;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

public class Util {

    public static void configTalonSRXWithEncoder(final WPI_TalonSRX srx, final boolean isInverted) {
        final ErrorCode errorCode = srx.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        if(errorCode == ErrorCode.OK) {
            srx.setSensorPhase(isInverted);
        }
        else {
            SmartDashboard.putString(srx.getSubsystem() + "_" + srx.getName() + "_status", errorCode.toString());
        }
    }

    public static boolean isWithinTolerance(final double value, final double target, final double tolerance){
        return value > target - Math.abs(tolerance) && value < target + Math.abs(tolerance);
    }

    public static int distanceToTicks(final double distance, final double diameter) {
        return (int)(rotationsToTicks(distanceToRotations(distance, diameter)));
    }

    public static double getEncoderRotations(final double encoderTicks) {
        return encoderTicks / RobotMap.ticksPerRotation;
    }

    public static double distanceToRotations(final double distance, final double diameter) {
        return distance / circumference(diameter);
    }

    public static double rotationsToTicks(final double rotations) {
        return (int)(rotations * RobotMap.ticksPerRotation);
    }

    public static double ticksToRotations(final int ticks) {
        return ticks / RobotMap.ticksPerRotation;
    }

    public static double ticksToDistance(final int ticks, final double diameter) {
        return rotationsToDistance(ticksToRotations(ticks), diameter);
    }

    public static double ticksToDegrees(final int ticks) {
        return ((ticks / RobotMap.ticksPerRotation) * 360);
    }

    public static double ticksToDegrees(final int ticks, final double gearReduction) {
        return ((ticks / RobotMap.ticksPerRotation) * 360) * gearReduction;
    }

    public static int degreesToTicks(final double degrees, final double gearReduction) {
        return (int) (((degrees / 360) * RobotMap.ticksPerRotation) * gearReduction);
    }

    public static double circumference(final double diameter) {
        return Math.PI * diameter;
    }

    public static double rotationsToDistance(final double rotations, final double diameter) {
        return circumference(diameter) * rotations;
    }
}
