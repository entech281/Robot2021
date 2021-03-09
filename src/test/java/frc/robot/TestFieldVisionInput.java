package frc.robot;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestFieldVisionInput {
    
    @Test
    public void testFieldVisionInput(){
        
        FieldVisionInput fieldVisionInput = new FieldVisionInput(1,2,30,40,25);
        boolean result = fieldVisionInput.equals(fieldVisionInput);

        assertEquals(true, result);
        
   }

}