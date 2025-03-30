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
        public static final double kLengthOfRobot = 36.0; // Length of the robot from front to back in inches, used for path planning 
        public static final String kLimelightFront = "limelight-front";
        public static final String kLimelightBack = "limelight-back";
        public static final String[] kLimelightNames = {
            kLimelightBack
        };
        public static final double kReefOffsetRight = 25.0; // inches - total is 6'4" wide
        public static final double kReefOffsetLeft = -25.0; // inches
        public static final double kCoralStationOffsetRight = 21.0; //inches - total is 5'5 1/2" wide
        public static final double kCoralStationOffsetLeft = -21.0; // inches

    };
};