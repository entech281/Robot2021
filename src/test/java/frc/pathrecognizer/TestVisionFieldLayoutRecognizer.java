package frc.pathrecognizer;

import org.junit.Test;
import java.util.List;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

public class TestVisionFieldLayoutRecognizer {  
    @Test
    public void testVisionFieldLayoutRecognizerPathRedA(){
        AutonomousPath autonomousPath = new AutonomousPath(VisionPathName.PathRedA);
        VisionFieldLayoutRecognizer visionFieldLayoutRecognizer = new VisionFieldLayoutRecognizer();
        List<FieldVisionInput> fieldVisionInputList = new ArrayList<FieldVisionInput>();

        FieldVisionInput fieldVisionInput = new FieldVisionInput(1,2,17.58,2,25);
        fieldVisionInputList.add(fieldVisionInput);

        fieldVisionInput = new FieldVisionInput(1,3,17.58,2,30);
        fieldVisionInputList.add(fieldVisionInput);

        fieldVisionInput = new FieldVisionInput(1,5,17.58,2,45);
        fieldVisionInputList.add(fieldVisionInput);

        AutonomousPath autonomousPathTestResult = 
            visionFieldLayoutRecognizer.detectPose(fieldVisionInputList);
        
        assertEquals(autonomousPath.getPathName(), autonomousPathTestResult.getPathName());
   }
   @Test
    public void testVisionFieldLayoutRecognizerPathBlueA(){
        AutonomousPath autonomousPath = new AutonomousPath(VisionPathName.PathBlueA);
        VisionFieldLayoutRecognizer visionFieldLayoutRecognizer = new VisionFieldLayoutRecognizer();
        List<FieldVisionInput> fieldVisionInputList = new ArrayList<>();

        FieldVisionInput fieldVisionInput = new FieldVisionInput(1,2,10.84,2,25);
        fieldVisionInputList.add(fieldVisionInput);
        fieldVisionInput = new FieldVisionInput(1,3,10.84,2,30);
        fieldVisionInputList.add(fieldVisionInput);
        fieldVisionInput = new FieldVisionInput(1,5,10.84,2,45);
        fieldVisionInputList.add(fieldVisionInput);

        AutonomousPath autonomousPathTestResult = 
            visionFieldLayoutRecognizer.detectPose(fieldVisionInputList);

        assertEquals(autonomousPath.getPathName(), autonomousPathTestResult.getPathName());
   }
   @Test
    public void testVisionFieldLayoutRecognizerPathRedB(){
        AutonomousPath autonomousPath = new AutonomousPath(VisionPathName.PathRedB);
        VisionFieldLayoutRecognizer visionFieldLayoutRecognizer = new VisionFieldLayoutRecognizer();
        List<FieldVisionInput> fieldVisionInputList = new ArrayList<>();

        FieldVisionInput fieldVisionInput = new FieldVisionInput(1,2,9.14,2,25);
        fieldVisionInputList.add(fieldVisionInput);
        fieldVisionInput = new FieldVisionInput(1,3,9.14,2,30);
        fieldVisionInputList.add(fieldVisionInput);
        fieldVisionInput = new FieldVisionInput(1,5,9.14,2,45);
        fieldVisionInputList.add(fieldVisionInput);

        AutonomousPath autonomousPathTestResult = 
            visionFieldLayoutRecognizer.detectPose(fieldVisionInputList);

        assertEquals(autonomousPath.getPathName(), autonomousPathTestResult.getPathName());
   }
   @Test
    public void testVisionFieldLayoutRecognizerPathBlueB(){
        AutonomousPath autonomousPath = new AutonomousPath(VisionPathName.PathBlueB);
        VisionFieldLayoutRecognizer visionFieldLayoutRecognizer = new VisionFieldLayoutRecognizer();
        List<FieldVisionInput> fieldVisionInputList = new ArrayList<>();

        FieldVisionInput fieldVisionInput = new FieldVisionInput(1,2,8.16,2,25);
        fieldVisionInputList.add(fieldVisionInput);
        fieldVisionInput = new FieldVisionInput(1,2,8.16,2,25);
        fieldVisionInputList.add(fieldVisionInput);
        fieldVisionInput = new FieldVisionInput(1,2,8.16,2,45);
        fieldVisionInputList.add(fieldVisionInput);

        AutonomousPath autonomousPathTestResult = 
            visionFieldLayoutRecognizer.detectPose(fieldVisionInputList);
            
        assertEquals(autonomousPath.getPathName(), autonomousPathTestResult.getPathName());
   }
   @Test
    public void testVisionFieldLayoutRecognizerPathUndetermined(){
        AutonomousPath autonomousPath = new AutonomousPath(VisionPathName.CouldNotDeterminePath);
        VisionFieldLayoutRecognizer visionFieldLayoutRecognizer = new VisionFieldLayoutRecognizer();
        List<FieldVisionInput> fieldVisionInputList = new ArrayList<>();

        FieldVisionInput fieldVisionInput = new FieldVisionInput(1,2,40,20,25);
        fieldVisionInputList.add(fieldVisionInput);
        fieldVisionInput = new FieldVisionInput(1,2,30,40,25);
        fieldVisionInputList.add(fieldVisionInput);
        fieldVisionInput = new FieldVisionInput(1,2,30,40,25);
        fieldVisionInputList.add(fieldVisionInput);

        AutonomousPath autonomousPathTestResult = 
            visionFieldLayoutRecognizer.detectPose(fieldVisionInputList);

        assertEquals(autonomousPath.getPathName(), autonomousPathTestResult.getPathName());
   }
   @Test
    public void testVisionFieldLayoutRecognizerZeroWidth(){
        AutonomousPath autonomousPath = new AutonomousPath(VisionPathName.CouldNotDeterminePath);
        VisionFieldLayoutRecognizer visionFieldLayoutRecognizer = new VisionFieldLayoutRecognizer();
        List<FieldVisionInput> fieldVisionInputList = new ArrayList<>();

        FieldVisionInput fieldVisionInput = new FieldVisionInput(1,2,0,10,25);
        fieldVisionInputList.add(fieldVisionInput);
        fieldVisionInput = new FieldVisionInput(1,2,0,10,25);
        fieldVisionInputList.add(fieldVisionInput);
        fieldVisionInput = new FieldVisionInput(1,2,0,10,25);
        fieldVisionInputList.add(fieldVisionInput);

        AutonomousPath autonomousPathTestResult = 
            visionFieldLayoutRecognizer.detectPose(fieldVisionInputList);

        assertEquals(autonomousPath.getPathName(), autonomousPathTestResult.getPathName());
   }
   @Test
    public void testVisionFieldLayoutRecognizerZeroHeight(){
        AutonomousPath autonomousPath = new AutonomousPath(VisionPathName.CouldNotDeterminePath);
        VisionFieldLayoutRecognizer visionFieldLayoutRecognizer = new VisionFieldLayoutRecognizer();        
        List<FieldVisionInput> fieldVisionInputList = new ArrayList<>();

        FieldVisionInput fieldVisionInput = new FieldVisionInput(1,2,4,0,25);
        fieldVisionInputList.add(fieldVisionInput);
        fieldVisionInput = new FieldVisionInput(1,2,30,0,25);
        fieldVisionInputList.add(fieldVisionInput);
        fieldVisionInput = new FieldVisionInput(1,2,30,0,25);
        fieldVisionInputList.add(fieldVisionInput);

        AutonomousPath autonomousPathTestResult = 
            visionFieldLayoutRecognizer.detectPose(fieldVisionInputList);
        
        assertEquals(autonomousPath.getPathName(), autonomousPathTestResult.getPathName());        
   }
   @Test
    public void testVisionFieldLayoutRecognizerWithNoInput(){
        AutonomousPath autonomousPath = new AutonomousPath(VisionPathName.CouldNotDeterminePath);
        VisionFieldLayoutRecognizer visionFieldLayoutRecognizer = new VisionFieldLayoutRecognizer();
        List<FieldVisionInput> fieldVisionInputList = new ArrayList<>();

        AutonomousPath autonomousPathTestResult = 
            visionFieldLayoutRecognizer.detectPose(fieldVisionInputList);
        //AssertionError ae
        assertEquals(autonomousPath.getPathName(), autonomousPathTestResult.getPathName()); 
   }  
}