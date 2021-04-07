package frc.robot.commands;

import frc.robot.subsystems.TurretSubsystem;

public class AdjustTurretClockwiseCommand extends EntechCommandBase {
    private TurretSubsystem turret;
    public AdjustTurretClockwiseCommand(TurretSubsystem turret){
        super(turret);
        this.turret = turret;
    }
    @Override
    public void execute() {
        turret.adjustTurretClockwise();
    }
    @Override
    public void initialize() {
    }
}