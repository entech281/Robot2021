package frc.robot;

import java.util.ArrayList;
import io.github.pseudoresonance.pixy2api.*;
import io.github.pseudoresonance.pixy2api.links.*;
import io.github.pseudoresonance.pixy2api.Pixy2CCC.Block;
import  edu.wpi.first.wpilibj.TimedRobot;
import  edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


// tag::code[]
//@Data
public class PixyCameraConnector implements PixyCameraConnectorInterface{

    private Pixy2 pixy;    

    public Pixy2  PixyCameraConnector() {

      //pixy = new Pixy2();
      pixy = Pixy2.createInstance(new SPILink());
      pixy.init();
      return pixy;

    }

    public ArrayList<FieldVisionInput> getFieldVisionInput() {

      double  xCoord = 0.0;
      double yCoord = 0.0;
      int imageAngle = 0;
      int imageHeight = 0;
      int imageWidth = 0;
      int blockCount = 0;
      ArrayList<Block> blocks;
      ArrayList<FieldVisionInput> arraylistFieldVisionInput = new ArrayList<FieldVisionInput>();
      FieldVisionInput objectFieldVisionInput;
      
      //+pixy.setLamp((byte) 1, (byte) 1); // Turns the LEDs on
		  //pixy.setLED(200, 30, 255); // Sets the RGB LED to purple
      //pixy.getCCC().getBlocks( false , 255 , 255 );  

      //assign the data toan ArrayList for convinience      
      blockCount = pixy.getCCC().getBlocks();
      
      if (blockCount <= 0) {
        return null; // If blocks were not found, stop processing
      }
      
      blocks = pixy.getCCC().getBlockCache();
      
      if (blocks.size() >  0 )   
      {
        for(int count = 0; count < blocks.size(); count++){
          
          objectFieldVisionInput = new FieldVisionInput();

          objectFieldVisionInput.xValue = blocks.get(count).getX();        // x position of the largesttarget
          objectFieldVisionInput.yValue = blocks.get(count).getY();        // y position of the largesttarget  
          objectFieldVisionInput.objectAngle = blocks.get(count).getAngle();// angle at which the camera sees the image
          objectFieldVisionInput.objectHeight = blocks.get(count).getHeight(); // Image height
          objectFieldVisionInput.objectWidth = blocks.get(count).getWidth(); // ImageWidth
        
          // examples     
          SmartDashboard.putBoolean( "present" ,  true );    
          SmartDashboard.putNumber( "Xccord" ,0);     
          SmartDashboard.putNumber( "Ycoord" , 0);     
          SmartDashboard.putString( "Data" , "" );   

          arraylistFieldVisionInput.add(objectFieldVisionInput);
        }
      }
      else     
        SmartDashboard.putBoolean( "present" ,  false );

        return arraylistFieldVisionInput;

    }

    
    
}

