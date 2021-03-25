package frc.robot.subsystems;



public class TurretSubsystemInterface extends BaseSubsystem{
   
private static final double COORDINATE_OFFSET;
private static final double GEAR_RATIO;
private boolean turretHomedAlready;


public void homeTurret();
public void setTurretPosition(double angle);
public double getTurretError();
public boolean isLimitHit();
public void adjustActualPosition();
public double pidOutput();


}