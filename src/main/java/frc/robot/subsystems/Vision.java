package frc.robot.subsystems;

import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.controls.StaticBrake;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants;
import frc.robot.LimelightHelpers;

public class Vision implements Subsystem {

    private final TalonFX m_climberMotor;
    
    // Climber positions for top and bottom of arm
    private static final Slot0Configs climberGains = new Slot0Configs()
    .withKP(2.4).withKI(0).withKD(0.1); // was 1, 0, 0

    /** Creates a new Vision subsystem. */
    public Vision() {

    }

    /**
     * This method is an example of the 'subsystem factory' style of command creation. A method inside
     * the subsytem is created to return an instance of a command. This works for commands that
     * operate on only that subsystem, a similar approach can be done in RobotContainer for commands
     * that need to span subsystems. The Subsystem class has helper methods, such as the startEnd
     * method used here, to create these commands.
     */
    /* 
    public Command getClimberUpCommand() {
        // The startEnd helper method takes a method to call when the command is
        // initialized and one to call when it ends
        return this.startEnd(
               
                () -> {
                    setClimberMotor(kClimberTopPositionRevolutions);
                },
                // When the command stops, stop the climber
                () -> {
                    stop();
                });
    }
*/
    
    // A method to update the robot pose using Limelight cameras
    public void updateRobotPoseUsingVision() {
        var driveState = m_robotContainer.drivetrain.getState();
        double headingDeg = driveState.Pose.getRotation().getDegrees();
        double omegaRps = Units.radiansToRotations(driveState.Speeds.omegaRadiansPerSecond);

        LimelightHelpers.SetRobotOrientation(Constants.Vision.kLimelightBack, headingDeg, 0, 0, 0, 0, 0);
      
        /* Original code to get pose estimate from Limelight assumes Blue field orientation */
        var llMeasurement = LimelightHelpers.getBotPoseEstimate_wpiBlue_MegaTag2(Constants.Vision.kLimelightBack);

      /* This uses X,Y values from vision and resets pose of robot. */
      
      if (llMeasurement != null && llMeasurement.tagCount > 0 && Math.abs(omegaRps) < 2.0) {
        m_robotContainer.drivetrain.addVisionMeasurement(llMeasurement.pose, llMeasurement.timestampSeconds);
      }
    }

}
