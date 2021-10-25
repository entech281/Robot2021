package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.RobotConstants;
import static frc.robot.RobotConstants.*;
import frc.robot.controllers.*;

public class IntakeSubsystem extends BaseSubsystem {

    private WPI_TalonSRX intakeMotor;
    private TalonSpeedController intakeMotorController;
    private DoubleSolenoid deployIntakeSolenoids;
    private DoubleSolenoid.Value currentStateIntake;
    private double intakeSpeed = 0.0;

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
    }

    @Override
    public void periodic() {
	if (DriverStation.getInstance().isDisabled()) {
	    // nothing to do at the moment
	}
        intakeMotorController.setDesiredSpeed(intakeSpeed);
        updateIntakeSolenoidPosition();

        logger.log("Current command", getCurrentCommand());
    }

    public void deployAndStart(){
        deployIntakeArms();
        intakeOn();
    }

    public void raiseAndStop(){
        raiseIntakeArms();
        intakeOff();
    }

    private void deployIntakeArms(){
        currentStateIntake = DoubleSolenoid.Value.kForward;
    }

    private void raiseIntakeArms(){
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
        if (isIntakeOn()) {
            intakeOff();
        } else {
            intakeOn();
        }
    }

    public boolean isIntakeOn(){
        return intakeMotorController.getDesiredSpeed() != 0;
    }

    // TODO: This needs to be a command since it now involves several subsystems -- keep for the moment
    // public void fireSequenceEnable(double desiredSpeed){
    //     if(isBallAtShooter()){
    //         setIntakeMotorSpeed(0);
    //         setElevatorSpeed(0.0);
    //         fire();
    //     } else {
    //         currentStateFlapper = DoubleSolenoid.Value.kReverse;
    //         updateFlapperSolenoidPosition();
    //         setElevatorSpeed(desiredSpeed);
    //     }
    // }

    public void setIntakeMotorSpeed(double desiredSpeed) {
        intakeSpeed = desiredSpeed;
    }

    public void updateIntakeSolenoidPosition() {
        if ( deployIntakeSolenoids.get() != currentStateIntake ){
            deployIntakeSolenoids.set(currentStateIntake);
        }
    }

}
