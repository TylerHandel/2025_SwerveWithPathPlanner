// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.CoralSensor;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private final RobotContainer m_robotContainer;

  public Robot() {
    m_robotContainer = new RobotContainer();
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();

   // System.out.println("Coral detected: %s/f".formatted(Boolean.toString(CoralSensor.isCoralDetected())));

    /*
     * This example of adding Limelight is very simple and may not be sufficient for on-field use.
     * Users typically need to provide a standard deviation that scales with the distance to target
     * and changes with number of tags available.
     *
     * This example is sufficient to show that vision integration is possible, though exact implementation
     * of how to use vision should be tuned per-robot and to the team's specification.
     */
    if (Constants.Vision.kUseLimelight) {
      var driveState = m_robotContainer.drivetrain.getState();
      double headingDeg = driveState.Pose.getRotation().getDegrees();
      double omegaRps = Units.radiansToRotations(driveState.Speeds.omegaRadiansPerSecond);

      LimelightHelpers.SetRobotOrientation(Constants.Vision.kLimelightBack, headingDeg, 0, 0, 0, 0, 0);
      
      /* Original code to get pose estimate from Limelight assumes Blue field orientation */
      var llMeasurement = LimelightHelpers.getBotPoseEstimate_wpiBlue_MegaTag2(Constants.Vision.kLimelightBack);

      /* Updated code checks field orientation and uses appropriate pose estimate call */
      /* var llMeasurement = DriverStation.getAlliance().orElse(Alliance.Blue) == Alliance.Red
        ? LimelightHelpers.getBotPoseEstimate_wpiRed_MegaTag2(Constants.Vision.kLimelightFront)
        : LimelightHelpers.getBotPoseEstimate_wpiBlue_MegaTag2(Constants.Vision.kLimelightFront);
      */
      /* Original Pose Update from Vision code. This uses X, Y and Z values from vision and resets pose of robot. */
      
      if (llMeasurement != null && llMeasurement.tagCount > 0 && Math.abs(omegaRps) < 2.0) {
        m_robotContainer.drivetrain.addVisionMeasurement(llMeasurement.pose, llMeasurement.timestampSeconds);
      }
      
      /* New Vision Pose Update code. This uses just the X and Y (location on field) values from vision and does not overwrite the 
       * gyro-based Z (rotation) value. The assumption is that the gyro is relatively accurate throughout the match and does not need
       * to be updated.
       * 
       * This may not be needed with MegaTag2 since MegaTag2 is only supposed to update X and Y values.
      */
      
      /* Commenting out for now 
      if (llMeasurement != null && llMeasurement.tagCount > 0 && Math.abs(omegaRps) < 2.0) {
        m_robotContainer.drivetrain.addVisionMeasurement(new Pose2d(
                                          new Translation2d(
                                              llMeasurement.pose.getTranslation().getX(),
                                              llMeasurement.pose.getTranslation().getY()),
                                              driveState.Pose.getRotation()),
                                          llMeasurement.timestampSeconds);
      }
      */
    }
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void disabledExit() {}

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void autonomousExit() {}

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {}

  @Override
  public void teleopExit() {}

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}

  @Override
  public void testExit() {}

  @Override
  public void simulationPeriodic() {}
}
