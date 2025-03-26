package frc.robot.commands;

import java.nio.file.Path;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.path.PathPlannerPath;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.VisionDriveSystem;


public class DriveToApriltag extends Command{
    
    int aprilTagTarget;

    private VisionDriveSystem m_VisionDriveSystem;
    
    public DriveToApriltag(VisionDriveSystem driveSystem, int aprilTagTarget) {
        this.aprilTagTarget = aprilTagTarget;
        m_VisionDriveSystem = driveSystem;
        addRequirements(m_VisionDriveSystem);
    }
    
    @Override
    public void initialize() {
        PathPlannerPath path = m_VisionDriveSystem.getPathToVisionTarget(aprilTagTarget);
        AutoBuilder.followPath(path);
    }
    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        // is there a way to stop the robot?
    }

    public void stopDriveToApriltag() {
        // how to stop a path?
    }
}
