package frc.robot;


public class RobotMap {

    //Driver Buttons
    public static int releaseHatchButton = 1;
    public static int stopAll = 2;
    public static int shootCargoButton = 3;
    public static int cargoCollectButton = 4;
    public static int toggleShifterButton = 9;
    public static int switchClawSideButton = 10;
    
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
    public static int armMotorID = 10;
    public static int clawIntakeMotorID = 11;

    //Misc
    public static double driveBaseWheelsDiameter = 5.0; //might need to be tweaked

    //DIO IDs
    public static int cargoIntakeProxID = 0;
    public static int clawCargoProxID = 1;
    public static int clawHatchProxTopID = 2;
    public static int clawHatchProxBottomID = 3;

    public static int defaultTimeout = 10;
    public static int driverID = 0;
    public static int ticksPerRotation = 4096;

    //Pneumatics
    public static int highGearID = 0;   //NOT REAL
    public static int lowGearID = 1;    //NOT REAL
    public static int hatchRetractID = 2;
    public static int hatchReleaseID = 3;

    //Elevator
    public static double elevatorShaftDiameter = 2.0;   //NOT REAL NEEDS TO BE CHANGED
    public static double elevatorTolerance = 1.0;       //NOT REAL NEEDS TO BE CHANGED
    public static double setElevatorAbsoluteTimeout = 2.0;   //NOT REAL NEEDS TO BE CHANGED
    public static double wristTolerance = 100;
    public static double wristGearboxReduction = 4.444444;

}