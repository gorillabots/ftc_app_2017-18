package org.firstinspires.ftc.teamcode.OpModes;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Components.ColorHelper;
import org.firstinspires.ftc.teamcode.Components.Drive;
import org.firstinspires.ftc.teamcode.Vision.VuMarkRecognition;

/**
 * Created by Jarred on 10/29/2017.
 */
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="CloseBlueSecond", group="Autonomous")
public class AutoCloseBlue extends LinearOpMode {
    Drive drive;
    Servo arm;
    ColorSensor ballColor;
    VuMarkRecognition vuMark;

    void init_(){
        drive = new Drive(this);
        arm = hardwareMap.servo.get("arm");

        telemetry.addData("pre_sus","");
        telemetry.update();
        // Telemetry.Log
        ColorHelper.lowerSecond(arm);

        telemetry.addData("after_sus","");
        telemetry.update();
        ballColor=hardwareMap.colorSensor.get("ballColor");
        ballColor.enableLed(false);
        ballColor.enableLed(true);

        vuMark = new VuMarkRecognition();
        vuMark.init(hardwareMap);

        arm.setPosition(.88);
    }



    @Override
    public void runOpMode() throws InterruptedException {

        init_();
        arm.setPosition(22);
        waitForStart();
        drive.encoderMoveMRGyro(0,.25,.4);
        arm.setPosition(.88);



        if(ColorHelper.blueBall(ballColor)){
            drive.encoderMoveMRGyro(270,.05,.2);
        }
        else if(ColorHelper.redBall(ballColor)){
            drive.encoderMoveMRGyro(90,.05,.2);
        }
        int vuId = vuMark.getVuMark();

        arm.setPosition(.22);

        //drive.encoderMoveMRGyro(90,.5,.4);




    }

}
