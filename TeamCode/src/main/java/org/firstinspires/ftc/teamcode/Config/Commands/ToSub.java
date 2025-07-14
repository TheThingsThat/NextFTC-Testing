package org.firstinspires.ftc.teamcode.Config.Commands;

import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.command.groups.ParallelGroup;
import com.rowanmcalpin.nextftc.core.command.groups.SequentialGroup;
import com.rowanmcalpin.nextftc.core.command.utility.delays.Delay;

import org.firstinspires.ftc.teamcode.Config.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Config.Subsystems.Lift;

public class ToSub {

    public static Command move(Intake intake, Lift lift) {
            return new SequentialGroup(
                    intake.openClaw(),
                    new Delay(0.2),
                    new ParallelGroup(
                            intake.intakeUp1(),
                            intake.intakeUp2(),
                            lift.retractLeftLift(),
                            lift.retractRightLift()
                    ),
                    lift.pivotDown()
            );
    }
}