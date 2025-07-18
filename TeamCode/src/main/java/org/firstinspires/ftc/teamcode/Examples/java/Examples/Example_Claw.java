package org.firstinspires.ftc.teamcode.Examples.java.Examples;

import com.qualcomm.robotcore.hardware.Servo;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.ServoToPosition;

public class Example_Claw extends Subsystem {
    // BOILERPLATE
    public static final Example_Claw INSTANCE = new Example_Claw();
    private Example_Claw() { }

    // USER CODE
    public Servo servo;
    
    public String name = "claw_servo";

    public Command open() {
        return new ServoToPosition(servo, // SERVO TO MOVE
                0.9, // POSITION TO MOVE TO
                this); // IMPLEMENTED SUBSYSTEM
    }

    public Command close() {
        return new ServoToPosition(servo, // SERVO TO MOVE
                0.2, // POSITION TO MOVE TO
                this); // IMPLEMENTED SUBSYSTEM
    }

    @Override
    public void initialize() {
        servo = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, name);
    }
}
