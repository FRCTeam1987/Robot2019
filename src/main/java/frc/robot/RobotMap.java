package frc.robot;


public class RobotMap {

    //Driver Buttons
    public static int driverID = 0;
    public static int elevatorToHeightButton = 1; //a
    public static int collectHatchButton = 2;
    public static int cargoCollectButton = 3; //x
    public static int placeButton = 4; //y
    public static int armManualForwardButton = 5;
    public static int armManualBackButton = 6;
    public static int toggleShifterButton = 9; //left stick push
    public static int aimRobotButton = 7; 

    //Co-driver Buttons
    public static int coDriverID = 1;
    public static int switchPotentialClawSideButton = 13;
    public static int level1HatchSetButton = 8;
    public static int level2HatchSetButton = 14;
    public static int cargoShipCargoSetButton = 9;
    public static int level1CargoSetButton = 15;
    public static int level2CargoSetButton = 16;
    public static int loadingStationCargoSetButton = 10;
    public static int defenseSetButton = 5;
    public static int stopAllButton = 11; //change ID
    public static int hasHatchButton = 19;
    public static int yeetOntoHabButton = 17;

    //Drive
    public static double maxHighGearVelocity = 13;
    public static double maxHighGearAcceleration = 8;
    public static double maxHighGearJerk = 197;

    //Path Turning Tuning
    public static double pathGP = 0.048;
    public static double pathGD = 0.0;
    
    //Drive PID
    public static double pathHighGearKP = 0.4;
    public static double pathHighGearKI = 0.0;
    public static double pathHighGearKD = 0.0;
    public static double pathHighGearKV = 1 / maxHighGearVelocity;
    public static double pathHighGearKA = 0.0;

    public static double driveStraightHighKP = 1.0;
    public static double driveStraightHighKD = 0.0;

    public static double drivePivotHighKP = 0.7;
    public static double drivePivotHighKD = 0.0;

    
    public static double driveStraightLowKP = 1.3;
    public static double driveStraightLowKD = 0.0;

    public static double drivePivotLowKP = 1.0;
    public static double drivePivotLowKD = 0.0;




    
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
    // public static int clawHatchSonarPingID = 8;
    // public static int clawHatchSonarEchoID = 9;
    public static int wristHomeID = 2;
    public static int cargoIntakeHomeID = 3;
    public static int elevatorMaxID = 4;
    public static int elevatorHomeID = 5;
    public static int elevatorMinID = 6;

    public static int defaultTimeout = 10;
    public static double ticksPerRotation = 4096;

    //Analog IDs
    // public static int clawCargoSonarID = 1;

    //Pneumatics
    public static int highGearID = 0;
    public static int lowGearID = 1;
    public static int hatchFingersOutID = 2;
    public static int hatchFingersInID = 3;
    public static int venturiID = 4;

    //Robot Dimensions
    public static double driveBaseWheelsDiameter = 5.0 / 12.0; 
    public static double wheelBaseDiameter = 1.875;

    //Elevator Setpoints
    public static double elevatorGroundCollectHeight = -1.7;    
    public static double elevatorHomeHeight = 0;
    public static double elevatorCollectHatchHeight = -3.0;
    public static double elevatorLevel1HatchHeight = -9;
    public static double elevatorLevel1CargoHeight = 1.0; 
    public static double elevatorCargoShipHeight = 20;
    public static double elevatorLevel2HatchHeight = 21.1;
    public static double elevatorLevel2CargoHeight = 19.5;
    public static double elevatorCargoLoadingStationHeight = 7.0; 
    public static double elevatorFlipHeight = 15;
    public static double elevatorQuickHatchFlipHeight = 1.0;
    public static double elevatorQuickCargoFlipHeight = 12.5;
    public static double elevatorYeetHab = 4;
    public static double elevatorHabLevel2 = 5;
    
    //Arm Setpoints
    public static double armHomeAngle = 0;
    public static double armHatchCollectedAngle = 90;
    public static double armHatchAngle = 90;
    public static double armCargoShipAngle = 105;                
    public static double armRocketLevel1CargoAngle = 75;
    public static double armRocketLevel2CargoAngle = 67;
    public static double armLoadingStationCargoAngle = 63;      
    public static double armFloorCollectCargoAngle = 125;
    public static double armFullSendCargoAngle = 30;
    public static double armYeetHab = 8;
    public static double wristTolerance = 3;


    //Cargo Intake Pivot Setpoints
    public static double practiceBotCargoAngleOffset = -15;
    public static double cargoIntakeAngle = 81;
    // public static double cargoIntakeHomeAngle = -14.5;
    public static double cargoIntakeHomeAngle = -7;
    public static double cargoIntakeTolerance = 1.5;


    //Elevator Misc
    public static double elevatorPulleyDiameter = 1.506;   
    public static double elevatorTolerance = 0.2;       
    public static double setElevatorAbsoluteTimeout = 60.0; 
    public static double elevatorMax = 21.5;
    public static double elevatorMin = -20.0;   
    public static double elevatorHomeTolerance = 0.25;

    //Limelight Constants
    public static double kLimelightSteer = 0.1; //how hard it turns towards target
    public static double kLimelightDrive = 0.035; //how hard it drives fwd towards target
    public static double limelightHatchTargetArea = 10.0; //Area of target when it is properly aimed
    public static double limelightMaxDrive = 0.8; //max speed robot will drive forward

    //Misc
    public static double period = 0.02;
}