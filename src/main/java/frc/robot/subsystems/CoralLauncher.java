package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants;

public class CoralLauncher implements Subsystem {

    private final TalonFX m_intakeWheel;
    private final TalonFX m_outtakeWheel;

    // Speeds for the intake and outtake wheels
    private static final double kIntakeLauncherSpeed = 1; // -1
    private static final double kOuttakeFeederSpeed = .2; // -.2

    /** Creates a new Launcher. */
    public CoralLauncher() {
        m_intakeWheel = new TalonFX(Constants.CoralLauncher.kIntakeId);
        m_outtakeWheel = new TalonFX(Constants.CoralLauncher.kOuttakeId);
    }

    /**
     * This method is an example of the 'subsystem factory' style of command
     * creation. A method inside
     * the subsytem is created to return an instance of a command. This works for
     * commands that
     * operate on only that subsystem, a similar approach can be done in
     * RobotContainer for commands
     * that need to span subsystems. The Subsystem class has helper methods, such as
     * the startEnd
     * method used here, to create these commands.
     */
    public Command getIntakeCommand() {
        // The startEnd helper method takes a method to call when the command is
        // initialized and one to call when it ends
        return this.startEnd(
                // When the command is initialized, set the wheels to the intake speed values
                () -> {
                    // setOuttakeWheel(kOuttakeFeederSpeed);
                    setIntakeWheel(kIntakeLauncherSpeed);
                },
                // When the command stops, stop the wheels
                () -> {
                    stop();
                });
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
