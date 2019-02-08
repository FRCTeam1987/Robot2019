/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


public class RobotMap {
    //MotorIDs
    public static int leftMasterID = 1;
	public static int leftSlave1ID = 2;
	public static int leftSlave2ID = 3;
	public static int rightMasterID = 4;
	public static int rightSlave1ID = 5;
    public static int rightSlave2ID = 6;
    public static int elevatorMotorID = 7;
    public static int intakePivotMotorID = 8;
    public static int cargoRollerMotorID = 9;

    //MiscIDs

    //DIO IDs
    public static int cargoIntakeProxID = 0;

    public static int defaultTimeout = 10;
    public static int driverID = 0;
    public static int ticksPerRotation = 4096;

    //ElevatorIDs
    public static double elevatorShaftDiameter = 2.0;   //NOT REAL NEEDS TO BE CHANGED
    public static double elevatorTolerance = 1.0;       //NOT REAL NEEDS TO BE CHANGED
    public static double setElevatorAbsoluteTimeout = 2.0;   //NOT REAL NEEDS TO BE CHANGED
}