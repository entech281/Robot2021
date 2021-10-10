package frc.robot.preferences;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class SmartDashboardPathChooser{

    private SendableChooser<Integer> chooser = new SendableChooser<>();

    public SmartDashboardPathChooser(){
        chooser.setDefaultOption("Shoot and back up", AutoOption.ShootAndBackUp);
        chooser.addOption("Middle 6 ball", AutoOption.MiddleSixBall);
        chooser.addOption("Left 7 ball", AutoOption.LeftSevenBall);
        SmartDashboard.putData("auto paths", chooser);
    }

    public int getSelected(){
        return chooser.getSelected();
    }    
}