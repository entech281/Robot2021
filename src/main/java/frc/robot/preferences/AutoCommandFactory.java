package frc.robot.preferences;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.commands.CommandFactory;

public class AutoCommandFactory{
    private CommandFactory commandFactory;
    public AutoCommandFactory( CommandFactory commandFactory ){
        this.commandFactory = commandFactory; 
    }
    public  Command getSelectedCommand(int selected){
        switch(selected){
            case AutoOption.MiddleSixBall:
                return commandFactory.simpleForwardShoot3Auto();
            case AutoOption.LeftSevenBall:
                return commandFactory.simpleForwardShoot3Auto();
            case AutoOption.ShootAndBackUp:
                return commandFactory.simpleForwardShoot3Auto();
        }
        return commandFactory.doNothing();
    }
}
