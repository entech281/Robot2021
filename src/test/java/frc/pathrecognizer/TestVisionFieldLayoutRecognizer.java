package frc.pathrecognizer;

import org.junit.Test;
import java.util.List;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

public class TestVisionFieldLayoutRecognizer {
    @Test
    public void testVisionFieldLayoutRecognizer(){

        AutonomousPath autonomousPath = new AutonomousPath(VisionPathName.PathBlueA);
        VisionFieldLayoutRecognizer visionFieldLayoutRecognizer = new VisionFieldLayoutRecognizer();
        
        // https://stackoverflow.com/questions/322715/when-to-use-linkedlist-over-arraylist-in-java
        List<FieldVisionInput> fieldVisionInputList = new ArrayList<FieldVisionInput>();

        FieldVisionInput fieldVisionInput = new FieldVisionInput(1,2,30,40,25);
        fieldVisionInputList.add(fieldVisionInput);

        fieldVisionInput = new FieldVisionInput(1,2,30,40,25);
        fieldVisionInputList.add(fieldVisionInput);

        AutonomousPath autonomousPathTestResult = 
            visionFieldLayoutRecognizer.detectPose(fieldVisionInputList);

        assertEquals(autonomousPath, autonomousPathTestResult);


   }
}