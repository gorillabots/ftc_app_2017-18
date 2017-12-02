package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Components.Drive;

/**
 * Created by Jarred on 10/29/2017.
 */

@Disabled
@Autonomous(name="AutoCloseRedSecond", group="SecondBot")
public class AutoCloseRedSecond extends LinearOpMode
{
    final double ARM_RAISED = .22;
    final double ARM_LOWERED = .9;//.88

    Drive drive;
    Servo arm;
    ColorSensor armColor;

    @Override
    public void runOpMode()
    {
        drive = new Drive(this);
        arm = hardwareMap.servo.get("arm");
        armColor=hardwareMap.colorSensor.get("ballColor");
        armColor.enableLed(false);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        arm.setPosition(ARM_LOWERED);
        armColor.enableLed(true);
        sleep(500);

        boolean isRed = armColor.red() > armColor.blue();

        telemetry.addData("Color", isRed ? "Red" : "Blue");
        telemetry.addData("Driving", isRed ? "Left" : "Right");
        telemetry.update();

        if(isRed) //Ball is red
        {
            drive.encoderMoveMRGyro(270,.2,.2); //Knock off red to left
        }
        else //Ball is blue
        {
            drive.encoderMoveMRGyro(90,.2,.2); //Knock off blue to right
        }

        armColor.enableLed(false);

        arm.setPosition(ARM_RAISED);
        sleep(500);

        drive.encoderMoveMRGyro(270, isRed ? 2.1 : 2.5, .6);

        drive.close();
    }
}
