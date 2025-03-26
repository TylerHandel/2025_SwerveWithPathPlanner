package frc.robot.commands;

import java.nio.file.Path;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.path.PathPlannerPath;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.VisionDriveSystem;


public class FollowPathToApriltag extends Command{
    
    int aprilTagTarget;

    private VisionDriveSystem m_VisionDriveSystem;
    private Command pathCommand;  // Store the path command
    
    public FollowPathToApriltag(VisionDriveSystem driveSystem, int aprilTagTarget) {
        this.aprilTagTarget = aprilTagTarget;
        m_VisionDriveSystem = driveSystem;
        addRequirements(m_VisionDriveSystem);
    }
    
    @Override
    public void initialize() {
        PathPlannerPath path = m_VisionDriveSystem.getPathToVisionTarget(aprilTagTarget);
        pathCommand = AutoBuilder.followPath(path);
        pathCommand.schedule();  // Start the path
    }
    @Override
    public boolean isFinished() {
        return pathCommand != null && pathCommand.isFinished();
    }

    @Override
    public void end(boolean interrupted) {
        stopFollowPathToApriltag();
    }

    public void stopFollowPathToApriltag() {
        if (pathCommand != null) {
            pathCommand.cancel();  // Stops the path-following command
            pathCommand = null;    // Reset the command reference
        }
        m_VisionDriveSystem.stop();  // Stop the drivetrain motors
    }
}
