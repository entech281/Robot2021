/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import frc.robot.RobotConstants;
import frc.robot.pose.PoseSource;
import frc.robot.pose.RobotPose;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.TurretSubsystem;
import frc.robot.utils.PIDControlOutputProcessor;

/**
 *
 * @author aryan
 */
public class SnapToVisionTargetCommand extends EntechCommandBase {

    private TurretSubsystem turret;
    private PIDController controller;
    private double offset;
    private double output = 0.0;
    private PoseSource poseSource;
    public static final double TIMEOUT_SECONDS=2;
    private int count = 0;

    public SnapToVisionTargetCommand(TurretSubsystem turret, PoseSource poseSource) {
        super(turret,TIMEOUT_SECONDS);
        this.turret = turret;
        this.poseSource = poseSource;
        this.controller = new PIDController(RobotConstants.PID.AUTO_TURN.P,
            RobotConstants.PID.AUTO_TURN.I,
            RobotConstants.PID.AUTO_TURN.D);
    }
    @Override
    public void initialize(){
        controller.setSetpoint(0);
        controller.setTolerance(2);
    }

    @Override
    public void execute(){
        RobotPose rp = poseSource.getRobotPose();
        if(rp.getVisionDataValidity()){
            offset = rp.getTargetLateralOffset();
            output = controller.calculate(offset);
            output = PIDControlOutputProcessor.constrainWithMinBounds(output, 0.8, 0.25);
            turret.turnTurret(output);
        }

    }

    @Override
    public boolean isFinished(){
        if(controller.atSetpoint()){
            count += 1;
        } else {
            count = 0;
        }
        return count > 3 || !poseSource.getRobotPose().getVisionDataValidity();
    }

}
