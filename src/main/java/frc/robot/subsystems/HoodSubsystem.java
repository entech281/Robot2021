/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.RobotConstants;
import frc.robot.controllers.TalonPositionController;
import frc.robot.utils.ClampedDouble;

/**
 *
 * @author dcowden
 */
public class HoodSubsystem extends EntechSubsystem {

    private WPI_TalonSRX hoodMotor;
    private TalonPositionController hoodMotorController;
    public static final double HOOD_TOLERANCE_COUNTS = 50;
    // MCA: How were these presets found and are they wrong if the robot starts away from the limit switches?
    public static final double HOME_OFFSET = 15.0;
    public static final double CLOSE_PRESET = 375;
    public static final double FAR_PRESET = 911;
    public static final double STARTING_LINE_PRESET = 940;
    private boolean hoodHomedAlready = false;

    private final ClampedDouble desiredHoodPositionEncoder = ClampedDouble.builder()
            .bounds(0, 1500)
            .withIncrement(15.0)
            .withValue(0.0).build();

    @Override
    public void initialize() {
        hoodMotor = new WPI_TalonSRX(RobotConstants.CAN.HOOD_MOTOR);

        hoodMotorController = new TalonPositionController(hoodMotor, frc.robot.RobotConstants.MOTOR_SETTINGS.HOOD, true);
        hoodMotorController.configure();

        hoodMotor.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 0);
        hoodMotor.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 0);
        hoodMotor.overrideLimitSwitchesEnable(true);
    }

    public boolean isUpperLimitHit() {
        return hoodMotor.getSensorCollection().isFwdLimitSwitchClosed();
    }

    public boolean knowsHome(){
        return hoodHomedAlready;
    }

    public void reset(){
        hoodMotorController.resetPosition();
        desiredHoodPositionEncoder.setValue(0.0);
    }

    public boolean isLowerLimitHit() {
        return hoodMotor.getSensorCollection().isRevLimitSwitchClosed();
    }

    public void goToHomePosition(){
        hoodMotor.set(ControlMode.PercentOutput, 0.2);
    }

    public void goToHomeOffset(){
        if (knowsHome()) setHoodPosition(HOME_OFFSET);
    }

    private void update() {
        hoodMotorController.setDesiredPosition(desiredHoodPositionEncoder.getValue());
    }

    public void setHoodPosition(double desiredAngle) {
        if (knowsHome()) {
            desiredHoodPositionEncoder.setValue(desiredAngle);
            update();
        }
    }

    public void park(){
        if (knowsHome()) {
            desiredHoodPositionEncoder.setValue(HOME_OFFSET);
            update();
        }
    }

    public boolean atHoodPosition() {
        return Math.abs(hoodMotorController.getDesiredPosition() - hoodMotorController.getActualPosition()) < HOOD_TOLERANCE_COUNTS;
    }

    public void adjustHoodForward() {
        if (knowsHome()) {
            desiredHoodPositionEncoder.increment();
            update();
        }
    }

    public void upAgainstTargetPreset(){
        if (knowsHome()) {
            desiredHoodPositionEncoder.setValue(CLOSE_PRESET);
               update();
        }
    }

    public void trenchPreset(){
        if (knowsHome()) {
            desiredHoodPositionEncoder.setValue(FAR_PRESET);
            update();
        }
    }

    public void startingLinePreset(){
        if (knowsHome()) {
            desiredHoodPositionEncoder.setValue(STARTING_LINE_PRESET);
            update();
        }
    }

    public void adjustHoodBackward() {
        if (knowsHome()) {
            desiredHoodPositionEncoder.decrement();
            update();
        }
    }

    @Override
    public void periodic() {
        if (isUpperLimitHit()) {
            reset();
            hoodHomedAlready = true;
        }
        if (!knowsHome()) {
            goToHomePosition();
        }
        logger.log("Hood current position1", hoodMotorController.getActualPosition());
        logger.log("Hood Desired Position1", hoodMotorController.getDesiredPosition());
        logger.log("Hood Current Command", getCurrentCommand());
        logger.log("Control Mode", RobotConstants.MOTOR_SETTINGS.HOOD.getControlMode());
        logger.log("upper limit switch", isUpperLimitHit());
        logger.log("lower limit switch", isLowerLimitHit());
        logger.log("Clamped double", desiredHoodPositionEncoder.getValue());

        logger.log("Known home", knowsHome());
    }

}
