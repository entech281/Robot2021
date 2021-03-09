package frc.robot;

import java.util.List;
import java.util.ArrayList;

public class FieldVisionInputGenerator {

    private FieldVisionInput fieldVisionInput;
    private List<FieldVisionInput> arrayOfFieldVisionInput;
    private PixyCameraConnector pixyCameraConnector;

    public FieldVisionInputGenerator(){
        
        arrayOfFieldVisionInput = new ArrayList<FieldVisionInput>();
        pixyCameraConnector = new PixyCameraConnector();
        arrayOfFieldVisionInput = pixyCameraConnector.getFieldVisionInput();

    }

}