package frc.robot.subsystems;

import java.util.Arrays;

import frc.robot.pose.FieldPose;
import frc.robot.pose.FieldPoseManager;
import frc.robot.pose.PoseSource;
import frc.robot.pose.RobotPose;
import frc.robot.pose.RobotPoseManager;

public class SubsystemManager implements PoseSource{

    boolean hasClimb = false;

    public SubsystemManager() {
    }

    public DriveSubsystem getDriveSubsystem() {
        return driveSubsystem;
    }

    public IntakeSubsystem getIntakeSubsystem() {
        return intakeSubsystem;
    }

    public ElevatorSubsystem getElevatorSubsystem() {
        return elevatorSubsystem;
    }

    public NavXSubsystem getNavXSubsystem() {
        return navXSubsystem;
    }

    public ClimbSubsystem getClimbSubsystem() {
        return climbSubsystem;
    }

    public ShooterSubsystem getShooterSubsystem() {
        return shooterSubsystem;
    }

    public VisionSubsystem getVisionSubsystem(){
	return visionSubsystem;
    }

    public HoodSubsystem getHoodSubsystem() {
        return hoodSubsystem;
    }

    public TurretSubsystem getTurretSubsystem() {
        return turretSubsystem;
    }

    private DriveSubsystem driveSubsystem;
    private IntakeSubsystem intakeSubsystem;
    private ElevatorSubsystem elevatorSubsystem;
    private NavXSubsystem navXSubsystem;
    private ShooterSubsystem shooterSubsystem;
    private ClimbSubsystem climbSubsystem;
    private VisionSubsystem visionSubsystem;
    private HoodSubsystem hoodSubsystem;
    private PathRecognizerSubsystem pathRecognizerSubsystem;
    private TurretSubsystem turretSubsystem;

    private final RobotPoseManager robotPoseManager = new RobotPoseManager();
    private final FieldPoseManager fieldPoseManager = new FieldPoseManager();
    public void setHoodSubsystem(HoodSubsystem hoodSubsystem) {
        this.hoodSubsystem = hoodSubsystem;
    }

    public void initAll() {
        driveSubsystem = new DriveSubsystem();
        intakeSubsystem = new IntakeSubsystem();
        elevatorSubsystem = new ElevatorSubsystem();
        navXSubsystem = new NavXSubsystem();
        shooterSubsystem = new ShooterSubsystem();
        climbSubsystem = new ClimbSubsystem();
        visionSubsystem = new VisionSubsystem();
        hoodSubsystem  = new HoodSubsystem();
        pathRecognizerSubsystem = new PathRecognizerSubsystem();
        turretSubsystem = new TurretSubsystem();

        Arrays.asList(
            driveSubsystem,
            intakeSubsystem,
	    elevatorSubsystem,
            navXSubsystem,
	    visionSubsystem,
            shooterSubsystem,
            hoodSubsystem,
            pathRecognizerSubsystem,
            turretSubsystem).forEach(subsystem -> subsystem.initialize());

    }
    public void updatePoses() {
        robotPoseManager.updateEncoders(driveSubsystem.getEncoderValues());
        robotPoseManager.updateNavxAngle(navXSubsystem.updateNavXAngle());
	robotPoseManager.updateVisionData(visionSubsystem.getVisionData());
        robotPoseManager.update();
    }
    public void updateFieldPoses() {
        fieldPoseManager.setCurrentVisionFieldPath(pathRecognizerSubsystem.getAutonomousPath());
    }

    @Override
    public RobotPose getRobotPose() {
        return robotPoseManager.getCurrentPose();
    }

    @Override
    public FieldPose getFieldPose() {
        return fieldPoseManager.getCurrentPose();
    }

    @Override
    public String getVisionFieldPath() {
        return fieldPoseManager.getCurrentVisionFieldPath();
    }
}
