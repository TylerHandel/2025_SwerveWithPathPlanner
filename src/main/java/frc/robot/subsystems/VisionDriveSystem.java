package frc.robot.subsystems;

import static edu.wpi.first.units.Units.Inches;

import java.util.List;

import com.pathplanner.lib.path.GoalEndState;
import com.pathplanner.lib.path.PathConstraints;
import com.pathplanner.lib.path.PathPlannerPath;
import com.pathplanner.lib.path.Waypoint;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants;

public class VisionDriveSystem implements Subsystem {

    /** Creates a new VisionDriveSystem. */
    public VisionDriveSystem() {
        
    }

    /* Array of locations corresponding to AprilTag constants.
    * Assumes Blue origin coordinate system
    * The XYZ Origin is established in the bottom left corner of
    * the field. An x coordinate of 0 is aligned with the Blue Alliance Station
    * diamond plate. A y coordinate of 0 is aligned with the
    * side border polycarbonate on the Scoring Table side of
    * the field. A z coordinate of 0 is on the carpet.
    * +Z is up into the air from the carpet, +X is horizontal to the
    * right (in this image above) toward the opposing alliance
    * stations, and +Y runs from the Field Border towards the REEFS.
    * The face-pose of the tags is denoted with 1 degree
    * representation, the Z-rotation. 0° faces the red alliance
    * station, 90° faces the non- scoring table side, and 180°
    * faces the blue alliance station. For the X-Rotation, 0 is
    * perpendicular to the Z plane, and 90 degrees is facing the carpet.
    * Distances are measured to the center of the tag.
    * Distances are measured in inches.
    */
   

    // An accessor method to set the AprilTag target
    public void setVisionTarget(int aprilTagTarget) {
        double m_AprilTagTargetData[] = Constants.Vision.kAprilTagLocations[aprilTagTarget];
        final double InchestoMeters = 0.0254;
    
        double targetX = m_AprilTagTargetData[0] * InchestoMeters;   // X coordinate in inches
        double targetY = m_AprilTagTargetData[1] * InchestoMeters;   // Y coordinate in inches
        double targetRot = m_AprilTagTargetData[3]; // Z rotaton in degrees

        // Create a list of waypoints from poses. Each pose represents one waypoint.
        // The rotation component of the pose should be the direction of travel. Do not use holonomic rotation.
        List<Waypoint> waypoints = PathPlannerPath.waypointsFromPoses(
            new Pose2d(targetX, targetY, Rotation2d.fromDegrees(targetRot)) //,
        //    new Pose2d(3.0, 1.0, Rotation2d.fromDegrees(0)),
        //    new Pose2d(5.0, 3.0, Rotation2d.fromDegrees(90))
        );

        PathConstraints constraints = new PathConstraints(3.0, 3.0, 2 * Math.PI, 4 * Math.PI); // The constraints for this path.
        // PathConstraints constraints = PathConstraints.unlimitedConstraints(12.0); // You can also use unlimited constraints, only limited by motor torque and nominal battery voltage

        // Create the path using the waypoints created above
        PathPlannerPath path = new PathPlannerPath(
            waypoints,
            constraints,
            null, // The ideal starting state, this is only relevant for pre-planned paths, so can be null for on-the-fly paths.
            new GoalEndState(0.0, Rotation2d.fromDegrees(-90)) // Goal end state. You can set a holonomic rotation here. If using a differential drivetrain, the rotation will have no effect.
        );

        // Prevent the path from being flipped if the coordinates are already correct  
        path.preventFlipping = true;

    }
    
    // A helper method to stop the Vision Drive System.
    public void stop() {
        
    }

}
