package org.firstinspires.ftc.teamcode.Examples.kotlin

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.rowanmcalpin.nextftc.core.command.Command
import com.rowanmcalpin.nextftc.core.command.groups.ParallelGroup
import com.rowanmcalpin.nextftc.core.command.groups.SequentialGroup
import com.rowanmcalpin.nextftc.core.command.utility.delays.Delay
import com.rowanmcalpin.nextftc.ftc.NextFTCOpMode

@Autonomous(name = "NextFTC Autonomous Program Kotlin")
class AutonomousProgram: NextFTCOpMode(Example_Claw_Kotlin, Example_Lift_Kotlin) {
    val firstRoutine: Command
        get() = SequentialGroup(
            Example_Lift_Kotlin.toHigh,
            ParallelGroup(
                Example_Lift_Kotlin.toMiddle,
                Example_Claw_Kotlin.close
            ),
            Delay(0.5),
            ParallelGroup(
                Example_Claw_Kotlin.open,
                Example_Lift_Kotlin.toLow
            )
        )

    override fun onStartButtonPressed() {
        firstRoutine()
    }
}