package org.firstinspires.ftc.teamcode.OpModes;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
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
@Disabled
@Autonomous(name="Raw Servo Test", group="Autonomous")
public class RawServoTest extends LinearOpMode {
    //Drive drive;
    Servo arm;
    Servo rotateArm;

    ColorSensor ballColor;
    VuMarkRecognition vuMark;
    Drive driveTrain;
    Jewels jewel;

    DcMotor extendOne;
    DcMotor extendTwo;
    void init_(){


        driveTrain = new Drive(this.hardwareMap,this.telemetry);
    }



    @Override
    public void runOpMode() throws InterruptedException {
        init();
        waitForStart();
        driveTrain.encoderMoveMRGyro(90,1,.5);

    }

}
