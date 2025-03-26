package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Climber;

public class ClimbDown extends Command{
    private Climber m_Climber;
    public ClimbDown(Climber climber) {
        m_Climber = climber;
        addRequirements(m_Climber);
    }

    @Override
    public void initialize() {
        m_Climber.setClimberPosition(Constants.Climber.kClimberBottomPositionRevolutions);
    }

    @Override
    public void end(boolean interrupted) {
        m_Climber.stop();
    }
}
