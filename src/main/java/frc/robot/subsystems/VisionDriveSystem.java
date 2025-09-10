package frc.robot.subsystems;

import java.util.List;
import java.util.Optional;

import com.pathplanner.lib.path.GoalEndState;
import com.pathplanner.lib.path.PathConstraints;
import com.pathplanner.lib.path.PathPlannerPath;
import com.pathplanner.lib.path.Waypoint;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Subsystem;
import java.lang.Math;
import java.nio.file.Path;

import frc.robot.Constants;
import frc.robot.FieldConstants;


public class VisionDriveSystem implements Subsystem {

    /** Creates a new VisionDriveSystem. */
    public VisionDriveSystem() {
        
    }
   
    // An accessor method to create the path to the AprilTag target
    public PathPlannerPath getPathToVisionTarget(int aprilTagTarget) {
    
        //double m_AprilTagTargetData[] = Constants.Vision.kAprilTagLocations[aprilTagTarget];
        final double InchestoMeters = 0.0254;

        // Get the pose of the AprilTag
        Optional<Pose3d> targetPose3d = FieldConstants.getTagPose(aprilTagTarget); // Get the pose of the AprilTag
        if (targetPose3d.isEmpty()) {

        
        /* Old code to look up apriltag pose info
        double targetX = m_AprilTagTargetData[0] * InchestoMeters;   // X coordinate in inches
        double targetY = m_AprilTagTargetData[1] * InchestoMeters;   // Y coordinate in inches
        double targetRot = m_AprilTagTargetData[3]; // Z rotaton in degrees
        */

        // Get the pose of the AprilTag - need to caluclate the offset from the center of the robot
        double targetX = targetPose3d.get().getX();   // X coordinate in meters
        double targetY = targetPose3d.get().getY();   // Y coordinate in meters
        Rotation2d targetRot = targetPose3d.get().getRotation().toRotation2d(); // Z rotation in radians

        // Translate the target from the center of the tag to the center of the robot
        double translatedTargetX = targetX + Constants.Vision.kLengthOfRobot*InchestoMeters*Math.cos(targetRot.getDegrees());
        double translatedTargetY = targetY + Constants.Vision.kLengthOfRobot*InchestoMeters*Math.sin(targetRot.getDegrees());
        
        // Create a list of waypoints from poses. Each pose represents one waypoint.
        // The rotation component of the pose should be the direction of travel. Do not use holonomic rotation.
        List<Waypoint> waypoints = PathPlannerPath.waypointsFromPoses(
            new Pose2d(translatedTargetX, translatedTargetY, targetRot)
        );
        // can have more than one waypoint
        //    new Pose2d(3.0, 1.0, Rotation2d.fromDegrees(0)),
        //    new Pose2d(5.0, 3.0, Rotation2d.fromDegrees(90))

        // The constraints for this path.
        PathConstraints constraints = new PathConstraints(3.0, 3.0, 2 * Math.PI, 4 * Math.PI); 
        // You can also use unlimited constraints, only limited by motor torque and nominal battery voltage
        // PathConstraints constraints = PathConstraints.unlimitedConstraints(12.0); 
        
        // Create the path using the waypoints created above
        PathPlannerPath path = new PathPlannerPath(
            waypoints,
            constraints,
            null, // The ideal starting state, this is only relevant for pre-planned paths, so can be null for on-the-fly paths.
            new GoalEndState(0.0, Rotation2d.fromDegrees(targetRot.getDegrees())) // Goal end state. You can set a holonomic rotation here. If using a differential drivetrain, the rotation will have no effect.
        );

        // Prevent the path from being flipped if the coordinates are already correct  
        path.preventFlipping = true;
       return path;
        } else {
            System.out.println("AprilTag " + aprilTagTarget + " not found on field.");
            return null;
        }
    }

     // An accessor method to create the path to the AprilTag target
     public PathPlannerPath getPathToVisionTargetOffset(int aprilTagTarget) {
    
        final double InchestoMeters = 0.0254;
        final double offsetDistance = 1; // 1 meter offset from Apriltag

        // Get the pose of the AprilTag
        Optional<Pose3d> targetPose3d = FieldConstants.getTagPose(aprilTagTarget); // Get the pose of the AprilTag
        double targetX = targetPose3d.get().getX();   // X coordinate in meters
        double targetY = targetPose3d.get().getY();   // Y coordinate in meters
        Rotation2d targetRot = targetPose3d.get().getRotation().toRotation2d(); // Z rotation in radians

        // Translate the target from the center of the tag to the center of the robot + 1 meter back
        double translatedTargetOffsetX = targetX + (Constants.Vision.kLengthOfRobot+offsetDistance)*InchestoMeters*Math.cos(targetRot.getDegrees());
        double translatedTargetOffsetY = targetY + (Constants.Vision.kLengthOfRobot+offsetDistance)*InchestoMeters*Math.sin(targetRot.getDegrees());
    
         // Create a list of waypoints from poses. Each pose represents one waypoint.
        // The rotation component of the pose should be the direction of travel. Do not use holonomic rotation.
        List<Waypoint> waypoints = PathPlannerPath.waypointsFromPoses(
            new Pose2d(translatedTargetOffsetX, translatedTargetOffsetY, targetRot)
        );
        // can have more than one waypoint
        //    new Pose2d(3.0, 1.0, Rotation2d.fromDegrees(0)),
        //    new Pose2d(5.0, 3.0, Rotation2d.fromDegrees(90))

        // The constraints for this path.
        PathConstraints constraints = new PathConstraints(3.0, 3.0, 2 * Math.PI, 4 * Math.PI); 
        // You can also use unlimited constraints, only limited by motor torque and nominal battery voltage
        // PathConstraints constraints = PathConstraints.unlimitedConstraints(12.0); 
        
        // Create the path using the waypoints created above
        PathPlannerPath path = new PathPlannerPath(
            waypoints,
            constraints,
            null, // The ideal starting state, this is only relevant for pre-planned paths, so can be null for on-the-fly paths.
            new GoalEndState(0.0, Rotation2d.fromDegrees(targetRot.getDegrees())) // Goal end state. You can set a holonomic rotation here. If using a differential drivetrain, the rotation will have no effect.
        );

        // Prevent the path from being flipped if the coordinates are already correct  
        path.preventFlipping = true;
        return path;
     }
     
     public PathConstraints getConstraintsToVisionTargetOffset(int aprilTagTarget) {
         
         // The constraints for this path. For now, it's the same for all AprilTags
         PathConstraints constraints = new PathConstraints(3.0, 3.0, 2 * Math.PI, 4 * Math.PI); 
         // You can also use unlimited constraints, only limited by motor torque and nominal battery voltage
         // PathConstraints constraints = PathConstraints.unlimitedConstraints(12.0); 

         return constraints;
     }

    // A helper method to stop the Vision Drive System.
    public void stop() {
        
    }

}
