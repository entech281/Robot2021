package frc.robot.commands;

import frc.robot.subsystems.TurretSubsystem;

public class AdjustTurretRightCommand extends EntechCommandBase {
    private TurretSubsystem turret;
    private double speed;
    public AdjustTurretRightCommand(TurretSubsystem turret, double speed){
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