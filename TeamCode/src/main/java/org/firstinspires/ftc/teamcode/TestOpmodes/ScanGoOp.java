package org.firstinspires.ftc.teamcode.TestOpmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Drive.Drive;
import org.firstinspires.ftc.teamcode.Vision.VuMarkRecognition;

//Created by Mikko on 2017-12-15

@Autonomous(name="ScanGoOp", group="AndrewBot")
public class ScanGoOp extends LinearOpMode
{
    Drive drive;
    VuMarkRecognition vuMarks;

    @Override
    public void runOpMode()
    {
        drive = new Drive(hardwareMap, telemetry);

        telemetry.addData("Status", "Initializing Vuforia");
        telemetry.update();

        vuMarks = new VuMarkRecognition(this.hardwareMap, this.telemetry);


        telemetry.addData("Status", "Ready!");
        telemetry.update();

        waitForStart();

        int vuMark = vuMarks.getVuMark();

        drive.encoderMoveMRGyro(270, 2, .6);

        telemetry.addData("VuMark", vuMark);
        telemetry.update();
    }
}
