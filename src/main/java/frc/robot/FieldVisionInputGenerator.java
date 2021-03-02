package frc.robot;

import java.util.ArrayList;

public class FieldVisionInputGenerator {

    private FieldVisionInput objectFieldVisionInput;
    private ArrayList<FieldVisionInput> objectArrayFieldVisionInput;
    private PixyCameraConnector objectPixyCameraConnector;

    public FieldVisionInputGenerator(){
        
        objectArrayFieldVisionInput = new ArrayList<FieldVisionInput>();
        objectPixyCameraConnector = new PixyCameraConnector();

        objectArrayFieldVisionInput = objectPixyCameraConnector.getFieldVisionInput();

    }

    // public FieldVisionInputGenerator(int xValue, int yValue,
    // int objWidth, int objHeight, int objAngle){

    //     objectFieldVisionInput = new FieldVisionInput(xValue, yValue,
    //                                 objWidth, objHeight, objAngle);

    //     objectArrayFieldVisionInput.add(objectFieldVisionInput);

    // }

    public FieldVisionInput getObjectFieldVisionInput() {
        return objectFieldVisionInput;
    }

    public void setObjectFieldVisionInput(FieldVisionInput objectFieldVisionInput) {
        this.objectFieldVisionInput = objectFieldVisionInput;
    }

    public ArrayList<FieldVisionInput> getFieldVisionInputBlocks(){
        return objectArrayFieldVisionInput;
    }



}