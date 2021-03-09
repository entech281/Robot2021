package frc.robot;

import java.util.List;

public interface PixyFieldPoseResolverInterface{

    public AutonomousPath detectPose(List<FieldVisionInput> arraylistFieldVisionInput);
    
}