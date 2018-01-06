package org.firstinspires.ftc.teamcode.OpModes.AndrewAutos.OldAndrewAutos;

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
@Autonomous(name="CloseRed", group="AndrewBot")
public class CloseRed extends LinearOpMode {
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
        drive = new Drive(this);

        jewel = new JewelsAndrew(this.hardwareMap,this.telemetry);
        jewel.reset();
        jewel.toggleSwing(false);
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
        jewel.toggleSwing(true);
        jewel.lowerArm();
        sleep(500);
        jewel.color.enableLed(true);

        telemetry.addData("blue left", jewel.leftSensorIsBlue());
        telemetry.addData("red left", jewel.leftSensorIsRed());
        telemetry.addData("blue right", jewel.rightSensorIsBlue());
        telemetry.addData("red right", jewel.rightSensorIsRed());
        telemetry.addData("col", goodCol);telemetry.update();
        sleep
        (500);
        jewel.hitBalls(
                jewel.leftSensorIsBlue(),
                jewel.leftSensorIsRed(),
                jewel.rightSensorIsBlue(),
                jewel.rightSensorIsRed()
        );

        //drive.encoderMoveMRGyro(180, .3, 1);

        sleep(500);
        telemetry.addData("status", "dunzo");
        telemetry.update();
        jewel.reset();
        jewel.toggleSwing(false);
        sleep(500);

        telemetry.addData("zone mabob", goodCol);
        telemetry.update();

        drive.encoderMoveMRGyro2(270, .6, .3, .5);
        drive.turn(270, 2, 1, .1);

        //drive.encoderMoveMRGyro2(180, .5, .8, .5);



        rangeCrypto.approach(40,.35);
        rangeCrypto.updateOffset(); //Scan wall for reference distance
        rangeCrypto.go(goodCol); //Count off 3 things while moving - 2-Left, 3-Center, 4-Right
        drive.encoderMoveMRGyro2(180,.007,.3,.5);

        //drive.encoderMoveMRGyro2(180, .16, .5, .5);



        rangeCrypto.approach(15, .35); //Approach box with range sensor
        grabber.openinst1();
        grabber.openinst2();
        grabber.wide1();
        grabber.wide2();
        drive.encoderMoveMRGyro(90,.5,.5);
        drive.encoderMoveMRGyro(270,.75,.5);
    }
}
