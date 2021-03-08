package frc.robot;

import java.util.ArrayList;

public class FieldVisionInputGenerator {

    private FieldVisionInput objectFieldVisionInput;
    private ArrayList<FieldVisionInput> objectArrayFieldVisionInput;
    private PixyCameraConnector objectPixyCameraConnector;

    public FieldVisionInputGenerator(){
        
        // Here Pixy connection will be created

    }

    public FieldVisionInput getObjectFieldVisionInput() {
        // This function has all code removed except for the ones below 
        // because i need to return a value

        return objectFieldVisionInput;
    }

    public void setObjectFieldVisionInput(FieldVisionInput objectFieldVisionInput) {
        this.objectFieldVisionInput = objectFieldVisionInput;
    }

    public ArrayList<FieldVisionInput> getFieldVisionInputBlocks(){
        return objectArrayFieldVisionInput;
    }



}