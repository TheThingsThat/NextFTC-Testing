package org.firstinspires.ftc.teamcode.Config.Subsystems;

import com.qualcomm.robotcore.hardware.Servo;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.ServoToPosition;

import org.firstinspires.ftc.teamcode.Config.RobotConstants;

public class Intake extends Subsystem{

    public static final Intake INSTANCE = new Intake();
    private Intake() {}

    public Servo claw, clawRot, intakeRot1, intakeRot2;

    @Override
    public void initialize() {
        claw = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, "claw");
        clawRot = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, "clawRot");
        intakeRot1 = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, "rotation");
        intakeRot2 = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, "parRotation");
    }

    public Command closeClaw() {
        return new ServoToPosition(claw, RobotConstants.closeClaw, this);
    }
    public Command openClaw() {
        return new ServoToPosition(claw, RobotConstants.openClaw, this);
    }

    public Command clawHor() {
        return new ServoToPosition(clawRot, RobotConstants.clawHor, this);
    }
    public Command clawVer() {
        return new ServoToPosition(clawRot, RobotConstants.clawVer, this);
    }

    public Command intakeUp1() {
        return new ServoToPosition(intakeRot1, RobotConstants.intakeUp1, this);
    }
    public Command intakeDown1() {
        return new ServoToPosition(intakeRot1, RobotConstants.intakeDown1, this);
    }
    public Command intakeDeposit1() {
        return new ServoToPosition(intakeRot1, RobotConstants.intakeDeposit1, this);
    }
    public Command intakeUp2() {
        return new ServoToPosition(intakeRot2, RobotConstants.intakeUp2, this);
    }
    public Command intakeDown2() {
        return new ServoToPosition(intakeRot2, RobotConstants.intakeDown2, this);
    }
    public Command intakeDeposit2() {
        return new ServoToPosition(intakeRot2, RobotConstants.intakeDeposit2, this);
    }
}