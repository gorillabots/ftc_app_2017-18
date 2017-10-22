package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Components.TestArmExtender;
import org.firstinspires.ftc.teamcode.Components.TestDriveTrain;
import org.firstinspires.ftc.teamcode.Components.TestGrabber;
import org.firstinspires.ftc.teamcode.Interfaces.ArmExtender;
import org.firstinspires.ftc.teamcode.Interfaces.DriveTrain;
import org.firstinspires.ftc.teamcode.Interfaces.Grabber;

/**
 * Created by Owner on 10/6/2017.
 */

public class TeleOp extends OpMode{

    DriveTrain driveTrain;
    ArmExtender armExtender;
    Grabber grabber;

    Servo claw;
    Servo spin;


    @Override
    public void init() {
        driveTrain = new TestDriveTrain(hardwareMap, telemetry);
        armExtender = new TestArmExtender(hardwareMap, telemetry);
        grabber = new TestGrabber(hardwareMap, telemetry);

        claw = hardwareMap.servo.get("claw");
        spin = hardwareMap.servo.get("spin");

    }

    @Override
    public void loop() {
        float stickX = (gamepad1.left_stick_x);
        float stickY = (gamepad1.right_stick_y); // range between -1 to 1

        //DriveTrain mov.
        if(stickY >= .6){
            driveTrain.move(0,stickY);
        }
        else if(stickY <= -.6){
            driveTrain.move(180, stickY);
        }

        if (stickX >= .6){
            driveTrain.move(90, stickX);
        }
        else if(stickX <= -.6){
            driveTrain.move(270, stickX);
        }

        //Arm extention
        if(gamepad2.right_stick_y <= .7){
            armExtender.extend(50);
        }
        else if(gamepad2.right_stick_y >= .7){
            armExtender.extend(0);
        }
        if(gamepad2.right_stick_y <= -.7){
            armExtender.extend(-50);
        }
        else if(gamepad2.right_stick_y >= -.7){
            armExtender.extend(0);
        }

        //Graber
        if (gamepad2.x){
            grabber.open(90, 005);}
        else if (gamepad2.x){
            grabber.open(0, 005);
        }
        if (gamepad2.y){
            claw.close();
        }
        else if (gamepad2.y){
            claw.close();
        }
        if(gamepad2.a){
            grabber.rotate(90, 005);}
        else if (gamepad2.b){
            grabber.rotate(-90, 005);
        }





    }
}
