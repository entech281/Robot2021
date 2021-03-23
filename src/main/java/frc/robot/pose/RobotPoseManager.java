package frc.robot.pose;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import frc.robot.RobotConstants;
import frc.robot.vision.VisionData;

public class RobotPoseManager {

    private EncoderValues encoders = EncoderValues.NO_ENCODER_VALUES;
    private EncoderValues lastEncoderValues = EncoderValues.NO_ENCODER_VALUES;
    private NavXData navXData = NavXData.EMPTY_NAVX_DATA;
    private VisionData vData = VisionData.DEFAULT_VISION_DATA;
    private Rotation2d rotation;
    private WheelColorValue wColor;

    private RobotPose pose = RobotConstants.DEFAULTS.START_POSE;
    private final DifferentialDriveOdometry m_odometry;
    private DifferentialDriveWheelSpeeds wheelSpeeds = new DifferentialDriveWheelSpeeds();
    private boolean navXWorking = navXData.getValidity();

    public RobotPoseManager(Rotation2d initial){
        rotation = initial;
        m_odometry = new DifferentialDriveOdometry(initial);
    }

    public RobotPose getCurrentPose() {
        return pose;
    }

    public DifferentialDriveWheelSpeeds getWheelSpeeds(){
        return wheelSpeeds;
    }

    public void update() {
        //do all the maths to get a new pose
        pose = new RobotPose(PoseMathematics.addPoses(pose.getRobotPosition(), 
                PoseMathematics.calculateRobotPositionChange(
                        encoderDeltaLeft(), 
                        getEncodersRight())), vData);
        
        m_odometry.update(rotation, encoders.getLeftFront(), -encoders.getRightFront());
        
        if (navXWorking) {
            RobotPosition withNavXPosition = new RobotPosition(pose.getRobotPosition().getForward(), pose.getRobotPosition().getHorizontal(), navXData.getAngle());
            pose = new RobotPose(withNavXPosition, vData);
        }
    }

      /**
   * Returns the currently-estimated pose of the robot.
   *
   * @return The pose.
   */
  public Pose2d getPose() {
    return m_odometry.getPoseMeters();
  }

    public void updateWheelSpeed(DifferentialDriveWheelSpeeds wheelSpeeds){
        this.wheelSpeeds = wheelSpeeds;
    }

    public void updateNavxAngle(NavXData newNavxData) {
        this.navXData = newNavxData;
        navXWorking = this.navXData.getValidity();
    }

    public void updateRotation2D(Rotation2d rotation){
        this.rotation = rotation;
    }

    public void resetOdometry(Pose2d pose) {
        m_odometry.resetPosition(pose, rotation);
      }

    public void updateEncoders(EncoderValues newEncoderValues) {
        this.lastEncoderValues = this.encoders;
        this.encoders = newEncoderValues;
    }

    public void updateVisionData(VisionData newVisionData) {
        this.vData = newVisionData;
    }

    public void updateWheelColor(WheelColorValue newWheelColor) {
        this.wColor = newWheelColor;
    }

    public double encoderDeltaLeft() {
        return (this.encoders.getLeftFront() + this.encoders.getLeftRear()) / 2
                - (this.lastEncoderValues.getLeftFront() + this.lastEncoderValues.getLeftRear()) / 2;
    }

    public double getEncodersRight() {
        return (this.encoders.getRightFront() + this.encoders.getRightRear()) / 2
                - (this.lastEncoderValues.getRightFront() + this.lastEncoderValues.getRightRear()) / 2;
    }

}
