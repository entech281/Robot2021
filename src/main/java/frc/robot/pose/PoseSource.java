/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frc.robot.pose;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;


/**
 *
 * @author dcowden
 */
public interface PoseSource {
    //public RobotPose getRobotPose();
    //public Pose2d getPose();
    public FieldPose getFieldPose();
    //public void resetOdometry(Pose2d pose);
}
