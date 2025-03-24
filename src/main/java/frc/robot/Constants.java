package frc.robot;

import java.lang.reflect.Array;

public final class Constants {
    public final class Controllers {
        public static final int kJoystickId = 0;
    }
    public static class CoralLauncher {
        public static final int kIntakeId = 17;
        public static final int kOuttakeId = 16;
    }
    public static class Sensors {
        public static final int kCoralSensorId = 15;
    }
    public static class CANdle {
        public static final int kCANdleID = 19;
    }
    public static class Climber {
        public static final int kClimberMotorId = 18;
        public static final double kClimberTopPositionRevolutions = 0.0;
        public static final double kClimberBottomPositionRevolutions = 40.0;
    }
    public static class Vision {
        public static final boolean kUseLimelight = true;
        public static final String kLimelightFront = "limelight-front";
        public static final String kLimelightBack = "limelight-back";
        public static final String[] kLimelightNames = {
            kLimelightFront,
            kLimelightBack
        };
        public static double[][] kAprilTagLocations = {
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
            {0, 0, 0, 0, 0}, // 0 - blue origin = not valid apriltag
            {657.37, 25.80, 58.50, 126, 0},  // 1
            {657.37, 291.20, 58.50, 234, 0}, // 2
            {455.15, 317.15, 51.25, 270, 0}, // 3
            {365.20, 241.64, 73.54, 0, 30},  // 4
            {365.20, 75.39, 73.54, 0, 30},   // 5
            {530.49, 130.17, 12.13, 300, 0}  // 6
        };
    }

 
} 
