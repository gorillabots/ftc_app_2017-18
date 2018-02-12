package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Drive.Drive;
import org.firstinspires.ftc.teamcode.Components.JewelsJack;
import org.firstinspires.ftc.teamcode.Vision.VuMarkRecognition;

/**
 * Created by Jarred on 10/29/2017.
 */

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="Raw Servo Test", group="Autonomous")
public class RawServoTest extends LinearOpMode {
    //Drive drive;
    Servo arm;
    Servo rotateArm;

    ColorSensor ballColor;
    VuMarkRecognition vuMark;
    Drive driveTrain;
    JewelsJack jewel;

    DcMotor extendOne;
    DcMotor extendTwo;

    Servo linkage;
    Servo claw;
    void init_(){

        claw = hardwareMap.servo.get("claw");
        linkage = hardwareMap.servo.get("linkage");


    }



    @Override
    public void runOpMode() throws InterruptedException {
        init();
        waitForStart();

        while(opModeIsActive()) {
            linkage.setPosition(gamepad1.left_trigger);
            telemetry.addData("link", gamepad1.left_trigger);
            telemetry.update();
        }

    }

}
