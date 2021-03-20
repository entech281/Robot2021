package frc.pathrecognizer;

import java.util.List;
import java.util.ArrayList;
import io.github.pseudoresonance.pixy2api.*;
import io.github.pseudoresonance.pixy2api.links.*;
import io.github.pseudoresonance.pixy2api.Pixy2CCC.Block;
import frc.robot.logger.DataLogger;
import frc.robot.logger.DataLoggerFactory;

public class PixyCameraConnector {

  private DataLogger logger = DataLoggerFactory.getLoggerFactory().createDataLogger(PixyCameraConnector.class.getName()); 
    private Pixy2 pixy;  
    private boolean pixyConnectionStatus = false;  

    public PixyCameraConnector() {

      try {
        pixy = Pixy2.createInstance(new SPILink());
        pixy.init();
        setPixyConnectionStatus(true);
      }
      catch (Exception ex){
        setPixyConnectionStatus(false);
        ex.printStackTrace();
      }
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
      pixy.getCCC().getBlocks();
      
      List<Block> blocks = pixy.getCCC().getBlockCache();
      int count = 1;
        for(Block block: blocks){
          
          fieldVisionInput = new FieldVisionInput(
            block.getX(),
            block.getY(),
            block.getWidth(),
            block.getHeight(),
            block.getAngle()
          );

          switch  (count){
          case 1:
          {
          logger.log("Pixel Width 1:", block.getWidth());
          logger.log("PixelHeight 1:", block.getHeight());
          break;
          }
          case 2:
          {
          logger.log("Pixel Width 2: ", block.getWidth());
          logger.log("PixelHeight: 2: ", block.getHeight());
          break;
          }
          case 3:
          {
          logger.log("Pixel Width 3: ", block.getWidth());
          logger.log("PixelHeight 3: ", block.getHeight());
          break;
          }
        }

          count = count + 1;

          fieldVisionInputlist.add(fieldVisionInput);
        }
      

        return fieldVisionInputlist;

    }

    public boolean getPixyConnectionStatus() {
      return pixyConnectionStatus;
    }

    public void setPixyConnectionStatus(boolean pixyConnectionStatus) {
      this.pixyConnectionStatus = pixyConnectionStatus;
    }

    
    
}

