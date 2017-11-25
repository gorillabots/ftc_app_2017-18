package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Components.ColorHelper;
import org.firstinspires.ftc.teamcode.Components.Drive;
import org.firstinspires.ftc.teamcode.Vision.VuMarkRecognition;

/**
 * Created by Jarred on 10/29/2017.
 */

@Autonomous(name="AutoFarBlue", group="Autonomous")
public class AutoFarBlue extends LinearOpMode
{
    final double ARM_RAISED = .22;
    final double ARM_LOWERED = .88;

    Drive drive;
    Servo arm;
    ColorSensor armColor;
    VuMarkRecognition vuMarks;

    @Override
    public void runOpMode()
    {
        drive = new Drive(this);
        //drive.init(0);
        arm = hardwareMap.servo.get("arm");
        armColor=hardwareMap.colorSensor.get("ballColor");
        armColor.enableLed(false);
        armColor.enableLed(true);
        vuMarks = new VuMarkRecognition();
        vuMarks.init(hardwareMap);

        waitForStart();

        int vuMark = vuMarks.getVuMark();

        telemetry.addData("VuMark", vuMark);
        telemetry.update();

        arm.setPosition(ARM_LOWERED);
        sleep(500);

        if(armColor.red() > armColor.blue()) //Ball is red
        {
            drive.encoderMoveMRGyro(270,.05,.2); //Drive right
        }
        else //Ball is blue
        {
            drive.encoderMoveMRGyro(90,.05,.2); //Drive left
        }

        arm.setPosition(ARM_RAISED);
        sleep(500);
    }
}
