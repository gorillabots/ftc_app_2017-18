package org.firstinspires.ftc.teamcode.OpModes.AndyTests;

//MIKKO DO NOT DELETE THIS PROGRAM... IT IS VERY IMPORTANT
//MIKKO DO NOT DELETE THIS PROGRAM... IT IS VERY IMPORTANT
//MIKKO DO NOT DELETE THIS PROGRAM... IT IS VERY IMPORTANT
//MIKKO DO NOT DELETE THIS PROGRAM... IT IS VERY IMPORTANT
//MIKKO DO NOT DELETE THIS PROGRAM... IT IS VERY IMPORTANT
//MIKKO DO NOT DELETE THIS PROGRAM... IT IS VERY IMPORTANT
//MIKKO DO NOT DELETE THIS PROGRAM... IT IS VERY IMPORTANT
//MIKKO DO NOT DELETE THIS PROGRAM... IT IS VERY IMPORTANT
//MIKKO DO NOT DELETE THIS PROGRAM... IT IS VERY IMPORTANT
//MIKKO DO NOT DELETE THIS PROGRAM... IT IS VERY IMPORTANT
//MIKKO DO NOT DELETE THIS PROGRAM... IT IS VERY IMPORTANT
//MIKKO DO NOT DELETE THIS PROGRAM... IT IS VERY IMPORTANT
//MIKKO DO NOT DELETE THIS PROGRAM... IT IS VERY IMPORTANT
//MIKKO DO NOT DELETE THIS PROGRAM... IT IS VERY IMPORTANT
//MIKKO DO NOT DELETE THIS PROGRAM... IT IS VERY IMPORTANT
//MIKKO DO NOT DELETE THIS PROGRAM... IT IS VERY IMPORTANT
//MIKKO DO NOT DELETE THIS PROGRAM... IT IS VERY IMPORTANT
//MIKKO DO NOT DELETE THIS PROGRAM... IT IS VERY IMPORTANT
//MIKKO DO NOT DELETE THIS PROGRAM... IT IS VERY IMPORTANT
//MIKKO DO NOT DELETE THIS PROGRAM... IT IS VERY IMPORTANT
//MIKKO DO NOT DELETE THIS PROGRAM... IT IS VERY IMPORTANT
//MIKKO DO NOT DELETE THIS PROGRAM... IT IS VERY IMPORTANT
//MIKKO DO NOT DELETE THIS PROGRAM... IT IS VERY IMPORTANT

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
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
@Autonomous(name = "wonderousProgramBlue", group = "AndrewBot")
public class wonderousProgram extends LinearOpMode {

    Drive drive;
    DcMotor m1;
    DcMotor m2;
    DcMotor m3;
    DcMotor m4;
    JewelsAndrew jewel;
    VuMarkRecognition vuMark;
    GrabberAndrew grabber;
    RangeCrypto rangeCrypto;
    ModernRoboticsI2cRangeSensor range;
    DcMotor rotateOne;
    DcMotor rotateTwo;
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {

        grabber = new GrabberAndrew(this.hardwareMap, this.telemetry);
        grabber.closeinst2();
        grabber.closeinst1();
        drive = new Drive(this.hardwareMap, this.telemetry);
        range = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "range");

        rotateOne = hardwareMap.dcMotor.get("rotateOne");
        rotateTwo = hardwareMap.dcMotor.get("rotateTwo");

        jewel = new JewelsAndrew(this.hardwareMap, this.telemetry);
        jewel.reset();
        jewel.toogleSwing(false);

        vuMark = new VuMarkRecognition(this.hardwareMap, this.telemetry);
        m1 = hardwareMap.dcMotor.get("m1");
        m2 = hardwareMap.dcMotor.get("m2");
        m3 = hardwareMap.dcMotor.get("m3");
        m4 = hardwareMap.dcMotor.get("m4");

        grabber.closeinst2();
        grabber.closeinst1();

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        grabber.closeinst2();
        grabber.closeinst1();

        waitForStart();

        int goodCol = vuMark.getVuMark();
        runtime.reset();
        grabber.rotateTwo(0.5);

