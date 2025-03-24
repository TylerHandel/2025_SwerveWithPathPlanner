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
    }
    public static class CANdle {
        public static final int kCANdleID = 19;
    }
} 
