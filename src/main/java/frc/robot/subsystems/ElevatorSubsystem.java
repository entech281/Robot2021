package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.RobotConstants;
import static frc.robot.RobotConstants.*;
import frc.robot.controllers.*;
import edu.wpi.first.wpilibj.Timer;

public class ElevatorSubsystem extends EntechSubsystem {

    public enum ElevatorMode {
        stop, up, down, autoPickup
    }
    
    private WPI_TalonSRX elevatorMotor;
    private TalonSpeedController elevatorMotorController;
    private DigitalInput intakeBallSensor;
    private DigitalInput shooterBallSensor;
    private boolean autoPickupInProcess = false;
    private Timer m_timer;
    private ElevatorMode currentMode = ElevatorMode.stop;
    static private double DELAY1 = 0.3;
    static private double DELAY2 = 0.1;
    static private double PICKUP_SPEED1 = 0.3;
    static private double PICKUP_SPEED2 = 0.5;
    static private double NORMAL_SPEED = 0.4;

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
        // decide on motor speed based on current state
        double speed = 0.0;
        switch (currentMode) {
            case stop:
                speed = 0.0;
                break;
            case autoPickup:
                // we are in the middle of doing an auto-pickup
                if (autoPickupInProcess) {
                    if (m_timer.get() <= DELAY1) {
                        speed = PICKUP_SPEED1;
                    } else if (m_timer.get() <= (DELAY1+DELAY2)) {
                        speed = PICKUP_SPEED2;
                    } else {
                        speed = 0.0;
                        autoPickupInProcess = false;
                    }
                } else if (isBallAtIntake()) {
                    // start a new auto-pickup
                    m_timer.stop();
                    m_timer.reset();
                    m_timer.start();
                    autoPickupInProcess = true;                    
                }
                break;
            case up:
                speed = NORMAL_SPEED;
                break;
            case down:
                speed = -NORMAL_SPEED;
                break;
        }
        // disabled mode takes priority -- so set it last
	    if (DriverStation.getInstance().isDisabled()) {
            speed = 0.0;
        }
        // set the motor to the correct speed
        elevatorMotorController.setDesiredSpeed(speed);

        // send output to dashboard
        logger.log("Elevator Speed", speed);
        logger.log("Ball sensor", isBallAtIntake());
        logger.log("Shooter sensor", isBallAtShooter());
    }

    public boolean isBallAtIntake(){
        return intakeBallSensor.get();
    }

    public boolean isBallAtShooter(){
        return shooterBallSensor.get();
    }

    public void setMode(ElevatorMode mode) {
        // only allow mode to change to autoPickup if the elevator is currently stopped.
        if ((mode == ElevatorMode.autoPickup) && (currentMode != ElevatorMode.stop)) {
            return;
        }
        currentMode = mode;
    }
}