        jewel.toogleSwing(true);
        jewel.lowerArm();
        sleep(400);
        jewel.color.enableLed(true);
        jewel.AHEhitBallsVariablesForBlueVersionTwo(
                jewel.isRedLeft(),
                jewel.isBlueLeft(),
                jewel.isRedRight(),
                jewel.isBlueRight()
        )
        ;
        jewel.reset();
        jewel.toogleSwing(false);
        sleep(500);
        grabber.rotateTwo(0);
        runtime.reset();

        drive.encoderMoveMRGyro2(90, .7, .5, 0.5);

        m1.setPower(.2);
        m2.setPower(-.2);
        m3.setPower(-.2);
        m4.setPower(.2);
        sleep(1000);
        stopMotors();
        sleep(400);

        drive.encoderMoveMRGyro2(90, .15, .3, 0.5);
        sleep(300);
        drive.encoderMoveMRGyro2(0,.15,1,0.5);//left
        sleep (1000);

        double startdis = range.cmUltrasonic();
        double detectdist = range.cmUltrasonic();

        m1.setPower(-.3);
        m2.setPower(-.3);
        m3.setPower(.3);
        m4.setPower(.3);

        if (goodCol == 1) {

            while (startdis - detectdist < 4) {
                detectdist = range.cmUltrasonic();
                telemetry.addData("Status", "detecting");
                telemetry.update();
            }
            stopMotors();
            telemetry.addData("Status", "done");
            telemetry.update();
            sleep(2000);

        } else if (goodCol == 2 || goodCol == 0) {

            while (startdis - detectdist < 4) {
                detectdist = range.cmUltrasonic();
                telemetry.addData("Status", "detecting");
                telemetry.update();
            }
            telemetry.addData("Status", "FOUND 1");
            telemetry.update();
            sleep(750);

            while (startdis - detectdist < 4) {
                detectdist = range.cmUltrasonic();
                telemetry.addData("Status", "detecting");
                telemetry.update();
            }
            stopMotors();
            telemetry.addData("Status", "done");
            telemetry.update();
            sleep(500);
        } else {
            while (startdis - detectdist < 4) {
                detectdist = range.cmUltrasonic();
                telemetry.addData("Status", "detecting");
                telemetry.update();
            }
            telemetry.addData("Status", "FOUND 1");
            telemetry.update();
            sleep(750);

            while (startdis - detectdist < 4) {
                detectdist = range.cmUltrasonic();
                telemetry.addData("Status", "detecting");
                telemetry.update();
            }
            telemetry.addData("Status", "FOUND 2");
            telemetry.update();
            sleep(750);

            while (startdis - detectdist < 4) {
                detectdist = range.cmUltrasonic();
                telemetry.addData("Status", "detecting");
                telemetry.update();
            }
            stopMotors();
            telemetry.addData("Status", "done");
            telemetry.update();
            sleep(500);
        }

        drive.encoderMoveMRGyro2(180,.3,1,.5);
        drive.encoderMoveMRGyro2(90, .15, .3, 0.5);

        grabber.openinst1();
        grabber.openinst2();

        m1.setPower(-.2);
        m2.setPower(.2);
        m3.setPower(.2);
        m4.setPower(-.2);
        sleep(1000);
        stopMotors();
        sleep(500);

        drive.encoderMoveMRGyro2(270, .15, .3, 0.5);
        sleep(200);

        drive.turn(180, 2, 1, .1);
        sleep(200);

        stopMotors();
        m1.setPower(.3);
        m2.setPower(-.3);
        m3.setPower(-.3);
        m4.setPower(.3);
        sleep(2000);
        stopMotors();
        sleep(300);

        drive.encoderMoveMRGyro2(90, .15, .3, .5);

        if (goodCol == 3) {
            drive.encoderMoveMRGyro2(180, .3, .6, .5);
        } else if (goodCol == 1) {
            drive.encoderMoveMRGyro2(0, .15, .6, .5);
        } else {

        }
    }

    public void stopMotors() {
        m1.setPower(0);
        m2.setPower(0);
        m3.setPower(0);
        m4.setPower(0);
    }
}