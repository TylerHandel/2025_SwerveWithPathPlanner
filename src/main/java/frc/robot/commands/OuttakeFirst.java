package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CoralLauncher;

public class OuttakeFirst extends Command{
    private CoralLauncher m_launcher;
    private Timer time = new Timer();
    public OuttakeFirst(CoralLauncher launcher) {
        
        m_launcher = launcher;
        addRequirements(m_launcher);
    }

    @Override
    public void initialize() {
        m_launcher.setIntakeWheel(0.4);
        m_launcher.setOuttakeWheel(0.2);
        time.reset();
        time.start();
    }
    @Override
    public boolean isFinished() {
        return time.get() > 1;
    }

    @Override
    public void end(boolean interrupted) {
        stop();
    }

    public void stop() {
        m_launcher.setIntakeWheel(0);
        m_launcher.setOuttakeWheel(0);
    }
}
