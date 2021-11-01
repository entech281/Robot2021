package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.RobotConstants;
import static frc.robot.RobotConstants.*;
import frc.robot.controllers.*;

public class ElevatorSubsystem extends EntechSubsystem {

    private WPI_TalonSRX elevatorMotor;
    private TalonSpeedController elevatorMotorController;
    private DigitalInput intakeBallSensor;
    private DigitalInput shooterBallSensor;
    private double elevatorSpeed = 0.;

    @Override
    public void initialize() {

        elevatorMotor = new WPI_TalonSRX(RobotConstants.CAN.ELEVATOR_MOTOR);
        elevatorMotorController = new TalonSpeedController(elevatorMotor, MOTOR_SETTINGS.ELEVATOR,true);
        elevatorMotorController.configure();
        elevatorMotorController.setDesiredSpeed(0);

        intakeBallSensor = new DigitalInput(RobotConstants.DIGITIAL_INPUT.BALL_SENSOR);
        shooterBallSensor = new DigitalInput(RobotConstants.DIGITIAL_INPUT.SHOOTER_SENSOR);
    }

    @Override
    public void periodic() {
	if (DriverStation.getInstance().isDisabled()) {
	    setElevatorSpeed(0.);
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

}
