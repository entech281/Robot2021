package frc.robot;

import io.github.pseudoresonance.pixy2api.*;
import io.github.pseudoresonance.pixy2api.links.*;

// tag::code[]
//@Data
public class pixylightintialization {
    private static Pixy2 pixy;


    

    public  pixylightintialization() {
      pixy = Pixy2.createInstance(new SPILink());
      pixy.init();
      pixy.setLamp((byte) 1, (byte) 1); // Turns the LEDs on
		  pixy.setLED(200, 30, 255); // Sets the RGB LED to purple

    }

    
    private void Main() {

    double dblFocalLength = 11.33;//pixels = 3mm
    double dblWidth = 7; //inches
    double dblDistance = 0; 
    double dblAverageDistance = 0;

    // This is the input array that will hold the values provided by the pixie cam 
    // regarding the pixels from each ball seen
    double[] dblArrPixels = {5.0,13.46,10.31};
    

    // Just for refernce the formula to calculate the focal length
    //dblFocalLength = (dblPixels*dblDistance)/dblWidth;
    
    // For loop that adds the distance of the three balls
    for (int i = 0; i < dblArrPixels.length; i++) {
       dblDistance = dblDistance + (dblWidth*dblFocalLength)/dblArrPixels[i];

    }
    
    // Finding the average of the distance
    dblAverageDistance = dblDistance/3;

  // I am comparing the above calculated value with the averages i calculated using the given 
  // galactic search measurements.
 
  if (dblAverageDistance > 8.50 && dblAverageDistance < 10.0){
    System.out.println("Path A - red");
  }
  else if (dblAverageDistance > 16.10 && dblAverageDistance < 16.30){
    System.out.println("Path A - blue");
  }
  else if (dblAverageDistance > 10.30 && dblAverageDistance < 10.40){
    System.out.println("Path B - red");
  }
  else if (dblAverageDistance > 17.50 && dblAverageDistance < 17.80){
    System.out.println("Path B - blue");
  } 
     
}

}

