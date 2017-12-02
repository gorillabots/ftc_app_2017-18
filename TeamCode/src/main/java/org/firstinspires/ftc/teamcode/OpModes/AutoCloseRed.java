package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Components.Drive;
import org.firstinspires.ftc.teamcode.Components.Jewels;

/**
 * Created by mikko on 12/1/17.
 */

@Autonomous(name="AutoCloseRed", group="Bot1")
public class AutoCloseRed extends LinearOpMode
{
    Drive drive;
    Jewels jewel;
    DcMotor extendOne;
    DcMotor extendTwo;

    public void runOpMode()
    {
        drive = new Drive(this.hardwareMap,this.telemetry);
        jewel = new Jewels(hardwareMap);
        extendTwo = hardwareMap.dcMotor.get("extend2");
        extendOne = hardwareMap.dcMotor.get("extend2");
        extendOne.setPower(-.8);
        extendTwo.setPower(-.8);
        waitForStart();

        jewel.scanPosition();
        sleep(1500);

        boolean isRed = jewel.isRed();

        jewel.betweenPosition();

        sleep(300);

        if(isRed) //Right ball is red, hit blue on left
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
