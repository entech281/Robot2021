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

    private final ClampedDouble desiredTurretPositionEncoder = ClampedDouble.builder()
            .bounds(-5000000, 5000000)
            .withIncrement(5000.0)
            .withValue(0.0).build();

    @Override
    public void initialize() {
        turretMotor = new WPI_TalonSRX(RobotConstants.CAN.TURRET_MOTOR);

        turretMotorController = new TalonPositionController(turretMotor, frc.robot.RobotConstants.MOTOR_SETTINGS.TURRET, true);
        turretMotorController.configure();

        turretMotor.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 0);
        turretMotor.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 0);

        turretMotor.overrideLimitSwitchesEnable(true);

        reset();
    }


    public boolean isClockLimitHit() {
        return turretMotor.getSensorCollection().isFwdLimitSwitchClosed();
    }

    public void reset(){
        turretMotorController.resetPosition();
        desiredTurretPositionEncoder.setValue(turretMotorController.getActualPosition());
        update();
    }

    //public void turnTurret(Double speed){
     //   turretMotor.set(ControlMode.PercentOutput, speed);
    //}

    public boolean isCounterClockLimitHit() {
        return turretMotor.getSensorCollection().isRevLimitSwitchClosed();
    }
    
    public void adjustTurretCounterClockwise() {
        desiredTurretPositionEncoder.increment();
        update();
    }

    public void adjustTurretClockwise() {
        desiredTurretPositionEncoder.decrement();
        update();
    }
    private void update() {
        turretMotorController.setDesiredPosition(desiredTurretPositionEncoder.getValue());
    }

    public void setTurretPosition(double desiredAngle) {
        desiredTurretPositionEncoder.setValue(desiredAngle);
        update();
    }

    @Override
    public void periodic() {
        logger.log("Turret current position1", turretMotorController.getActualPosition());
        logger.log("Turret Desired Position1", turretMotorController.getDesiredPosition());
        logger.log("Turret Current Command", getCurrentCommand());
        logger.log("Control Mode", RobotConstants.MOTOR_SETTINGS.TURRET.getControlMode());
        logger.log("clockwise limit switch", isClockLimitHit());
        logger.log("counterclockwise limit switch", isCounterClockLimitHit());
        logger.log("Clamped double", desiredTurretPositionEncoder.getValue());
    }

    private static class LimitSwitchState {
        public static int closed = 1;
        public static int open = 0;
    }

}
