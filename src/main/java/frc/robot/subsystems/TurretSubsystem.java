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
import frc.robot.commands.SnapToVisionTargetCommand;
import frc.robot.pose.PoseSource;

/**
 *
 * @author aryan - for hoodSubsystem
 * @modifiedBy rohit for turretSubsytem
 */
public class TurretSubsystem extends BaseSubsystem {

    private WPI_TalonSRX turretMotor;
    private TalonPositionController turretMotorController;
    public static final double TURRET_TOLERANCE_COUNTS = 50;
    public static final double HOME_OFFSET = 15.0;
    public static final double CLOSE_PRESET = 375;
    public static final double FAR_PRESET = 911;
    public static final double STARTING_LINE_PRESET = 940;
    private boolean turretHomedAlready = false;
    
    private final ClampedDouble desiredTurretPositionEncoder = ClampedDouble.builder()
            .bounds(0, 1500)
            .withIncrement(5.0)
            .withValue(0.0).build();

    @Override
    public void initialize() {
        turretMotor = new WPI_TalonSRX(RobotConstants.CAN.TURRET_MOTOR);

        turretMotorController = new TalonPositionController(turretMotor, frc.robot.RobotConstants.MOTOR_SETTINGS.TURRET, true);
        turretMotorController.configure();
        
        turretMotor.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen,
                0);
        turretMotor.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen,
                0);

        turretMotor.overrideLimitSwitchesEnable(true);
    }

    public boolean isUpperLimitHit() {
        return turretMotor.getSensorCollection().isFwdLimitSwitchClosed();
    }
    
    public boolean knowsHome(){
        return turretHomedAlready;
    }

    public void reset(){
        turretMotorController.resetPosition();
        desiredTurretPositionEncoder.setValue(0.0);
    }
    
    public boolean isLowerLimitHit() {
        return turretMotor.getSensorCollection().isRevLimitSwitchClosed();
    }

    public void goToHomePosition(){
        turretMotor.set(ControlMode.PercentOutput, 0.2);
        turretHomedAlready = true;
    }
    
    public void goToHomeOffset(){
        setTurretPosition(HOME_OFFSET);
    }
    
    private void update() {
        turretMotorController.setDesiredPosition(desiredTurretPositionEncoder.getValue());
    }

    public void setTurretPosition(double desiredAngle) {
        desiredTurretPositionEncoder.setValue(desiredAngle);
        update();
    }
    
    public void park(){
        desiredTurretPositionEncoder.setValue(HOME_OFFSET);
        update();
    }
    // RR
    public boolean atTurretPosition() {
        return Math.abs(turretMotorController.getDesiredPosition() - turretMotorController.getActualPosition()) < TURRET_TOLERANCE_COUNTS;
    }

    public void adjustTurretRight() {
        desiredTurretPositionEncoder.increment();
        update();
    }
    
    public void upAgainstTargetPreset(){
        desiredTurretPositionEncoder.setValue(CLOSE_PRESET);
        update();
    }

    public void trenchPreset(){
        desiredTurretPositionEncoder.setValue(FAR_PRESET);
        update();
    }

    public void startinfLinePreset(){
        desiredTurretPositionEncoder.setValue(STARTING_LINE_PRESET);
        update();
    }
    
    public void adjustTurretLeft() {
        desiredTurretPositionEncoder.decrement();
        update();
    }

    @Override
    public void periodic() {
        logger.log("Turret current position1", turretMotorController.getActualPosition());
        logger.log("Turret Desired Position1", turretMotorController.getDesiredPosition());
        logger.log("Turret Current Command", getCurrentCommand());
        logger.log("Control Mode", RobotConstants.MOTOR_SETTINGS.INTAKE.getControlMode());
        logger.log("turret upper limit switch", isUpperLimitHit());
        logger.log("turret lower limit switch", isLowerLimitHit());
        logger.log("Clamped double", desiredTurretPositionEncoder.getValue());
    }

    private static class LimitSwitchState {
        public static int closed = 1;
        public static int open = 0;
    }

}
