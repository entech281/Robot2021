package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.RobotConstants;
import frc.robot.controllers.*;

public class ShooterSubsystem extends EntechSubsystem {

    private static final int DEFAULT_SHOOTER_FIRE_RPM = 5000;
    private static final int SHOOTER_MAX_RPM = 5400;
    private static final int SHOOTER_RPM_TOLERANCE=100;
    private static final int SHOOTER_RPM_INCREMENT=100;
    private static final double FIRE_TIME = 0.5; //secs

    private DoubleSolenoid flapperSolenoids;
    private DoubleSolenoid.Value currentStateFlapper = DoubleSolenoid.Value.kOff;
    private Timer m_timer = new Timer();

    private CANSparkMax shootMotor;
    private SparkSpeedController shooterMotorClosedLoopController;

    private int currentSpeedSetShooter = 0;

    @Override
    public void initialize() {
        flapperSolenoids = new DoubleSolenoid(RobotConstants.CAN.FORWARD_S, RobotConstants.CAN.REVERSE_S);
        reload();

        shootMotor = new CANSparkMax(RobotConstants.CAN.SHOOTER_MOTOR, MotorType.kBrushless);
        shooterMotorClosedLoopController = new SparkSpeedController(shootMotor, frc.robot.RobotConstants.MOTOR_SETTINGS.SHOOTER_CLOSED_LOOP,true);
        shooterMotorClosedLoopController.configure();
    }

    //Current structure of shooter is in auto it will be dictated purely by vision
    //in manual it will be adjusted by alex and with the presets
    @Override
    public void periodic() {
	if (DriverStation.getInstance().isDisabled()) {
	    currentStateFlapper = DoubleSolenoid.Value.kOff;
	} 
        updateFlapperSolenoidPosition();

//        logger.log("Current command", getCurrentCommand());
        logger.log("Current Speed", shooterMotorClosedLoopController.getActualSpeed());
        logger.log("Desired Speed", shooterMotorClosedLoopController.getDesiredSpeed());
        logger.log("AtSpeed", atShootSpeed());
//        logger.log("Enabled", shooterMotorClosedLoopController.isEnabled());
//        logger.log("Start controller config", RobotConstants.MOTOR_SETTINGS.SHOOTER_CLOSED_LOOP.ctrlType);
//        logger.log("Output Bus voltage", shootMotor.getBusVoltage());
//        logger.log("Applied output", shootMotor.getAppliedOutput());
//        logger.log("Output Current", shootMotor.getOutputCurrent());
//        logger.log("Faults", shootMotor.getFaults());
//        logger.log("Stick Faults", shootMotor.getStickyFaults());
//        logger.log("Last error", shootMotor.getLastError());
    }


    public void fire(){
        m_timer.stop();
        m_timer.reset();
        m_timer.start();
        currentStateFlapper = DoubleSolenoid.Value.kForward;
        updateSolenoidPosition();
    }

    public void reload(){
        currentStateFlapper = DoubleSolenoid.Value.kReverse;
        updateSolenoidPosition();
    }

    private void updateSolenoidPosition() {
        if ( flapperSolenoids.get() != currentStateFlapper ){
            flapperSolenoids.set(currentStateFlapper);
        }
    }

    public void startShooterPresetSpeed(){
        currentSpeedSetShooter = DEFAULT_SHOOTER_FIRE_RPM;
        shooterMotorClosedLoopController.setDesiredSpeed(DEFAULT_SHOOTER_FIRE_RPM);
    }

    public void setShooterSpeed(int currentSpeed){
        currentSpeedSetShooter = currentSpeed;
        shooterMotorClosedLoopController.setDesiredSpeed(currentSpeedSetShooter);
    }

    public boolean isShooterOn(){
        if (currentSpeedSetShooter != 0)
            return true;
        return false;
    }

    public void stopShooter(){
        currentSpeedSetShooter = 0;
        shooterMotorClosedLoopController.stop();
    }

    public boolean atShootSpeed(){
        return shooterMotorClosedLoopController.isSpeedWithinTolerance(SHOOTER_RPM_TOLERANCE, currentSpeedSetShooter);
    }

    public double getActualSpeed() {
        return shooterMotorClosedLoopController.getActualSpeed();
    }

    public double getDesiredSpeed(){
        return shooterMotorClosedLoopController.getDesiredSpeed();
    }

    public void updateFlapperSolenoidPosition() {
        if ( flapperSolenoids.get() != currentStateFlapper ) {
            flapperSolenoids.set(currentStateFlapper);
        }
    }
}
