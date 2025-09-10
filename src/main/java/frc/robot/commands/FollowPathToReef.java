package frc.robot.commands;

import java.nio.file.Path;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.path.PathPlannerPath;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.VisionDriveSystem;


public class FollowPathToReef extends Command{
    
    int aprilTagTarget;

    private VisionDriveSystem m_VisionDriveSystem;
    private Command pathCommand;  // Store the path command
    
    public FollowPathToReef(VisionDriveSystem driveSystem, int aprilTagTarget) {
        this.aprilTagTarget = aprilTagTarget;
        m_VisionDriveSystem = driveSystem;
        addRequirements(m_VisionDriveSystem);
    }
    
    @Override
    public void initialize() {

        PathPlannerPath path = m_VisionDriveSystem.getPathToVisionTarget(aprilTagTarget);

        if (path != null) { // Check if the path is not null
            pathCommand = AutoBuilder.followPath(path);
            pathCommand.schedule();  // Start the path
        } else {
            System.out.println("Error: Path is null. Cannot follow path.");
        }
    }
    @Override
    public boolean isFinished() {
        return pathCommand != null && pathCommand.isFinished();
    }

    @Override
    public void end(boolean interrupted) {
        stop();
    }

    public void stop() {
        if (pathCommand != null) {
            pathCommand.cancel();  // Stops the path-following command
            pathCommand = null;    // Reset the command reference
        }
        m_VisionDriveSystem.stop();  // Stop the drivetrain motors
    }
}
