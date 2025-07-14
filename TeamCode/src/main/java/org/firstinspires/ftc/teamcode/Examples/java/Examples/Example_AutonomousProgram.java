package org.firstinspires.ftc.teamcode.Examples.java.Examples;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.command.groups.ParallelGroup;
import com.rowanmcalpin.nextftc.core.command.groups.SequentialGroup;
import com.rowanmcalpin.nextftc.core.command.utility.delays.Delay;
import com.rowanmcalpin.nextftc.ftc.NextFTCOpMode;

@Autonomous(name = "NextFTC Autonomous Program Java")
public class Example_AutonomousProgram extends NextFTCOpMode {
    public Example_AutonomousProgram() {
        super(Example_Claw.INSTANCE, Example_Lift.INSTANCE);
    }

    public Command firstRoutine() {
        return new SequentialGroup(
                Example_Lift.INSTANCE.toHigh(),
                new ParallelGroup(
                        Example_Lift.INSTANCE.toMiddle(),
                        Example_Claw.INSTANCE.close()
                ),
                new Delay(0.5),
                new ParallelGroup(
                        Example_Claw.INSTANCE.open(),
                        Example_Lift.INSTANCE.toLow()
                )
        );
    }

    @Override
    public void onStartButtonPressed() {
        firstRoutine().invoke();
    }
}
