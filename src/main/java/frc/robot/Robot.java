// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.lang.reflect.Array;
import java.util.Arrays;

import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

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

  // Invert the joystick inputs if our alliance is red. The origin is always blue. 
  // When our alliance is red, X and Y need to be inverted.
  var alliance = DriverStation.getAlliance();
  if (alliance.isPresent() && alliance.get() == Alliance.Red) {
    m_robotContainer.joystickInvert = -1;
  }

  /* Update robot pose every cycle using Limelight cameras and AprilTags   */
    if (Constants.Vision.kUseLimelight) {
      // First, get your robot's current orientation from gyro
      var driveState = m_robotContainer.drivetrain.getState();
      double headingDeg = driveState.Pose.getRotation().getDegrees();
      double omegaRps = Units.radiansToRotations(driveState.Speeds.omegaRadiansPerSecond);
      
      // Set standard deviations for vision measurements
      m_robotContainer.drivetrain.setVisionMeasurementStdDevs(VecBuilder.fill(.5, .5, 9999999));

      var m_limelightNames = Constants.Vision.kLimelightNames;
      for (String m_limelightName : m_limelightNames) {
        /* Set robot orientation using gyro value */
        LimelightHelpers.SetRobotOrientation(m_limelightName, headingDeg, 0, 0, 0, 0, 0);
        
        /* Get pose estimate from Limelight assumes Blue field orientation */
        var llMeasurement = LimelightHelpers.getBotPoseEstimate_wpiBlue_MegaTag2(m_limelightName);
        
        if (llMeasurement != null && llMeasurement.tagCount > 0 && Math.abs(omegaRps) < 2.0) {
          /* This uses X,Y values from vision and resets pose of robot. */
          m_robotContainer.drivetrain.addVisionMeasurement(llMeasurement.pose, llMeasurement.timestampSeconds);
          System.out.println("LL " + m_limelightName + " Update: " + m_robotContainer.drivetrain.getState().Pose);
        }
      }
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
