package frc.robot;


public class RobotMap {

    //Driver Buttons
    public static int driverID = 0;
    public static int elevatorToHeightButton = 1; //a
    public static int hatchCollectButton = 2; //b
    public static int cargoCollectButton = 3; //x
    public static int placeButton = 4; //y
    public static int armFrontButton = 6;
    public static int armBackButton = 5;
    public static int toggleShifterButton = 9; //left stick push

    //Co-driver Buttons
    public static int coDriverID = 1;
    public static int switchPotentialClawSideButton = 13;
    public static int level1HatchSetButton = 8;
    public static int level2HatchSetButton = 14;
    public static int cargoShipCargoSetButton = 9;
    public static int level1CargoSetButton = 15;
    public static int level2CargoSetButton = 16;
    public static int loadingStationCargoSetButton = 10;

    
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
    public static int clawCargoProxID = 1;
    public static int wristHomeID = 2;
    public static int cargoIntakeHomeID = 3;
    public static int elevatorMaxID = 4;
    public static int elevatorHomeID = 5;
    public static int elevatorMinID = 6;

    public static int defaultTimeout = 10;
    public static double ticksPerRotation = 4096.0;

    //Pneumatics
    public static int highGearID = 0;
    public static int lowGearID = 1;
    public static int hatchRetractID = 2;
    public static int hatchReleaseID = 3;

    //Robot Dimensions
    public static double driveBaseWheelsDiameter = 5.0; //might need to be tweaked

    //Elevator Setpoints
    public static double elevatorGroundCollectHeight = -1.5;
    public static double elevatorHomeHeight = 0;
    public static double elevatorLevel1HatchHeight = -9.0;
    public static double elevatorLevel1CargoHeight = -0.6;
    public static double elevatorCargoShipHeight = 20;
    public static double elevatorLevel2HatchHeight = 21.1;
    public static double elevatorLevel2CargoHeight = 19.5;
    public static double elevatorCargoLoadingStationHeight = 0;
    public static double elevatorFlipHeight = 15;
    public static double elevatorHatchCollectUp = -5.0;
    
    //Arm Setpoints
    public static double armHomeAngle = 0;
    public static double armHatchCollectedAngle = 70;
    public static double armHatchAngle = 90;
    public static double armCargoShipAngle = 105;
    public static double armRocketLevel1CargoAngle = 90;
    public static double armRocketLevel2CargoAngle = 73;
    public static double armLoadingStationCargoAngle = 90;
    public static double armFloorCollectCargoAngle = 125;
    public static double armFullSendCargoAngle = 30;

    //Cargo Intake Pivot Setpoints
    public static double cargoIntakeAngle = 97;
    public static double cargoIntakeHomeAngle = 0;

    public static double cargoIntakeTolerance = 0.5;


    //Elevator Misc
    public static double elevatorPulleyDiameter = 1.506;   
    public static double elevatorTolerance = 1;       
    public static double setElevatorAbsoluteTimeout = 60.0; 
    public static double elevatorMax = 21.5;
    public static double elevatorMin = -20.0;   //NEED TO DEFINE  
    
    public static double wristTolerance = 5;
}