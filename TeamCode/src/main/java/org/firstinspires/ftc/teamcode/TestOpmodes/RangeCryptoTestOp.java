package org.firstinspires.ftc.teamcode.TestOpmodes;

//Created by Mikko on 2017-12-19

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Components.RangeCrypto;
import org.firstinspires.ftc.teamcode.Drive.Drive;
@Autonomous(name="RangeCryptoTestOp", group="Test")
public class RangeCryptoTestOp extends LinearOpMode
{
    public void runOpMode()
    {
        Drive drive = new Drive(this);
        drive.resetGyro();

        RangeCrypto rangeCrypto = new RangeCrypto(this, drive.driveTrain);

        waitForStart();

        //drive.turn(90, 3, 1, .1);

        rangeCrypto.updateOffset();
        rangeCrypto.go(3);

        sleep(1000);

        drive.encoderMoveMRGyro2(180, .16, .5, .5);

        sleep(1000);

        rangeCrypto.approach(15, .35);

    }
}
