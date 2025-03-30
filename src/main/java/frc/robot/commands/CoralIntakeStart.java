package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CoralLauncher;
import frc.robot.subsystems.CoralSensor;

public class CoralIntakeStart extends Command{

    private CoralLauncher m_launcher;
    private Timer time = new Timer();
    public CoralIntakeStart(CoralLauncher launcher) {
        m_launcher = launcher;
        addRequirements(m_launcher);
    }

    @Override
    public void initialize() {
        m_launcher.setIntakeWheel(0.25);
        m_launcher.setOuttakeWheel(0);
        time.reset();
        time.start();
    }
    @Override
    public void execute() {
        /* 
        if (DriverStation.isAutonomous() && time.get() < 1){
            m_launcher.setIntakeWheel(-0.25);
            m_launcher.setOuttakeWheel(0);
            
        }
        else {
            m_launcher.setIntakeWheel(0.25);
            m_launcher.setOuttakeWheel(0);
        }
            */
    }

    @Override
    public boolean isFinished() {
        return CoralSensor.isCoralDetected();
    }

    @Override
    public void end(boolean interrupted) {
        m_launcher.setIntakeWheel(0);
        m_launcher.setOuttakeWheel(0);
    }
}
