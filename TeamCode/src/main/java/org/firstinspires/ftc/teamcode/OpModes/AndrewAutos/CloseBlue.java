package org.firstinspires.ftc.teamcode.OpModes.AndrewAutos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Components.GrabberJack;
import org.firstinspires.ftc.teamcode.Components.JewelsAndrew;
import org.firstinspires.ftc.teamcode.Components.RangeCrypto;
import org.firstinspires.ftc.teamcode.Drive.Drive;
import org.firstinspires.ftc.teamcode.Vision.VuMarkRecognition;
import org.opencv.core.Range;

/**
 * Created by Jarred on 12/15/2017.
 */
@Autonomous(name = "CloseBlue", group = "AndrewBot")
public class CloseBlue extends LinearOpMode {
    final double ARM_RAISED = .22;
    final double ARM_LOWERED = .9;//.88

    Drive drive;

    JewelsAndrew jewel;
    VuMarkRecognition vuMark;
    GrabberJack grabber;
    RangeCrypto rangeCrypto;

    @Override
    public void runOpMode() {
        drive = new Drive(this.hardwareMap, this.telemetry);

        jewel = new JewelsAndrew(this.hardwareMap, this.telemetry);
        jewel.reset();
        jewel.toogleSwing(false);
        vuMark = new VuMarkRecognition(this.hardwareMap, this.telemetry);

        grabber = new GrabberJack(this.hardwareMap, this.telemetry);
        grabber.closeinst2();
        grabber.closeinst1();

        rangeCrypto = new RangeCrypto(this, drive.driveTrain);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        int[]goodColMeasure = new int[5];
        goodColMeasure[0] = vuMark.getVuMark();


        //drive.encoderMoveMRGyro(270,.5,.5);
        jewel.toogleSwing(true);
        jewel.lowerArm();
        sleep(500);
        jewel.color.enableLed(true);
        ElapsedTime time = new ElapsedTime();
        time.startTime();

        while (time.milliseconds() < 10000) {
            telemetry.addData("blue left", jewel.isBlueLeft());
            telemetry.addData("red left", jewel.isRedLeft());
            telemetry.addData("blue right", jewel.isBlueRight());
            telemetry.addData("red right", jewel.isRedRight());

            telemetry.update();
        }

        jewel.hitBalls(false, jewel.isRedRight());
         goodColMeasure[1] = vuMark.getVuMark();

        //drive.encoderMoveMRGyro(180, .3, 1);

        sleep(500);
        telemetry.addData("status", "dunzo");
        telemetry.update();
        jewel.reset();
        jewel.toogleSwing(false);
        sleep(500);



        drive.encoderMoveMRGyro2(90, .6, .3, .5);
        goodColMeasure[2] = vuMark.getVuMark();
        int goodCol = 0;

        for(int i=0;i<goodColMeasure.length; i++){
            if(goodColMeasure[i]!= 0){
                goodCol = goodColMeasure[i];
                break;
            }

        }
        if(goodCol==0){
            goodCol=1;
        }
        telemetry.addData("zone mabob", goodCol);
        telemetry.update();
        drive.turn(90, 2, 1, .1);


        sleep(3000);
        //drive.encoderMoveMRGyro2(180, .5, .8, .5);


        rangeCrypto.approach(40, .35);
        rangeCrypto.updateOffset(); //Scan wall for reference distance
        rangeCrypto.go(goodCol+1);

        //Count off 3 things while moving - 2-Left, 3-Center, 4-Right
        drive.encoderMoveMRGyro2(180,.007,.3,.5);

        //drive.encoderMoveMRGyro2(180, .16, .5, .5);



        rangeCrypto.approach(15, .35); //Approach box with range sensor
        grabber.openinst1();
        grabber.openinst2();
        grabber.wide1();
        grabber.wide2();
        drive.encoderMoveMRGyro(90,.5,.5);
        drive.encoderMoveMRGyro(270,.25,.5);



    }

}
