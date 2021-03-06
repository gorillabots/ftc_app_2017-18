package org.firstinspires.ftc.teamcode.OpModes.JackAutos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Drive.Drive;
import org.firstinspires.ftc.teamcode.Components.JewelsJack;

/**
 * Created by mikko on 12/1/17.
 */
@Disabled
@Autonomous(name="FarBlueJack", group="JackBot")
public class FarBlueJack extends LinearOpMode
{
    Drive drive;
    JewelsJack jewel;
    DcMotor extendOne;
    DcMotor extendTwo;

    public void runOpMode()
    {
        drive = new Drive(this);
        jewel = new JewelsJack(hardwareMap);
        extendTwo = hardwareMap.dcMotor.get("extend2");
        extendOne = hardwareMap.dcMotor.get("extend2");
        extendOne.setPower(-.8);
        extendTwo.setPower(-.8);
        waitForStart();

        jewel.scanPosition();
        sleep(1500);

        boolean isBlue = jewel.isBlue();

        jewel.betweenPosition();

        sleep(300);

        if(isBlue) //Right ball is red, hit blue on left
        {
            telemetry.addData("Color", "Red");
            telemetry.addData("Hitting", "Left");
            telemetry.update();

            drive.encoderMoveMRGyro(180,.1,.3);
            jewel.scanPosition();
            sleep(1000);
        }
        else //Right ball is blue, hit blue on right
        {
            telemetry.addData("Color", "Blue");
            telemetry.addData("Hitting", "Right");
            telemetry.update();

            jewel.hitRight();

            sleep(1000);
        }

        jewel.reset();

    }
}
