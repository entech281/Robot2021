/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frc.robot.commands;

import frc.robot.pose.PoseSource;
import frc.robot.subsystems.TurretSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.utils.ShooterCalculations;

/**
 *
 * @author aryan
 * @modifiedBy rohit
 */
public class AutoTurretShooterAdjust extends EntechCommandBase{

    private ShooterSubsystem shooter;
    private TurretSubsystem turret;
    private PoseSource poseSource;
    private int customSpeed = 4500;
    private double customPosition = 1000;
    
    public AutoTurretShooterAdjust(ShooterSubsystem shooter, TurretSubsystem turret, PoseSource poseSource) {
        super(shooter, turret);
        this.poseSource = poseSource;
        this.turret = turret;
        this.shooter = shooter;
    }
    @Override
    public void initialize(){
    }

    @Override
    public void execute(){
        double distance = poseSource.getRobotPose().getTargetLocation().getDistanceToTarget();
        
        if(poseSource.getRobotPose().getVisionDataValidity()){
            customSpeed = ShooterCalculations.calculateAutoShooterSpeed(distance);
            customPosition = ShooterCalculations.turretEncoderPosition(distance);
        }
        shooter.setShooterSpeed(customSpeed);
        turret.setTurretPosition(customPosition);
    }

    @Override
    public boolean isFinished(){
        return !shooter.isShooterOn();
    }
}
