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

    public PixyCameraConnector() {

      //pixy = new Pixy2();
      pixy = Pixy2.createInstance(new SPILink());
      pixy.init();
      //pixy.setLamp((byte) 1, (byte) 1); // Turns the LEDs on
		  //pixy.setLED(200, 30, 255); // Sets the RGB LED to purple
      
    }

    public ArrayList<FieldVisionInput> getFieldVisionInput() {

      int blockCount;
      ArrayList<Block> blocks;
      ArrayList<FieldVisionInput> arraylistFieldVisionInput = new ArrayList<FieldVisionInput>();
      FieldVisionInput objectFieldVisionInput;
      
      //+pixy.setLamp((byte) 1, (byte) 1); // Turns the LEDs on
		  //pixy.setLED(200, 30, 255); // Sets the RGB LED to purple
      //pixy.getCCC().getBlocks( false , 255 , 255 );  

      //assign the data to an ArrayList for convinience      
      blockCount = pixy.getCCC().getBlocks();
      SmartDashboard.putNumber("blockcount", blockCount);

      
      if (blockCount <= 0) {
        return arraylistFieldVisionInput; // If blocks were not found, stop processing
      }
      
      blocks = pixy.getCCC().getBlockCache();

      SmartDashboard.putNumber("BlockSizeFromCache", blocks.size());
      
      if (blocks.size() >  0 )   
      {
        for(int count = 0; count < blocks.size(); count++){
          
          objectFieldVisionInput = new FieldVisionInput(
            blocks.get(count).getX(),
            blocks.get(count).getY(),
            blocks.get(count).getWidth(),
            blocks.get(count).getHeight(),
            blocks.get(count).getAngle()
          );

          // // examples     
          // SmartDashboard.putBoolean( "present" ,  true );    
          // SmartDashboard.putNumber( "Width" ,objectFieldVisionInput.objectWidth);     
          // SmartDashboard.putNumber( "Ycoord" , 0);     
          // SmartDashboard.putString( "Data" , "" );   

          arraylistFieldVisionInput.add(objectFieldVisionInput);
        }
      }
      else     
        SmartDashboard.putBoolean( "present" ,  false );

        return arraylistFieldVisionInput;

    }

    
    
}

