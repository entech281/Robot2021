package frc.robot.commands;

import frc.robot.subsystems.TurretSubsystem;

public class SpinTurretSpeedCommand extends EntechCommandBase {
    private TurretSubsystem turret;
    private double speed;
    public SpinTurretSpeedCommand(TurretSubsystem turret, double speed){
        super(turret);
        this.turret = turret;
        this.speed = speed;
    }
    @Override
    public void execute() {
        turret.turnTurret(speed);
    }
    @Override
    public void initialize() {
    }
}