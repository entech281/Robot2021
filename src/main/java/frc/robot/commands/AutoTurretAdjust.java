package frc.robot.commands;

import frc.robot.pose.PoseSource;
import frc.robot.subsystems.TurretSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.utils.ShooterCalculations;
import frc.robot.utils.TurretCalculations;
/**
 *
 * @author aryan
 * @modifiedBy rohit
 */
public class AutoTurretAdjust extends EntechCommandBase{

    private TurretSubsystem turret;
    private PoseSource poseSource;
    private double customAngle = 1000;
    
    public AutoTurretAdjust(TurretSubsystem turret, PoseSource poseSource) {
        super(turret);
        this.poseSource = poseSource;
        this.turret = turret;
    }
    @Override
    public void initialize(){
    }

    @Override
    public void execute(){
        double distance = poseSource.getRobotPose().getTargetLocation().getDistanceToTarget();
        double lateralOffset = poseSource.getRobotPose().getTargetLateralOffset();
        
        if(poseSource.getRobotPose().getVisionDataValidity()){
            customAngle = TurretCalculations.turretEncoderPosition(distance, lateralOffset);
        }
      turret.setTurretPosition(customAngle);
    }

    
}

