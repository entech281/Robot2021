package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
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
    private double elevatorSpeed = 0.;
    private double intakeSpeed = 0.0;
    private Timer m_timer = new Timer();
    
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
        deployIntakeArms();
        intakeOn();
    }
    
    public void raiseAndStop(){
        raiseIntakeArms();
        intakeOff();
    }
    
    public void deployIntakeArms(){
        currentStateIntake = DoubleSolenoid.Value.kForward;
    }
    
    public void raiseIntakeArms(){
        currentStateIntake = DoubleSolenoid.Value.kReverse;
    }
    
    public void intakeOn(){
        setIntakeMotorSpeed(INTAKE_ON);
    }
    
    public void intakeOff(){
        setIntakeMotorSpeed(INTAKE_OFF);
    }
    
  
    public void toggleIntakeArms(){
        if (currentStateIntake == DoubleSolenoid.Value.kForward) {
            currentStateIntake = DoubleSolenoid.Value.kReverse;
        } else {
            currentStateIntake = DoubleSolenoid.Value.kForward;
        }
    }
    
    public boolean isIntakeOn(){
        return intakeMotorController.getDesiredSpeed() != 0;
    }
    
    @Override
    public void periodic() {
        logger.log("Current command", getCurrentCommand());
        logger.log("Ball sensor", isBallAtIntake());
        logger.log("Shooter sensor", isBallAtShooter());

        elevatorMotorController.setDesiredSpeed(elevatorSpeed);
        intakeMotorController.setDesiredSpeed(intakeSpeed);
        if (m_timer.get() > 0.5) {
            deactivate();
        }
        updateFlapperSolenoidPosition();
        updateIntakeSolenoidPosition();
        }
  
    public boolean isBallAtIntake(){
        return intakeBallSensor.get();
    }

    public boolean isBallAtShooter(){
        return shooterBallSensor.get();
    }

    public void fireSequenceEnable(double desiredSpeed){
        if(isBallAtShooter()){
            setIntakeMotorSpeed(0);
            setElevatorSpeed(0.0);
            fire();
        } else {
            currentStateFlapper = DoubleSolenoid.Value.kReverse;
            updateFlapperSolenoidPosition();
            setElevatorSpeed(desiredSpeed);
        }
    }
    
    public void fire(){
        m_timer.stop();
        m_timer.reset();
        m_timer.start();
        currentStateFlapper = DoubleSolenoid.Value.kForward;
        updateFlapperSolenoidPosition();
    }

    public void deactivate(){
        currentStateFlapper = DoubleSolenoid.Value.kReverse;
        updateFlapperSolenoidPosition();
    }

    public void setIntakeMotorSpeed(double desiredSpeed) {
        intakeSpeed = desiredSpeed;
    }
    
    public void setElevatorSpeed(double desiredSpeed) {
        elevatorSpeed = desiredSpeed;
    }

    public void updateIntakeSolenoidPosition() {
        if ( deployIntakeSolenoids.get() != currentStateIntake ){
            deployIntakeSolenoids.set(currentStateIntake);
        }
    }

    public void updateFlapperSolenoidPosition() {
        if ( flapperSolenoids.get() != currentStateFlapper ){
            flapperSolenoids.set(currentStateFlapper);
        }
    }
}
