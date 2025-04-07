package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.LimelightHelpers;


public class SetLEDMode extends Command{
        String limelightName;
    public SetLEDMode(String limelightName) {
        this.limelightName = limelightName;
    }

    @Override
    public void initialize() {
        LimelightHelpers.setLEDMode_ForceOn(limelightName);
    }
    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        LimelightHelpers.setLEDMode_ForceOff(limelightName);
    }
}
