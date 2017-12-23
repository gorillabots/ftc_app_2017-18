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
@Autonomous(name="CloseBlue", group="AndrewBot")
public class CloseBlue extends LinearOpMode {
    final double ARM_RAISED = .22;
    final double ARM_LOWERED = .9;//.88

    Drive drive;

    JewelsAndrew jewel;
    VuMarkRecognition vuMark;
    GrabberJack grabber;
    boolean IsDatBallLeft;
    @Override
    public void runOpMode()
    {
        drive = new Drive(this.hardwareMap,this.telemetry);
        jewel = new JewelsAndrew(this.hardwareMap,this.telemetry);
        vuMark = new VuMarkRecognition(this.hardwareMap, this.telemetry);
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        jewel.reset();
        grabber = new GrabberJack(this.hardwareMap,this.telemetry);
        grabber.closeinst2();
        grabber.closeinst1();


        waitForStart();
        int goodCol = vuMark.getVuMark();


        //drive.encoderMoveMRGyro(270,.5,.5);
        jewel.lowerArm();
        sleep(500);
        jewel.color.enableLed(true);

        jewel.hitBalls(drive,jewel.isRedRight(),jewel.isBlueLeft());


        sleep(500);
        telemetry.addData("status", "dunzo");
        telemetry.update();
        jewel.reset();
        sleep(500);

        telemetry.addData("zone mabob", goodCol);
        telemetry.update();



        drive.encoderMoveMRGyro2(90,.25,.3,.5);





    }
}
