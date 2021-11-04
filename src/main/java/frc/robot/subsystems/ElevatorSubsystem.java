package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.RobotConstants;
import static frc.robot.RobotConstants.*;
import frc.robot.controllers.*;
import edu.wpi.first.wpilibj.Timer;

public class ElevatorSubsystem extends EntechSubsystem {

    private WPI_TalonSRX elevatorMotor;
    private TalonSpeedController elevatorMotorController;
    private DigitalInput intakeBallSensor;
    private DigitalInput shooterBallSensor;
    private boolean autoBallPickupActive = false;
    private boolean autoBallPickupWorking = false;
    private double elevatorSpeed = 0.;
    private Timer m_timer;
    private double DELAY1 = 0.3;
    private double DELAY2 = 0.1;

    @Override
    public void initialize() {

        elevatorMotor = new WPI_TalonSRX(RobotConstants.CAN.ELEVATOR_MOTOR);
        elevatorMotorController = new TalonSpeedController(elevatorMotor, MOTOR_SETTINGS.ELEVATOR,true);
        elevatorMotorController.configure();
        elevatorMotorController.setDesiredSpeed(0);

        intakeBallSensor = new DigitalInput(RobotConstants.DIGITIAL_INPUT.BALL_SENSOR);
        shooterBallSensor = new DigitalInput(RobotConstants.DIGITIAL_INPUT.SHOOTER_SENSOR);

        m_timer = new Timer();
    }

    @Override
    public void periodic() {
	    if (DriverStation.getInstance().isDisabled()) {
	        setElevatorSpeed(0.);
        }
        if ( autoBallPickupActive && !autoBallPickupWorking && isBallAtIntake()) {
            m_timer.stop();
            m_timer.reset();
            m_timer.start();
            autoBallPickupWorking = true;
        }
        
        if (autoBallPickupWorking) {
            if (m_timer.get() <= DELAY1) {
                setElevatorSpeed(0.3);
            } else if (m_timer.get() <= (DELAY1+DELAY2)) {
                setElevatorSpeed(0.5);
            } else {
                setElevatorSpeed(0.0);
                autoBallPickupWorking = false;
            }
        }

        elevatorMotorController.setDesiredSpeed(elevatorSpeed);

        logger.log("Elevator Current command", getCurrentCommand());
        logger.log("Ball sensor", isBallAtIntake());
        logger.log("Shooter sensor", isBallAtShooter());
    }

    public boolean isBallAtIntake(){
        return intakeBallSensor.get();
    }

    public boolean isBallAtShooter(){
        return shooterBallSensor.get();
    }

    public void setElevatorSpeed(double desiredSpeed) {
        elevatorSpeed = desiredSpeed;
    }

    public void activateAutoBallPickup() {
        autoBallPickupActive = true;
    }

    public void deactivateAutoBallPickup() {
        autoBallPickupActive = false;
    }
}
