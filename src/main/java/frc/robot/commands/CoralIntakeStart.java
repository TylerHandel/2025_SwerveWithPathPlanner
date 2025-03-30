package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CoralLauncher;
import frc.robot.subsystems.CoralSensor;

public class CoralIntakeStart extends Command{

    private CoralLauncher m_launcher;
    private final Timer timer = new Timer();

    public CoralIntakeStart(CoralLauncher launcher) {
        m_launcher = launcher;
        addRequirements(m_launcher);
    }

    @Override
    public void initialize() {
        timer.reset();
        timer.start();
        m_launcher.setIntakeWheel(0.25);
        m_launcher.setOuttakeWheel(0);
    }

    @Override
    public void execute() {
        if (DriverStation.isAutonomous()) {
            double elapsedTime = timer.get();
            if (elapsedTime >= 0.5 && elapsedTime <= 1.0) {
                m_launcher.setIntakeWheel(-1);
            } else if (elapsedTime > 1.0) {
                m_launcher.setIntakeWheel(0.25);
            }
        }
    }

    @Override
    public boolean isFinished() {
        return CoralSensor.isCoralDetected();
    }

    @Override
    public void end(boolean interrupted) {
        timer.stop();
        m_launcher.setIntakeWheel(0);
        m_launcher.setOuttakeWheel(0);
    }
}
