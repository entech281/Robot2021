/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.ArrayList;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import org.junit.runner.JUnitCore;
// import org.junit.runner.Result;
// import org.junit.runner.notification.Failure;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
     
     @Override
     public void teleopPeriodic(){

          //PixyCameraConnector objectPixyCameraConnector = new PixyCameraConnector();
          VisionFieldLayoutRecognizer objectVisionFieldLayoutRecognizer = new VisionFieldLayoutRecognizer();
          PixyCameraConnector objPixyCameraConnector = new PixyCameraConnector();

          SmartDashboard.putNumber("Number Found", 11);

          ArrayList<FieldVisionInput> arraylistFieldVisionInput = 
                    objPixyCameraConnector.getFieldVisionInput();

          objectVisionFieldLayoutRecognizer.detectPose(arraylistFieldVisionInput);
          
          
     }
  
}
