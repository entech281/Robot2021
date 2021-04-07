package frc.robot.commands;

import frc.robot.subsystems.TurretSubsystem;

public class AdjustTurretCounterClockwiseCommand extends EntechCommandBase{

    private TurretSubsystem turret;
    public AdjustTurretCounterClockwiseCommand(TurretSubsystem turret){
        super(turret);
        this.turret = turret;
    }
    @Override
    public void execute() {
        turret.adjustTurretCounterClockwise();
    }
    @Override
    public void initialize() {
    }
}

