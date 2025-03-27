package frc.robot.commands;

import com.ctre.phoenix6.swerve.SwerveRequest;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import frc.robot.LimelightHelpers;

public class DriveToNearestApriltag extends Command{
    
    private final SwerveRequest.RobotCentric driveRobotCentric = new SwerveRequest.RobotCentric();

    public DriveToNearestApriltag(CommandSwerveDrivetrain drivetrain) {
        CommandSwerveDrivetrain m_Drive = drivetrain;
        addRequirements(m_Drive);
    }

    @Override
    public void initialize() {
        driveRobotCentric.withVelocityY(-LimelightHelpers.getBotPose_TargetSpace(Constants.Vision.kLimelightBack)[0]*5)
            .withVelocityX(LimelightHelpers.getBotPose_TargetSpace(Constants.Vision.kLimelightBack)[2]*1)
            .withRotationalRate((-LimelightHelpers.getBotPose_TargetSpace(Constants.Vision.kLimelightBack)[4]*0.1)); 
    }

    @Override
    public void end(boolean interrupted) {
        driveRobotCentric.withVelocityY(0).withVelocityX(0).withRotationalRate(0);
    }
}

