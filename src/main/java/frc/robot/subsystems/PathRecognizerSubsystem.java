package frc.robot.subsystems;

import java.util.List;
import edu.wpi.first.wpilibj.TimedRobot;
import frc.pathrecognizer.VisionFieldLayoutRecognizer;
//import sun.awt.www.content.image.png;
import frc.pathrecognizer.AutonomousPath;
import frc.pathrecognizer.PixyCameraConnector;
import frc.pathrecognizer.FieldVisionInput;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PathRecognizerSubsystem extends BaseSubsystem {

    private VisionFieldLayoutRecognizer visionFieldLayoutRecognizer;
    private PixyCameraConnector pixyCameraConnector;
    private List<FieldVisionInput> fieldVisionInputList; 

    @Override
    public void initialize() {

    }

    public AutonomousPath getAutonomousPath(){
        
        visionFieldLayoutRecognizer = new VisionFieldLayoutRecognizer();
        pixyCameraConnector = new PixyCameraConnector();
        fieldVisionInputList = 
                         pixyCameraConnector.getFieldVisionInput();
         
         AutonomousPath autonomousPath = visionFieldLayoutRecognizer.detectPose(fieldVisionInputList);
         
         return autonomousPath;
         
    }


}
