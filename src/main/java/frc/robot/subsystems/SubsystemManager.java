package frc.robot.subsystems;

import java.util.Arrays;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import frc.robot.pose.FieldPose;
import frc.robot.pose.FieldPoseManager;
import frc.robot.pose.PoseSource;
import frc.robot.pose.RobotPose;
import frc.robot.pose.RobotPoseManager;

public class SubsystemManager implements PoseSource {

    boolean hasClimb = false;

    public SubsystemManager() {
    }

    public DriveSubsystem getDriveSubsystem() {
        return driveSubsystem;
    }

    // public IntakeSubsystem getIntakeSubsystem() {
    //     return intakeSubsystem;
    // }

    public NavXSubsystem getNavXSubsystem() {
        return navXSubsystem;
    }

    // public ClimbSubsystem getClimbSubsystem() {
    //     return climbSubsystem;
    // }

    // public ShooterSubsystem getShooterSubsystem() {
    //     return shooterSubsystem;
    // }

    // public VisionSubsystem getVisionSubsystem(){
    // return visionSubsystem;
    // }

    // public HoodSubsystem getHoodSubsystem() {
    //     return hoodSubsystem;
    // }

    private DriveSubsystem driveSubsystem;
    // private IntakeSubsystem intakeSubsystem;
    private NavXSubsystem navXSubsystem;
    // private ShooterSubsystem shooterSubsystem;
    // private ClimbSubsystem climbSubsystem;
    // private VisionSubsystem visionSubsystem;
    // private HoodSubsystem hoodSubsystem;

    private RobotPoseManager robotPoseManager;
    private final FieldPoseManager fieldPoseManager = new FieldPoseManager();

    // public void setHoodSubsystem(HoodSubsystem hoodSubsystem) {
    //     this.hoodSubsystem = hoodSubsystem;
    // }

    public void initAll() {
        driveSubsystem = new DriveSubsystem();
        // intakeSubsystem = new IntakeSubsystem();
        navXSubsystem = new NavXSubsystem();
        // shooterSubsystem = new ShooterSubsystem();
        // climbSubsystem = new ClimbSubsystem();
        // visionSubsystem = new VisionSubsystem();
        // hoodSubsystem = new HoodSubsystem();

        Arrays.asList(
            // driveSubsystem, 
                //intakeSubsystem, 
                // visionSubsystem,
                //shooterSubsystem, hoodSubsystem,
                navXSubsystem).forEach(subsystem -> subsystem.initialize());

        robotPoseManager = new RobotPoseManager(navXSubsystem.getRotation2d());

    }

    public void updatePoses() {
        robotPoseManager.updateEncoders(driveSubsystem.getEncoderValues());
        robotPoseManager.updateNavxAngle(navXSubsystem.updateNavXAngle());
        robotPoseManager.updateRotation2D(navXSubsystem.getRotation2d());
        robotPoseManager.updateWheelSpeed(driveSubsystem.getWheelSpeeds());
        // robotPoseManager.updateVisionData(visionSubsystem.getVisionData());
        robotPoseManager.update();
    }

    @Override
    public RobotPose getRobotPose() {
        return robotPoseManager.getCurrentPose();
    }

    public Pose2d getPose() {
        return robotPoseManager.getPose();
    }

    public void resetOdometry(Pose2d pose) {
        robotPoseManager.resetOdometry(pose);
        driveSubsystem.resetEncoders();
    }

    @Override
    public FieldPose getFieldPose() {
        return fieldPoseManager.getCurrentPose();
    }

}
