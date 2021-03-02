package frc.robot;

public class FieldVisionInput{

    private int xValue = 0;         // Not sure if i will need this in my calculation, looks like (x-cord)
    private int yValue = 0;         // Not sure if i will need this in my calculation, looks like (y-cord)
    private int objectWidth = 0;    // Width of the object we have programmed the Pixy to see
    private int objectHeight = 0;   // Height of the object we have programmed the Pixy to see
    private int objectAngle = 0;    // Angle at which the Pixy to sees the object

    public FieldVisionInput(int xValue, int yValue,
    int objWidth, int objHeight, int objAngle){
        this.xValue  = xValue;
        this.yValue =  yValue;
        this.objectWidth = objWidth;
        this.objectHeight = objHeight;
        this.objectAngle = objAngle;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + objectAngle;
        result = prime * result + objectHeight;
        result = prime * result + objectWidth;
        result = prime * result + xValue;
        result = prime * result + yValue;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        FieldVisionInput other = (FieldVisionInput) obj;

        if (objectAngle != other.objectAngle)
            return false;
        if (objectHeight != other.objectHeight)
            return false;
        if (objectWidth != other.objectWidth)
            return false;
        if (xValue != other.xValue)
            return false;
        if (yValue != other.yValue)
            return false;
            
        return true;
    }

    public int getxValue() {
        return xValue;
    }

    public int getyValue() {
        return yValue;
    }

    public int getObjectWidth() {
        return objectWidth;
    }

    public int getObjectHeight() {
        return objectHeight;
    }

    public int getObjectAngle() {
        return objectAngle;
    }


}