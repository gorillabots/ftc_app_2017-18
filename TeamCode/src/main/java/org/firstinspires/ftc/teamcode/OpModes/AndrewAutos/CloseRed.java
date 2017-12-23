package org.firstinspires.ftc.teamcode.OpModes.AndrewAutos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Components.GrabberJack;
import org.firstinspires.ftc.teamcode.Components.JewelsAndrew;
import org.firstinspires.ftc.teamcode.Drive.Drive;
import org.firstinspires.ftc.teamcode.Vision.VuMarkRecognition;

/**
 * Created by Jarred on 12/15/2017.
 */
@Autonomous(name="CloseRed", group="AndrewBot")
public class CloseRed extends LinearOpMode {
    final double ARM_RAISED = .22;
    final double ARM_LOWERED = .9;//.88
    GrabberJack grabber;
    Drive drive;

    JewelsAndrew jewel;
    VuMarkRecognition vuMarks;

    @Override
    public void runOpMode()
    {
        drive = new Drive(this.hardwareMap,this.telemetry);
        jewel = new JewelsAndrew(this.hardwareMap,this.telemetry);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        jewel.reset();
        grabber = new GrabberJack(this.hardwareMap,this.telemetry);
        grabber.closeinst2();
        grabber.closeinst1();
        vuMarks = new VuMarkRecognition(this.hardwareMap);

        waitForStart();
        int goodCol = vuMarks.getVuMark();
        //drive.encoderMoveMRGyro(270,.5,.5);
        jewel.lowerArm();
        sleep(500);
        jewel.color.enableLed(true);

        jewel.hitBalls(drive,jewel.isBlue());


        sleep(500);
        telemetry.addData("status", "dunzo");
        telemetry.update();
        jewel.reset();
        sleep(500);


    }
}
