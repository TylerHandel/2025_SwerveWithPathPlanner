package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants;

public class CoralLauncher implements Subsystem {

    private final TalonFX m_intakeWheel;
    private final TalonFX m_outtakeWheel;

    /** Creates a new Launcher. */
    public CoralLauncher() {
        m_intakeWheel = new TalonFX(Constants.CoralLauncher.kIntakeId);
        m_outtakeWheel = new TalonFX(Constants.CoralLauncher.kOuttakeId);
    }

    // An accessor method to set the speed (technically the output percentage) of
    // the launch wheel
    public void setIntakeWheel(double speed) {
        m_intakeWheel.set(speed);
    }

    // An accessor method to set the speed (technically the output percentage) of
    // the feed wheel
    public void setOuttakeWheel(double speed) {
        m_outtakeWheel.set(speed);
    }

    // A helper method to stop both wheels. You could skip having a method like this
    // and call the individual accessors with speed = 0 instead
    public void stop() {
        m_intakeWheel.set(0);
        m_outtakeWheel.set(0);
    }

}
