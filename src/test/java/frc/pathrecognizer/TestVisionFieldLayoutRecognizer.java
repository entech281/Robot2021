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

        FieldVisionInput fieldVisionInput = new FieldVisionInput(1,2,4.4,2,25);
        fieldVisionInputList.add(fieldVisionInput);

        fieldVisionInput = new FieldVisionInput(1,3,4.4,2,30);
        fieldVisionInputList.add(fieldVisionInput);

        fieldVisionInput = new FieldVisionInput(1,5,4.4,2,45);
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

        FieldVisionInput fieldVisionInput = new FieldVisionInput(1,2,3.85,2,25);
        fieldVisionInputList.add(fieldVisionInput);
        fieldVisionInput = new FieldVisionInput(1,3,3.85,2,30);
        fieldVisionInputList.add(fieldVisionInput);
        fieldVisionInput = new FieldVisionInput(1,5,3.85,2,45);
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

        FieldVisionInput fieldVisionInput = new FieldVisionInput(1,2,2.45,2,25);
        fieldVisionInputList.add(fieldVisionInput);
        fieldVisionInput = new FieldVisionInput(1,3,2.45,2,30);
        fieldVisionInputList.add(fieldVisionInput);
        fieldVisionInput = new FieldVisionInput(1,5,2.45,2,45);
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

        FieldVisionInput fieldVisionInput = new FieldVisionInput(1,2,2.25,2,25);
        fieldVisionInputList.add(fieldVisionInput);
        fieldVisionInput = new FieldVisionInput(1,2,2.25,2,25);
        fieldVisionInputList.add(fieldVisionInput);
        fieldVisionInput = new FieldVisionInput(1,2,2.25,2,45);
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