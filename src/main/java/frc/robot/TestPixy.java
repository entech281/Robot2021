package frc.robot;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;


public class TestPixy {
    
    @Test
    public void Pixy(){          

        // Unit test for Input Class
        FieldVisionInput_UnitTest();

        // Unittest for autonomous path output
        AutonomousPathOutput_UnitTest();

    }

    public void FieldVisionInput_UnitTest(){
        
        FieldVisionInput objFieldVisionInput = new FieldVisionInput(1,2,30,40,25);
        boolean result = objFieldVisionInput.equals(objFieldVisionInput);
        
   }

   public void AutonomousPathOutput_UnitTest(){

        AutonomousPath objecAutonomousPath = new AutonomousPath();
        VisionFieldLayoutRecognizer objectVisionFieldLayoutRecognizer = new VisionFieldLayoutRecognizer();
        
        objecAutonomousPath.setPathName(VisionPathName.PathBlueA);
        
        ArrayList<FieldVisionInput> objArrayFieldVisionInput = new ArrayList<FieldVisionInput>();

        FieldVisionInput objFieldVisionInput = new FieldVisionInput(1,2,30,40,25);
        objArrayFieldVisionInput.add(objFieldVisionInput);

        objFieldVisionInput = new FieldVisionInput(1,2,30,40,25);
        objArrayFieldVisionInput.add(objFieldVisionInput);

        AutonomousPath objecAutonomousPathTestResult = 
            objectVisionFieldLayoutRecognizer.detectPose(objArrayFieldVisionInput);

        assertEquals(objecAutonomousPath, objecAutonomousPathTestResult);

   }


}