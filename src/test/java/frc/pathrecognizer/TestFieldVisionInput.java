package frc.pathrecognizer;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class TestFieldVisionInput {
    
    @Test
    public void testFieldVisionInput(){
        
        FieldVisionInput fieldVisionInput = new FieldVisionInput(1,2,30,40,25);
        boolean result = fieldVisionInput.equals(fieldVisionInput);

        assertTrue(result == true);
        
   }

}