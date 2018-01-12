package org.firstinspires.ftc.teamcode.OpModes.AndrewAutos.OldAndrewAutos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Components.GrabberAndrew;
import org.firstinspires.ftc.teamcode.Components.JewelsAndrew;
import org.firstinspires.ftc.teamcode.Components.RangeCrypto;
import org.firstinspires.ftc.teamcode.Drive.Drive;
import org.firstinspires.ftc.teamcode.Vision.VuMarkRecognition;

/**
 * Created by Andy on 12/15/2017.
 */
@Disabled
@Autonomous(name = "farRedAndy", group = "AndrewBot")
public class Jan4FarRed extends LinearOpMode {
    final double ARM_RAISED = .22;
    final double ARM_LOWERED = .9;//.88

    final double leftColumnDistance = .66;
    final double centerColumnDistance = .55;
    final double rightColumnDistance = .4;

    Drive drive;
    DcMotor m1;
    DcMotor m2;
    DcMotor m3;
    DcMotor m4;
    JewelsAndrew jewel;
    VuMarkRecognition vuMark;
    GrabberAndrew grabber;
    RangeCrypto rangeCrypto;
    DcMotor rotateOne;
    DcMotor rotateTwo;
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        grabber = new GrabberAndrew(this.hardwareMap, this.telemetry);
        grabber.closeinst2();
        grabber.closeinst1();
        m1 = hardwareMap.dcMotor.get("m1");
        m2 = hardwareMap.dcMotor.get("m2");
        m3 = hardwareMap.dcMotor.get("m3");
        m4 = hardwareMap.dcMotor.get("m4");
        drive = new Drive(this.hardwareMap, this.telemetry);
        rotateOne = hardwareMap.dcMotor.get("rotateOne");
        rotateTwo = hardwareMap.dcMotor.get("rotateTwo");
        jewel = new JewelsAndrew(this.hardwareMap, this.telemetry);
        jewel.reset();
        jewel.toogleSwing(false);
        vuMark = new VuMarkRecognition(this.hardwareMap, this.telemetry);

        rangeCrypto = new RangeCrypto(this, drive.driveTrain);
        grabber.closeinst2();
        grabber.closeinst1();
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        grabber.closeinst2();
        grabber.closeinst1();
        waitForStart();

        int goodCol = vuMark.getVuMark();
        runtime.reset();
        while (runtime.seconds() < 0.00001) {
            grabber.rotateTwo(0.5);
        }
        //-------------------------------------------jewel↓↓↓↓
        jewel.toogleSwing(true);
        jewel.lowerArm();
        sleep(400);
        jewel.color.enableLed(true);

        telemetry.addData("blue left", jewel.isBlueLeft());
        telemetry.addData("red left", jewel.isRedLeft());
        telemetry.addData("blue right", jewel.isBlueRight());
        telemetry.addData("red right", jewel.isRedRight());
        telemetry.addData("col", goodCol);
        telemetry.update();
        //jewel.hitBalls(jewel.isRedLeft(), jewel.isBlueRight());
        //jewel.AHEhitBallsVariablesForBlue(jewel.isRedLeft(),jewel.isBlueLeft(),jewel.isRedRight(),jewel.isBlueRight());
        jewel.AHEhitBallsVariablesForBlueVersionTwo(
                jewel.isBlueLeft(),
                jewel.isRedLeft(),
                jewel.isBlueRight(),
                jewel.isRedRight()
        )
        ;
        telemetry.addData("status", "dunzo");
        telemetry.update();
        jewel.reset();
        jewel.toogleSwing(false);
        grabber.rotateTwo(0);
        runtime.reset();


        telemetry.addData("zone mabob", goodCol);
        telemetry.update();
        //---------------------------------jewel↑↑↑

        //------↓ post platform aligning ↓---------------
        drive.encoderMoveMRGyro2(270, .6, .5, 0.5);
        //↑ go forward
        //drive.encoderMoveMRGyro2(90, .2, .3, 0.5);

