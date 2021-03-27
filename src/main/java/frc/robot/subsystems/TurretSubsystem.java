package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.RobotConstants;
import frc.robot.controllers.TalonPositionController;
import frc.robot.utils.ClampedDouble;

public class TurretSubsystem extends BaseSubsystem{

        private WPI_TalonSRX turretMotor;   
        private TalonPositionController turretMotorController; 
        private static boolean isLimitHit = false;
        private final ClampedDouble turretPositioner = ClampedDouble.builder().build();

        @Override
        public void initialize() {

                turretMotor = new WPI_TalonSRX(RobotConstants.CAN.TURRET_MOTOR);
                turretMotorController = new TalonPositionController(turretMotor, 
                                                frc.robot.RobotConstants.MOTOR_SETTINGS.TURRET, true);
                turretMotorController.configure();                
                turretMotor.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen,
                        0);
                turretMotor.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen,
                        0);
                turretMotor.overrideLimitSwitchesEnable(true);

        }

        // Sets the appropriate output on the talon, depending on the mode. 
        public void homeTurret(){
        /*move towards and hit limit switch to know position.
         In Position mode, output value is in encoder ticks or an analog value, depending on the sensor
        I am setting the value to 0 for now*/
        turretMotor.set(ControlMode.PercentOutput, 0);

        }
        public void setTurretAngle(double angle){
                /*give an angle*/
                turretPositioner.setValue(angle);
                turretMotorController.setDesiredPosition(angle);
        }
        public void resetTurretPosition(){
                turretMotorController.resetPosition();
        }
        public double getTurretError(){
        /* it's calculus and PID so I got it, by tomorrow night.*/
        return 0; //just for now
        }
        public boolean isLimitHit(){
                /*boolean of whether limit switch is hit*/
                isLimitHit = false;
                if ((turretMotor.getSensorCollection().isRevLimitSwitchClosed()) &&
                        (turretMotor.getSensorCollection().isFwdLimitSwitchClosed())){
                                isLimitHit = true;
                        }

                return isLimitHit;
        }
        public void adjustActualPosition(){
        /*use error and adjust angle, succeeds the next method*/
        }
        public double pidOutput(){
        /*again, adjusting voltage after error calc, I got this part, by tomorrow night*/
                turretMotor.setVoltage(0); // setting it to zero for now
                return 0; // just for now
        }
}
