package frc.robot;

import java.util.ArrayList;

public interface PixyFieldPoseResolver{

    public AutonomousPath detectPose(ArrayList<FieldVisionInput> arraylistFieldVisionInput);
    
}