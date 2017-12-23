package org.firstinspires.ftc.teamcode.TestOpmodes;

//Created by Mikko on 2017-12-19

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Components.RangeCrypto;
import org.firstinspires.ftc.teamcode.Drive.Drive;

@Autonomous(name="RangeCryptoTestOp", group="Test")
public class RangeCryptoTestOp extends LinearOpMode
{
    public void runOpMode()
    {
        Drive drive = new Drive(hardwareMap, telemetry);

        RangeCrypto rangeCrypto = new RangeCrypto(this, drive.driveTrain);

        //drive.resetGyro();

        double dist = rangeCrypto.range.cmUltrasonic();

        telemetry.addData("Distance", dist);
        telemetry.update();

        waitForStart();



        //rangeCrypto.updateOffset();
        //rangeCrypto.go(3);
    }
}
