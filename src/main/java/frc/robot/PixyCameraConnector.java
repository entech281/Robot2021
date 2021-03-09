package frc.robot;

import java.util.List;
import java.util.ArrayList;
import io.github.pseudoresonance.pixy2api.*;
import io.github.pseudoresonance.pixy2api.links.*;
import io.github.pseudoresonance.pixy2api.Pixy2CCC.Block;
import  edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PixyCameraConnector {

    private Pixy2 pixy;    

    public PixyCameraConnector() {

      pixy = Pixy2.createInstance(new SPILink());
      pixy.init();
      //pixy.setLamp((byte) 1, (byte) 1); // Turns the LEDs on
		  //pixy.setLED(200, 30, 255); // Sets the RGB LED to purple
      //pixy.getCCC().getBlocks( false , 255 , 255 );  
      
    }

    public List<FieldVisionInput> getFieldVisionInput() {

      int blockCount;
      List<Block> blocks;
      List<FieldVisionInput> fieldVisionInputlist = new ArrayList<FieldVisionInput>();
      FieldVisionInput fieldVisionInput;

      //assign the data to an ArrayList for convinience      
      blockCount = pixy.getCCC().getBlocks();
      SmartDashboard.putNumber("blockcount", blockCount);

      
      if (blockCount <= 0) {
        return fieldVisionInputlist; // If blocks were not found, stop processing
      }
      
      blocks = pixy.getCCC().getBlockCache();
      
      if (blocks.size() >  0 )   
      {
        for(int count = 0; count < blocks.size(); count++){
          
          fieldVisionInput = new FieldVisionInput(
            blocks.get(count).getX(),
            blocks.get(count).getY(),
            blocks.get(count).getWidth(),
            blocks.get(count).getHeight(),
            blocks.get(count).getAngle()
          );

          fieldVisionInputlist.add(fieldVisionInput);
        }
      }

        return fieldVisionInputlist;

    }

    
    
}

