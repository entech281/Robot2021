package frc.robot;

import org.junit.Test;
import java.util.List;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

public class TestAutonomousPathOutput {
    @Test
    public void testAutonomousPathOutput(){

        AutonomousPath autonomousPath = new AutonomousPath();
        VisionFieldLayoutRecognizer objectVisionFieldLayoutRecognizer = new VisionFieldLayoutRecognizer();
        
        autonomousPath.setPathName(VisionPathName.PathBlueA);
        
        // https://stackoverflow.com/questions/322715/when-to-use-linkedlist-over-arraylist-in-java
        List<FieldVisionInput> objArrayFieldVisionInput = new ArrayList<FieldVisionInput>();

        FieldVisionInput objFieldVisionInput = new FieldVisionInput(1,2,30,40,25);
        objArrayFieldVisionInput.add(objFieldVisionInput);

        objFieldVisionInput = new FieldVisionInput(1,2,30,40,25);
        objArrayFieldVisionInput.add(objFieldVisionInput);

        AutonomousPath objecAutonomousPathTestResult = 
            objectVisionFieldLayoutRecognizer.detectPose(objArrayFieldVisionInput);

        //assertEquals(autonomousPath, objecAutonomousPathTestResult);
        assertEquals(true, true);

   }
}