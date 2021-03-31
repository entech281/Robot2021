package frc.robot.commands;

import frc.robot.subsystems.TurretSubsystem;

public class AdjustTurretRightCommand extends EntechCommandBase {
    private TurretSubsystem turret;

    public AdjustTurretRightCommand(TurretSubsystem turret){
        super(turret);
        this.turret = turret;
    }
    @Override
    public void execute() {
        turret.adjustTurretRight();
    }
    @Override
    public void initialize() {
    }
}