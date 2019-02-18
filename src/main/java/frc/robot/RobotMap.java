package frc.robot;


public class RobotMap {

    //Driver Buttons


    public static int elevatorToHeightButton = 1; //a
    public static int hatchCollectButton = 2; //b
    public static int cargoCollectButton = 3; //x
    public static int placeButton = 4; //y
    public static int armFrontButton = 5;
    public static int armBackButton = 6;
    public static int toggleShifterButton = 9; //left stick push
    public static int switchClawSideButton = 10; //right stick push
    
    //MotorIDs
    public static int leftMasterID = 1;  //
	public static int leftSlave1ID = 2;
	public static int leftSlave2ID = 3;
	public static int rightMasterID = 4;
	public static int rightSlave1ID = 5;
    public static int rightSlave2ID = 6;
    public static int elevatorMotorID = 7;
    public static int armMotorID = 8;           //white stripe
    public static int intakePivotMotorID = 10;   //cargo intake pivot
    public static int cargoRollerMotorID = 14;   
    public static int clawIntakeMotorID = 11;
    public static int winchMasterID = 9; // winch motor ID might change
    public static int winchSlaveID = 12;
    public static int hatchCollectMotorID = 13;

    //DIO IDs
    public static int cargoIntakeProxID = 0;
    public static int clawCargoProxID = 1;

    public static int defaultTimeout = 10;
    public static int driverID = 0;
    public static double ticksPerRotation = 4096.0;

    //Pneumatics
    public static int highGearID = 0;   //NOT REAL
    public static int lowGearID = 1;    //NOT REAL
    public static int hatchRetractID = 2;
    public static int hatchReleaseID = 3;

    //Robot Dimensions
    public static double driveBaseWheelsDiameter = 5.0; //might need to be tweaked

    //Elevator Setpoints
    public static double elevatorGroundCollectHeight;
    public static double elevatorLevel1HatchHeight;
    public static double elevatorLevel1CargoHeight;
    public static double elevatorLevel2HatchHeight;
    public static double elevatorLevel2CargoHeight;
    public static double elevatorCargoLoadingStationHeight;
    public static double elevatorFlipHeight;
    
    //Arm Setpoints
    public static double armHatchAngle;
    public static double armLoadingStationCargoAngle;
    public static double armFloorCollectCargoAngle;
    public static double armFullSendCargoAngle;


    //Elevator Misc
    public static double elevatorPulleyDiameter = 1.506;   
    public static double elevatorTolerance = 0.25;       
    public static double setElevatorAbsoluteTimeout = 60.0;   
    
    public static double wristTolerance = 10;
}