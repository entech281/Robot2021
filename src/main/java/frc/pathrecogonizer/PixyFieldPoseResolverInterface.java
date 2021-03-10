package frc.pathrecogonizer;

import java.util.List;

public interface PixyFieldPoseResolverInterface{

    public AutonomousPath detectPose(List<FieldVisionInput> arraylistFieldVisionInput);
    
}