package org.firstinspires.ftc.teamcode.OpModes.AndrewAutos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Components.GrabberJack;
import org.firstinspires.ftc.teamcode.Components.JewelsAndrew;
import org.firstinspires.ftc.teamcode.Components.RangeCrypto;
import org.firstinspires.ftc.teamcode.Drive.Drive;
import org.firstinspires.ftc.teamcode.Vision.VuMarkRecognition;
import org.opencv.core.Range;

/**
 * Created by Jarred on 12/15/2017.
 */
@Autonomous(name="CloseBlue", group="AndrewBot")
public class CloseBlue extends LinearOpMode {
    final double ARM_RAISED = .22;
    final double ARM_LOWERED = .9;//.88

    Drive drive;

    JewelsAndrew jewel;
    VuMarkRecognition vuMark;
    GrabberJack grabber;
    RangeCrypto rangeCrypto;

    @Override
    public void runOpMode()
    {
        drive = new Drive(this.hardwareMap,this.telemetry);

        jewel = new JewelsAndrew(this.hardwareMap,this.telemetry);
        jewel.reset();

        vuMark = new VuMarkRecognition(this.hardwareMap, this.telemetry);

        grabber = new GrabberJack(this.hardwareMap,this.telemetry);
        grabber.closeinst2();
        grabber.closeinst1();

        rangeCrypto = new RangeCrypto(this, drive.driveTrain);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        int goodCol = vuMark.getVuMark();


        //drive.encoderMoveMRGyro(270,.5,.5);
        jewel.lowerArm();
        sleep(500);
        jewel.color.enableLed(true);

        jewel.hitBalls(drive,jewel.isRedRight(),jewel.isBlueLeft());

        //drive.encoderMoveMRGyro2(180, 1, 1, .5);

        sleep(500);
        telemetry.addData("status", "dunzo");
        telemetry.update();
        jewel.reset();
        sleep(500);

        telemetry.addData("zone mabob", goodCol);
        telemetry.update();

        drive.encoderMoveMRGyro2(90, .5, .3, .5);
        drive.encoderMoveMRGyro2(180, .5, .8, .5);

        drive.turn(90, 2, 1, .1);


        rangeCrypto.updateOffset(); //Scan wall for reference distance
        rangeCrypto.go(3); //Count off 3 things while moving - 2-Left, 3-Center, 4-Right

        sleep(1000);

        drive.encoderMoveMRGyro2(180, .16, .5, .5); //Move a bit right to align

        sleep(1000);

        rangeCrypto.approach(15, .35); //Approach box with range sensor
    }
}
