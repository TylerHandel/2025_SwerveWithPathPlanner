package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.VisionDriveSystem;

public class DriveToReefApriltag extends Command{
    
    private VisionDriveSystem m_VisionDriveSystem;
    
    public DriveToReefApriltag(VisionDriveSystem visionDriveSystem, int apriltagNum) {
        m_VisionDriveSystem = visionDriveSystem;
        addRequirements(m_VisionDriveSystem);
    }

    @Override
    public void initialize() {
        
    }
    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        
    }

  
}
