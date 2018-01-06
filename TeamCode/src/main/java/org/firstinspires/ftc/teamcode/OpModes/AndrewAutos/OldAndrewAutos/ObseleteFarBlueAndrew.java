package org.firstinspires.ftc.teamcode.OpModes.AndrewAutos.OldAndrewAutos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Drive.Drive;

/**
 * Created by Jarred on 10/29/2017.
 */
@Disabled
@Autonomous(name="ObseleteFarBlueAndrew", group="AndrewBot")
public class ObseleteFarBlueAndrew extends LinearOpMode
{
    final double ARM_RAISED = .22;
    final double ARM_LOWERED = .9;//.88

    Drive drive;
    Servo arm;
    ColorSensor armColor;
    //VuMarkRecognition vuMarks;

    @Override
    public void runOpMode()
    {
        drive = new Drive(this);
        //drive.init(0);
        arm = hardwareMap.servo.get("arm");
        armColor=hardwareMap.colorSensor.get("ballColor");
        armColor.enableLed(false);
        armColor.enableLed(true);

        //telemetry.addData("Status", "Initializing Vuforia");
        //telemetry.update();

        //vuMarks = new VuMarkRecognition();
        //vuMarks.init(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        //int vuMark = vuMarks.getVuMark();

        //telemetry.addData("VuMark", vuMark);
        //telemetry.update();

        arm.setPosition(ARM_LOWERED);
        sleep(500);

        boolean colorState;

        if(armColor.red() > armColor.blue()) //Ball is red
        {
            telemetry.addData("Ball Color", "Red");
            telemetry.update();

            drive.encoderMoveMRGyro(90,.2,.2); //Knock off red to right
            colorState = true;
        }
        else //Ball is blue
        {
            telemetry.addData("Ball Color", "Blue");
            telemetry.update();

            drive.encoderMoveMRGyro(270,.2,.2); //Knock off red to left
            colorState = false;
        }

        armColor.enableLed(false);

        arm.setPosition(ARM_RAISED);
        sleep(500);

        drive.encoderMoveMRGyro(90, colorState ? 2.1 : 2.5, .6);

        drive.close();
    }
}
