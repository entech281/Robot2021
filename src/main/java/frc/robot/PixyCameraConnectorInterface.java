package frc.robot;

import java.util.ArrayList;
import io.github.pseudoresonance.pixy2api.*;
import io.github.pseudoresonance.pixy2api.links.*;
import io.github.pseudoresonance.pixy2api.Pixy2CCC.Block;

public interface PixyCameraConnectorInterface {

    public Pixy2  PixyCameraConnector();
    public ArrayList<FieldVisionInput> getFieldVisionInput();

}