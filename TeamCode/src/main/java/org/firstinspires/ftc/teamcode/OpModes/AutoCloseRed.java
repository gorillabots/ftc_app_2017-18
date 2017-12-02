package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

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

    public void runOpMode()
    {
        drive = new Drive(this);
        jewel = new Jewels(hardwareMap);

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
        }
        else //Right ball is blue, hit blue on right
        {
            telemetry.addData("Color", "Blue");
            telemetry.addData("Hitting", "Right");
            telemetry.update();

            jewel.hitRight();

            sleep(500);
        }

        jewel.reset();

        sleep(1500);
    }
}
