package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.robot.RobotConstants;
import static frc.robot.RobotConstants.*;
import frc.robot.controllers.*;

public class IntakeSubsystem extends BaseSubsystem {

    private WPI_TalonSRX intakeMotor;
    private TalonSpeedController intakeMotorController;
    private WPI_TalonSRX elevatorMotor;
    private TalonSpeedController elevatorMotorController;
    private DoubleSolenoid deployIntakeSolenoids;
    private DoubleSolenoid flapperSolenoids;
    private DoubleSolenoid.Value currentStateIntake;
    private DoubleSolenoid.Value currentStateFlapper;
    private DigitalInput intakeBallSensor;
    private DigitalInput shooterBallSensor;
    
    public static final double INTAKE_ON= 1.0;
    public static final double INTAKE_OFF=0.0;
    
    @Override
    public void initialize() {

            
        intakeMotor = new WPI_TalonSRX(RobotConstants.CAN.INTAKE_MOTOR);
        intakeMotorController = new TalonSpeedController(intakeMotor, MOTOR_SETTINGS.INTAKE,false);
        intakeMotorController.configure();
        intakeMotorController.setDesiredSpeed(0);


        deployIntakeSolenoids = new DoubleSolenoid(RobotConstants.CAN.FORWARD, RobotConstants.CAN.REVERSE);
        currentStateIntake = DoubleSolenoid.Value.kReverse;
        updateIntakeSolenoidPosition();

        flapperSolenoids = new DoubleSolenoid(RobotConstants.CAN.FORWARD_S, RobotConstants.CAN.REVERSE_S);
        currentStateFlapper = DoubleSolenoid.Value.kReverse;
        updateFlapperSolenoidPosition();


        elevatorMotor = new WPI_TalonSRX(RobotConstants.CAN.ELEVATOR_MOTOR);
        elevatorMotorController = new TalonSpeedController(elevatorMotor, MOTOR_SETTINGS.ELEVATOR,true);
        elevatorMotorController.configure();
        elevatorMotorController.setDesiredSpeed(0);

        intakeBallSensor = new DigitalInput(RobotConstants.DIGITIAL_INPUT.BALL_SENSOR);
        shooterBallSensor = new DigitalInput(RobotConstants.DIGITIAL_INPUT.SHOOTER_SENSOR);
   
    }

    public void deployAndStart(){
        deployIntakeSolenoids.set(DoubleSolenoid.Value.kForward);
        intakeOn();
    }
    
    public void raiseAndStop(){
        deployIntakeSolenoids.set(DoubleSolenoid.Value.kReverse);
        intakeOff();
    }
    
    public void deployIntakeArms(){
        if ( deployIntakeSolenoids.get() == DoubleSolenoid.Value.kReverse ){
            deployIntakeSolenoids.set(DoubleSolenoid.Value.kForward);
        }
    }
    
    public void raiseIntakeArms(){
        if ( deployIntakeSolenoids.get() == DoubleSolenoid.Value.kForward){
            deployIntakeSolenoids.set(DoubleSolenoid.Value.kReverse);
        }        
    }
    
    public void intakeOn(){
        intakeMotorController.setDesiredSpeed(INTAKE_ON);
    }
    
    public void intakeOff(){
        intakeMotorController.setDesiredSpeed(INTAKE_OFF);
    }
    
  
    public void toggleIntakeArms(){
        if (currentStateIntake == DoubleSolenoid.Value.kForward) {
            currentStateIntake = DoubleSolenoid.Value.kReverse;
        } else {
            currentStateIntake = DoubleSolenoid.Value.kForward;
        }
        updateIntakeSolenoidPosition();
    }
    
    public boolean isIntakeOn(){
        return intakeMotorController.getDesiredSpeed() != 0;
    }
    
    @Override
    public void periodic() {
        logger.log("Current command", getCurrentCommand());
        logger.log("Ball sensor", isBallAtIntake());
        logger.log("Shooter sensor", isBallAtShooter());

    }
  
    public boolean isBallAtIntake(){
        return !intakeBallSensor.get();
    }

    public boolean isBallAtShooter(){
        return shooterBallSensor.get();
    }

    public void fireSequenceEnable(double desiredSpeed){
        if(isBallAtShooter()){
            setIntakeMotorSpeed(0);
            currentStateFlapper = DoubleSolenoid.Value.kForward;
            updateFlapperSolenoidPosition();
        } else {
            currentStateFlapper = DoubleSolenoid.Value.kReverse;
            updateFlapperSolenoidPosition();
            setIntakeMotorSpeed(desiredSpeed);
        }
    }
    
    public void setIntakeMotorSpeed(double desiredSpeed) {
        intakeMotorController.setDesiredSpeed(desiredSpeed);
    }
    
    public void setElevatorSpeed(double desiredSpeed) {
       elevatorMotorController.setDesiredSpeed(desiredSpeed);
    }

    public void updateIntakeSolenoidPosition() {
        deployIntakeSolenoids.set(currentStateIntake);
    }

    public void updateFlapperSolenoidPosition() {
        flapperSolenoids.set(currentStateFlapper);
    }

}
