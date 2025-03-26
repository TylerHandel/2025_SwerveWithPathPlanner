package frc.robot.commands;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.path.PathConstraints;
import com.pathplanner.lib.path.PathPlannerPath;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.VisionDriveSystem;


public class PathfindToApriltagOffset extends Command{
    
    int aprilTagTarget;

    private VisionDriveSystem m_VisionDriveSystem;
    private Command pathCommand;  // Store the path command
    
    public  PathfindToApriltagOffset(VisionDriveSystem driveSystem, int aprilTagTarget) {
        this.aprilTagTarget = aprilTagTarget;
        m_VisionDriveSystem = driveSystem;
        addRequirements(m_VisionDriveSystem);
    }
    
    @Override
    public void initialize() {
        PathPlannerPath targetPath = m_VisionDriveSystem.getPathToVisionTargetOffset(aprilTagTarget);
        PathConstraints targeConstraints = m_VisionDriveSystem.getConstraintsToVisionTargetOffset(aprilTagTarget);

        pathCommand = AutoBuilder.pathfindThenFollowPath(targetPath, targeConstraints);
        pathCommand.schedule();  // Start the path
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
