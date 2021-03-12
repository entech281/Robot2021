package frc.pathrecogonizer;

import java.util.List;
import java.util.ArrayList;
import io.github.pseudoresonance.pixy2api.*;
import io.github.pseudoresonance.pixy2api.links.*;
import io.github.pseudoresonance.pixy2api.Pixy2CCC.Block;
//import  edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PixyCameraConnector {

    private Pixy2 pixy;    

    public PixyCameraConnector() {

      pixy = Pixy2.createInstance(new SPILink());
      pixy.init();

      // Leaving the below lines for testing when we connect to roborio
      // to see if the lights work.
      //pixy.setLamp((byte) 1, (byte) 1); // Turns the LEDs on
		  //pixy.setLED(200, 30, 255); // Sets the RGB LED to purple
      //pixy.getCCC().getBlocks( false , 255 , 255 );  
      
    }

    public List<FieldVisionInput> getFieldVisionInput() {

      List<FieldVisionInput> fieldVisionInputlist = new ArrayList<FieldVisionInput>();
      FieldVisionInput fieldVisionInput;

      // Not sure if we need the below code for the 
      // getBlockCache to work so leaving it in here.
      //pixy.getCCC().getBlocks();
      
      List<Block> blocks = pixy.getCCC().getBlockCache();
      
      // Not sure how to modify this loop
      if (blocks.size() >  0 )   
      {
        //for(int count = 0; count < blocks.size(); count++){
          for(Block block: blocks){
          
          fieldVisionInput = new FieldVisionInput(
            block.getX(),
            block.getY(),
            block.getWidth(),
            block.getHeight(),
            block.getAngle()
          );

          fieldVisionInputlist.add(fieldVisionInput);
        }
      }

        return fieldVisionInputlist;

    }

    
    
}