        m1.setPower(-.2);
        m2.setPower(.2);
        m3.setPower(.2);
        m4.setPower(-.2);
        sleep(1000);
        stopMotors();
        sleep(300);
        //↑ align via platform
        drive.encoderMoveMRGyro2(270, .15, .3, 0.5);
        //↑ slightly fowrward
        //------↑ post platform aligning ↑-------
        drive.turn(-90, 1, .5, .1);
        sleep(300);

        drive.driveTrain.m1.setPower(.3);
        drive.driveTrain.m2.setPower(-.3);
        drive.driveTrain.m3.setPower(-.3);
        drive.driveTrain.m4.setPower(.3);
        sleep(2000);
        drive.driveTrain.stopMotors();
        sleep(100);

        //------↓ align to the correct column ↓---
        double finishTime;
        if (goodCol == 3) {
            telemetry.addData("Going for", "R");
            //telemetry.update();
            //drive.encoderMoveMRGyro2(90, .5, .6, 0.5);
            finishTime = drive.encoderMoveMRGyro3(90, rightColumnDistance, .6, 0.5);
            telemetry.addData("Finish time", finishTime);
            telemetry.update();
            drive.turn(-90, 1, 1, .1);
            sleep(400);
        } else if (goodCol == 1) {
            telemetry.addData("Going for", "L");
            finishTime = drive.encoderMoveMRGyro3(90, leftColumnDistance, .6, 0.5);
            telemetry.addData("Finish time", finishTime);
            telemetry.update();
            drive.turn(-90, 1, 1, .1);
            sleep(400);
        } else {
            telemetry.addData("Going for", "C");
            finishTime = drive.encoderMoveMRGyro3(90, centerColumnDistance, .6, 0.5);
            telemetry.addData("Finish time", finishTime);
            telemetry.update();
            drive.turn(-90, 1, 1, .1);
            sleep(400);
        }
        //------↑ align to the correct column ↑---

        //------↓ dropping and pushing in glyph ↓--- (not done)
        telemetry.addData("Step", "A");
        telemetry.update();
        drive.encoderMoveMRGyro2(90, .15, .3, 0.5);
        sleep(300);
        grabber.openinst1();
        grabber.openinst2();
        drive.encoderMoveMRGyro2(270, .15, .3, .5);
        sleep(300);
        drive.turn(180, 1, 1, .1);
        sleep(300);

        drive.driveTrain.stopMotors();
        drive.driveTrain.m1.setPower(.3);
        drive.driveTrain.m2.setPower(-.3);
        drive.driveTrain.m3.setPower(-.3);
        drive.driveTrain.m4.setPower(.3);
        sleep(2000);
        drive.driveTrain.stopMotors();

        drive.encoderMoveMRGyro2(90, .15, .3, .5);

        if (goodCol == 1) {
            drive.encoderMoveMRGyro2(0, .2, .6, .5);
        } else if (goodCol == 3) {
            drive.encoderMoveMRGyro2(180, .3, .6, .5);
        } else {

        }
    }



       /* drive.encoderMoveMRGyro2(90, .17, .3, 0.5);
        sleep (500);
        drive.encoderMoveMRGyro2(270, .18, .3, 0.5);
        sleep (500);
        drive.encoderMoveMRGyro2(90, .07, .3, 0.5);
        drive.encoderMoveMRGyro2(270, .15, .3, 0.5);
        sleep (500);
        drive.encoderMoveMRGyro2(270, .2, .3, 0.5);*/


        /*telemetry.addData("Step", "C");
        telemetry.update();
        drive.turn(180, 2, .5 , .1);
        sleep (500);

        telemetry.addData("Step", "D");
        telemetry.update();
        drive.encoderMoveMRGyro2(270, 1.2, .5, 0.5);
        sleep (500);
        //------↑ dropping and pushuing in glyph ↑---*/

    public void stopMotors() {
        m1.setPower(0);
        m2.setPower(0);
        m3.setPower(0);
        m4.setPower(0);
    }


}
