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
@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name="CloseBlueSecond", group="Autonomous")
public class AutoCloseBlue extends LinearOpMode {
    Drive drive;
    Servo arm;
    
    ColorSensor ballColor;
    VuMarkRecognition vuMark;

    void init_(){
        drive = new Drive(this);

    }



    @Override
    public void runOpMode() throws InterruptedException {

        init_();
        waitForStart();
        drive.encoderMoveMRGyro(0,2,.75);

    }

}
