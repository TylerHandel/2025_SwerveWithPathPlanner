package frc.robot.commands;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.path.PathConstraints;
import com.pathplanner.lib.path.PathPlannerPath;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.VisionDriveSystem;


public class PathfindToApriltagOffset extends Command{
    
    int aprilTagTarget;

    private VisionDriveSystem m_VisionDriveSystem;
    
    public  PathfindToApriltagOffset(VisionDriveSystem driveSystem, int aprilTagTarget) {
        this.aprilTagTarget = aprilTagTarget;
        m_VisionDriveSystem = driveSystem;
        addRequirements(m_VisionDriveSystem);
    }
    
    @Override
    public void initialize() {
        PathPlannerPath targetPath = m_VisionDriveSystem.getPathToVisionTargetOffset(aprilTagTarget);
        PathConstraints targeConstraints = m_VisionDriveSystem.getConstraintsToVisionTargetOffset(aprilTagTarget);

        AutoBuilder.pathfindThenFollowPath(targetPath, targeConstraints);
    }
    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        // is there a way to stop the robot?
    }

    public void stopPathfindToApriltag() {
        // how to stop a path?
    }
}
