package frc.robot;


public class RobotMap {

    //Driver Buttons
    public static int driverID = 0;
    public static int elevatorToHeightButton = 1; //a
    public static int cargoCollectButton = 3; //x
    public static int placeButton = 4; //y
    // public static int prepClimbButton = 5; //succ
    // public static int climbButton = 6;
    public static int armManualForwardButton = 5; //Temp buttons for systems test
    public static int armManualBackButton = 6;
    public static int toggleShifterButton = 9; //left stick push
    public static int aimHatchButton = 10; 
    public static int collectCargoFromLoadingStationButton = 2;

    //Co-driver Buttons
    public static int coDriverID = 1;
    public static int switchPotentialClawSideButton = 13;
    public static int level1HatchSetButton = 8;
    public static int level2HatchSetButton = 14;
    public static int cargoShipCargoSetButton = 9;
    public static int level1CargoSetButton = 15;
    public static int level2CargoSetButton = 16;
    public static int loadingStationCargoSetButton = 10;
    public static int homeSetButton = 17;
    public static int stopAllButton = 11; //change ID

    //Drive
    public static double maxHighGearVelocity = 14;

    //Path Turning Tuning
    public static double pathGP = 0.037;
    public static double pathGD = 0.0;
    
    //Drive PID
    public static double pathHighGearKP = 0.9;
    public static double pathHighGearKI = 0.0;
    public static double pathHighGearKD = 0.0;
    public static double pathHighGearKV = 1 / maxHighGearVelocity;
    public static double pathHighGearKA = 0.0;

    
    //MotorIDs
    public static int leftMasterID = 1;         //Yellow
	public static int leftSlave1ID = 2;         //Grey
	public static int leftSlave2ID = 3;         //White
	public static int rightMasterID = 4;        //Pink
	public static int rightSlave1ID = 5;        //Blue
    public static int rightSlave2ID = 6;        //Purple
    public static int elevatorMotorID = 7;      //Toad
    public static int armMotorID = 8;           //Zebra
    public static int intakePivotMotorID = 10;  //Red               cargo intake pivot
    public static int cargoRollerMotorID = 14;  //Pink Stripe
    public static int clawIntakeMotorID = 11;   //Bumble Bee
    public static int winchMasterID = 9;        //Green
    public static int winchSlaveID = 12;        //Polka Dot Dolphin
    public static int hatchCollectMotorID = 13; //Purple Stripe

    //DIO IDs
    public static int clawCargoProxID = 1;
    public static int clawHatchProxID = 7;
    public static int wristHomeID = 2;
    public static int cargoIntakeHomeID = 3;
    public static int elevatorMaxID = 4;
    public static int elevatorHomeID = 5;
    public static int elevatorMinID = 6;

    public static int defaultTimeout = 10;
    public static double ticksPerRotation = 4096;

    //Pneumatics
    public static int highGearID = 0;
    public static int lowGearID = 1;
    public static int hatchRetractID = 2;
    public static int hatchReleaseID = 3;

    //Robot Dimensions
    public static double driveBaseWheelsDiameter = 5.0; //might need to be tweaked
    public static double wheelBaseDiameter = 27.0;

    //Elevator Setpoints
    public static double elevatorGroundCollectHeight = -1.7;
    public static double elevatorHomeHeight = 0;
    public static double elevatorLevel1HatchHeight = -9.0;
    public static double elevatorLevel1CargoHeight = -0.6;
    public static double elevatorCargoShipHeight = 20;
    public static double elevatorLevel2HatchHeight = 21.1;
    public static double elevatorLevel2CargoHeight = 19.5;
    public static double elevatorCargoLoadingStationHeight = 9.9;
    public static double elevatorFlipHeight = 15;
    public static double elevatorQuickHatchFlipHeight = 1.0;
    public static double elevatorQuickCargoFlipHeight = 12.5;
    public static double elevatorHatchCollectUp = -5.0;
    
    //Arm Setpoints
    public static double armHomeAngle = 0;
    public static double armHatchCollectedAngle = 70;
    public static double armHatchAngle = 80;
    public static double armCargoShipAngle = 90;
    public static double armRocketLevel1CargoAngle = 90;
    public static double armRocketLevel2CargoAngle = 73;
    public static double armLoadingStationCargoAngle = 53;
    public static double armFloorCollectCargoAngle = 123;
    public static double armFullSendCargoAngle = 30;
    public static double wristTolerance = 5;


    //Cargo Intake Pivot Setpoints
    public static double cargoIntakeAngle = 90;
    public static double cargoIntakeHomeAngle = 0;
    public static double cargoIntakeTolerance = 0.5;


    //Elevator Misc
    public static double elevatorPulleyDiameter = 1.506;   
    public static double elevatorTolerance = 1;       
    public static double setElevatorAbsoluteTimeout = 60.0; 
    public static double elevatorMax = 21.5;
    public static double elevatorMin = -20.0;   //NEED TO DEFINE  
    public static double elevatorHomeTolerance = 0.2;
    

    //Misc
    public static double period = 0.02;
}