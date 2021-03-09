package frc.robot;

import java.util.List;
import java.util.ArrayList;

public class FieldVisionInputGenerator {

    private List<FieldVisionInput> fieldVisionInputsList;
    private PixyCameraConnector pixyCameraConnector;

    public List<FieldVisionInput> getFieldVisionInput(){
        
        fieldVisionInputsList = new ArrayList<FieldVisionInput>();
        pixyCameraConnector = new PixyCameraConnector();

        fieldVisionInputsList = pixyCameraConnector.getFieldVisionInput();

        return fieldVisionInputsList;

    }

}