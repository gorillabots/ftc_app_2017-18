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
@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name="Raw Servo Test", group="Autonomous")
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

        extendOne = hardwareMap.dcMotor.get("extendOne");
        extendTwo = hardwareMap.dcMotor.get("extendTwo");

        driveTrain = new Drive(this);

        extendTwo.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        extendOne.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


       // jewel = new Jewels(this.hardwareMap,this);
        arm = hardwareMap.servo.get("arm");
        rotateArm = hardwareMap.servo.get("rotateArm");
        rotateArm.setPosition(.76);
    }



    @Override
    public void runOpMode() throws InterruptedException {

        driveTrain.encoderMoveMRGyro(90,1,.5);

    }

}
