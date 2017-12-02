package org.firstinspires.ftc.teamcode.OpModes;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Components.ColorHelper;
import org.firstinspires.ftc.teamcode.Components.Drive;
import org.firstinspires.ftc.teamcode.Components.Jewels;
import org.firstinspires.ftc.teamcode.Vision.VuMarkRecognition;

/**
 * Created by Jarred on 10/29/2017.
 */
@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name="CloseBlueSecond", group="Autonomous")
public class AutoCloseBlue extends LinearOpMode {
    //Drive drive;
    Servo arm;
    Servo rotateArm;
    
    ColorSensor ballColor;
    VuMarkRecognition vuMark;

    Jewels jewel;

    DcMotor extendOne;
    DcMotor extendTwo;
    void init_(){

        extendOne = hardwareMap.dcMotor.get("extendOne");
        extendTwo = hardwareMap.dcMotor.get("extendTwo");


        extendTwo.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        extendOne.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        jewel = new Jewels(this.hardwareMap,this);
        arm = hardwareMap.servo.get("arm");
        rotateArm = hardwareMap.servo.get("rotateArm");
        rotateArm.setPosition(.76);
    }



    @Override
    public void runOpMode() throws InterruptedException {

        init_();

        extendOne.setPower(.25);
        extendOne.setPower(.25);
        sleep(300);
        extendOne.setPower(0);
        extendTwo.setPower(0);
        waitForStart();
        jewel.scanPosition();
        /*rotateArm.setPosition(.6);
        sleep(1000);
        jewel.lowerUntilBall(arm);
        telemetry.addData("found ", "ball");

        telemetry.update();
        sleep(10000);
        */

    }

}
