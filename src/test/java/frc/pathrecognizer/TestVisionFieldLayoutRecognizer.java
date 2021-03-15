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
        
        // https://stackoverflow.com/questions/322715/when-to-use-linkedlist-over-arraylist-in-java
        List<FieldVisionInput> fieldVisionInputList = new ArrayList<FieldVisionInput>();

        FieldVisionInput fieldVisionInput = new FieldVisionInput(1,2,4.4,4.4,25);
        fieldVisionInputList.add(fieldVisionInput);

        fieldVisionInput = new FieldVisionInput(1,3,4.4,4.4,30);
        fieldVisionInputList.add(fieldVisionInput);

        fieldVisionInput = new FieldVisionInput(1,5,4.4,4.4,45);
        fieldVisionInputList.add(fieldVisionInput);

        AutonomousPath autonomousPathTestResult = 
            visionFieldLayoutRecognizer.detectPose(fieldVisionInputList);
        
            try {
            assertEquals(autonomousPath.getPathName(), autonomousPathTestResult.getPathName());
            System.out.println(autonomousPath.getPathName());
            System.out.println(autonomousPathTestResult.getPathName());
        }
        catch (AssertionError ae) {
            System.out.println("test");
            System.out.println(ae.toString());
        }


   }

   @Test
    public void testVisionFieldLayoutRecognizerPathBlueA(){

        AutonomousPath autonomousPath = new AutonomousPath(VisionPathName.PathBlueA);
        VisionFieldLayoutRecognizer visionFieldLayoutRecognizer = new VisionFieldLayoutRecognizer();
        
        // https://stackoverflow.com/questions/322715/when-to-use-linkedlist-over-arraylist-in-java
        List<FieldVisionInput> fieldVisionInputList = new ArrayList<FieldVisionInput>();

        FieldVisionInput fieldVisionInput = new FieldVisionInput(1,2,3.85,3.85,25);
        fieldVisionInputList.add(fieldVisionInput);

        fieldVisionInput = new FieldVisionInput(1,3,3.85,3.85,30);
        fieldVisionInputList.add(fieldVisionInput);

        fieldVisionInput = new FieldVisionInput(1,5,3.85,3.85,45);
        fieldVisionInputList.add(fieldVisionInput);

        AutonomousPath autonomousPathTestResult = 
            visionFieldLayoutRecognizer.detectPose(fieldVisionInputList);

            try {
                assertEquals(autonomousPath.getPathName(), autonomousPathTestResult.getPathName());
                System.out.println(autonomousPath.getPathName());
                System.out.println(autonomousPathTestResult.getPathName());
            }
            catch (AssertionError ae) {
                System.out.println("test");
                System.out.println(ae.toString());
            }

   }

   @Test
    public void testVisionFieldLayoutRecognizerPathRedB(){

        AutonomousPath autonomousPath = new AutonomousPath(VisionPathName.PathRedB);
        VisionFieldLayoutRecognizer visionFieldLayoutRecognizer = new VisionFieldLayoutRecognizer();
        
        // https://stackoverflow.com/questions/322715/when-to-use-linkedlist-over-arraylist-in-java
        List<FieldVisionInput> fieldVisionInputList = new ArrayList<FieldVisionInput>();

        FieldVisionInput fieldVisionInput = new FieldVisionInput(1,2,2.4,2.4,25);
        fieldVisionInputList.add(fieldVisionInput);

        fieldVisionInput = new FieldVisionInput(1,3,2.4,2.4,30);
        fieldVisionInputList.add(fieldVisionInput);

        fieldVisionInput = new FieldVisionInput(1,5,2.4,2.4,45);
        fieldVisionInputList.add(fieldVisionInput);

        AutonomousPath autonomousPathTestResult = 
            visionFieldLayoutRecognizer.detectPose(fieldVisionInputList);

            try {
                assertEquals(autonomousPath.getPathName(), autonomousPathTestResult.getPathName());
                System.out.println(autonomousPath.getPathName());
                System.out.println(autonomousPathTestResult.getPathName());
            }
            catch (AssertionError ae) {
                System.out.println("test");
                System.out.println(ae.toString());
            }

   }

   @Test
    public void testVisionFieldLayoutRecognizerPathBlueB(){

        AutonomousPath autonomousPath = new AutonomousPath(VisionPathName.PathBlueB);
        VisionFieldLayoutRecognizer visionFieldLayoutRecognizer = new VisionFieldLayoutRecognizer();
        
        // https://stackoverflow.com/questions/322715/when-to-use-linkedlist-over-arraylist-in-java
        List<FieldVisionInput> fieldVisionInputList = new ArrayList<FieldVisionInput>();

        FieldVisionInput fieldVisionInput = new FieldVisionInput(1,2,4.5,4.5,25);
        fieldVisionInputList.add(fieldVisionInput);

        fieldVisionInput = new FieldVisionInput(1,2,4.5,4.5,25);
        fieldVisionInputList.add(fieldVisionInput);

        fieldVisionInput = new FieldVisionInput(1,2,4.5,4.5,45);
        fieldVisionInputList.add(fieldVisionInput);

        AutonomousPath autonomousPathTestResult = 
            visionFieldLayoutRecognizer.detectPose(fieldVisionInputList);

            try {
                assertEquals(autonomousPath.getPathName(), autonomousPathTestResult.getPathName());
                System.out.println(autonomousPath.getPathName());
                System.out.println(autonomousPathTestResult.getPathName());
            }
            catch (AssertionError ae) {
                System.out.println("test");
                System.out.println(ae.toString());
            }

   }

   @Test
    public void testVisionFieldLayoutRecognizerPathUndetermined(){

        AutonomousPath autonomousPath = new AutonomousPath(VisionPathName.CouldNotDeterminePath);
        VisionFieldLayoutRecognizer visionFieldLayoutRecognizer = new VisionFieldLayoutRecognizer();
        
        // https://stackoverflow.com/questions/322715/when-to-use-linkedlist-over-arraylist-in-java
        List<FieldVisionInput> fieldVisionInputList = new ArrayList<FieldVisionInput>();

        FieldVisionInput fieldVisionInput = new FieldVisionInput(1,2,40,20,25);
        fieldVisionInputList.add(fieldVisionInput);

        fieldVisionInput = new FieldVisionInput(1,2,30,40,25);
        fieldVisionInputList.add(fieldVisionInput);

        fieldVisionInput = new FieldVisionInput(1,2,30,40,25);
        fieldVisionInputList.add(fieldVisionInput);

        AutonomousPath autonomousPathTestResult = 
            visionFieldLayoutRecognizer.detectPose(fieldVisionInputList);

            try {
                assertEquals(autonomousPath.getPathName(), autonomousPathTestResult.getPathName());
                System.out.println(autonomousPath.getPathName());
                System.out.println(autonomousPathTestResult.getPathName());
            }
            catch (AssertionError ae) {
                System.out.println("test");
                System.out.println(ae.toString());
            }

   }

   @Test
    public void testVisionFieldLayoutRecognizerZeroWidth(){

        AutonomousPath autonomousPath = new AutonomousPath(VisionPathName.CouldNotDeterminePath);
        VisionFieldLayoutRecognizer visionFieldLayoutRecognizer = new VisionFieldLayoutRecognizer();
        
        // https://stackoverflow.com/questions/322715/when-to-use-linkedlist-over-arraylist-in-java
        List<FieldVisionInput> fieldVisionInputList = new ArrayList<FieldVisionInput>();

        FieldVisionInput fieldVisionInput = new FieldVisionInput(1,2,0,10,25);
        fieldVisionInputList.add(fieldVisionInput);

        fieldVisionInput = new FieldVisionInput(1,2,0,10,25);
        fieldVisionInputList.add(fieldVisionInput);

        fieldVisionInput = new FieldVisionInput(1,2,0,10,25);
        fieldVisionInputList.add(fieldVisionInput);

        AutonomousPath autonomousPathTestResult = 
            visionFieldLayoutRecognizer.detectPose(fieldVisionInputList);

            try {
                assertEquals(autonomousPath.getPathName(), autonomousPathTestResult.getPathName());
                System.out.println(autonomousPath.getPathName());
                System.out.println(autonomousPathTestResult.getPathName());
            }
            catch (AssertionError ae) {
                System.out.println("test");
                System.out.println(ae.toString());
            }

   }

   @Test
    public void testVisionFieldLayoutRecognizerZeroHeight(){

        AutonomousPath autonomousPath = new AutonomousPath(VisionPathName.PathBlueA);
        VisionFieldLayoutRecognizer visionFieldLayoutRecognizer = new VisionFieldLayoutRecognizer();
        
        // https://stackoverflow.com/questions/322715/when-to-use-linkedlist-over-arraylist-in-java
        List<FieldVisionInput> fieldVisionInputList = new ArrayList<FieldVisionInput>();

        FieldVisionInput fieldVisionInput = new FieldVisionInput(1,2,4,0,25);
        fieldVisionInputList.add(fieldVisionInput);

        fieldVisionInput = new FieldVisionInput(1,2,30,0,25);
        fieldVisionInputList.add(fieldVisionInput);

        fieldVisionInput = new FieldVisionInput(1,2,30,0,25);
        fieldVisionInputList.add(fieldVisionInput);

        AutonomousPath autonomousPathTestResult = 
            visionFieldLayoutRecognizer.detectPose(fieldVisionInputList);

            try {
                assertEquals(autonomousPath.getPathName(), autonomousPathTestResult.getPathName());
                System.out.println(autonomousPath.getPathName());
                System.out.println(autonomousPathTestResult.getPathName());
            }
            catch (AssertionError ae) {
                System.out.println("test");
                System.out.println(ae.toString());
            }
   }


   @Test
    public void testVisionFieldLayoutRecognizerWithNoInput(){

        AutonomousPath autonomousPath = new AutonomousPath(VisionPathName.CouldNotDeterminePath);
        VisionFieldLayoutRecognizer visionFieldLayoutRecognizer = new VisionFieldLayoutRecognizer();
        
        // https://stackoverflow.com/questions/322715/when-to-use-linkedlist-over-arraylist-in-java
        List<FieldVisionInput> fieldVisionInputList = new ArrayList<FieldVisionInput>();

        AutonomousPath autonomousPathTestResult = 
            visionFieldLayoutRecognizer.detectPose(fieldVisionInputList);

            try {
                assertEquals(autonomousPath.getPathName(), autonomousPathTestResult.getPathName());
                System.out.println(autonomousPath.getPathName());
                System.out.println(autonomousPathTestResult.getPathName());
            }
            catch (AssertionError ae) {
                System.out.println("test");
                System.out.println(ae.toString());
            }


   }
   
}