package frc.robot.commands;

import frc.robot.subsystems.TurretSubsystem;

public class AdjustTurretLeftCommand extends EntechCommandBase{

    private TurretSubsystem turret;
    public AdjustTurretLeftCommand(TurretSubsystem turret){
        super(turret);
        this.turret = turret;
    }
    @Override
    public void execute() {
        turret.adjustTurretLeft();
    }
    @Override
    public void initialize() {
    }
}

