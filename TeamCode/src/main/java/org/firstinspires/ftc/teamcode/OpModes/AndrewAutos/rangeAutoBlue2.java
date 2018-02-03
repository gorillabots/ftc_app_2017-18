package org.firstinspires.ftc.teamcode.OpModes.AndrewAutos;


import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
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

@Autonomous(name = "rangeAutoblue2", group = "AndrewBot")
public class rangeAutoBlue2 extends LinearOpMode {

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
        rangeCrypto = new RangeCrypto(this, drive.driveTrain);

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
        sleep(400);
        grabber.rotateTwo(0);
        runtime.reset();

        drive.encoderMoveMRGyro2(90, .7, .5, 0.5);

        m1.setPower(.2);
        m2.setPower(-.2);
        m3.setPower(-.2);
        m4.setPower(.2);
        sleep(1200);
        stopMotors();
        sleep(430);

        drive.encoderMoveMRGyro2(90, .14, .3, 0.5);
        sleep(300);
        drive.encoderMoveMRGyro2(0, .2, 1, 0.5);//left

        double startdis = range.cmUltrasonic();
        double detectdist = range.cmUltrasonic();

        m1.setPower(-.25);
        m2.setPower(-.25);
        m3.setPower(.25);
        m4.setPower(.25);

        if (goodCol == 3) {

            while (startdis - detectdist < 2) {
                detectdist = range.cmUltrasonic();
                telemetry.addData("dist", detectdist);
                telemetry.addData("Status", "detecting");
                telemetry.update();
            }
            telemetry.addData("Status", "FOUND 1");
            telemetry.update();
            sleep(600);

            while (startdis - detectdist < 2) {
                detectdist = range.cmUltrasonic();
                telemetry.addData("Status", "detecting");
                telemetry.update();
            }

            telemetry.addData("Status", "FOUND 2");
            telemetry.update();
            sleep(600);

            while (startdis - detectdist < 2) {
                detectdist = range.cmUltrasonic();
                telemetry.addData("Status", "detecting");
                telemetry.update();
            }
            telemetry.addData("Status", "FOUND 3");
            telemetry.update();
            sleep(600);

            while (startdis - detectdist < 2) {
                detectdist = range.cmUltrasonic();
                telemetry.addData("Status", "detecting");
                telemetry.update();
            }
            stopMotors();
            telemetry.addData("Status", "done");
            telemetry.update();
            sleep(600);

        } else if (goodCol == 1) {

            while (startdis - detectdist < 2) {
                detectdist = range.cmUltrasonic();
                telemetry.addData("dist", detectdist);
                telemetry.addData("Status", "detecting");
                telemetry.update();
            }
            telemetry.addData("Status", "FOUND 1");
            telemetry.update();
            sleep(600);

            while (startdis - detectdist < 2) {
                detectdist = range.cmUltrasonic();
                telemetry.addData("Status", "detecting");
                telemetry.update();
            }
            stopMotors();
            telemetry.addData("Status", "done");
            telemetry.update();
            sleep(600);
        } else if (goodCol == 2 ||goodCol == 0){
            while (startdis - detectdist < 2) {
                detectdist = range.cmUltrasonic();
                telemetry.addData("Status", "detecting");
                telemetry.update();
            }
            telemetry.addData("Status", "FOUND 1");
            telemetry.update();
            sleep(600);

            while (startdis - detectdist < 2) {
                detectdist = range.cmUltrasonic();
                telemetry.addData("Status", "detecting");
                telemetry.update();
            }
            telemetry.addData("Status", "FOUND 2");
            telemetry.update();
            sleep(600);

            while (startdis - detectdist < 2) {
                detectdist = range.cmUltrasonic();
                telemetry.addData("Status", "detecting");
                telemetry.update();
            }
            stopMotors();
            telemetry.addData("Status", "done");
            telemetry.update();
            sleep(600);
        }

        sleep(2000);
        drive.encoderMoveMRGyro2(180, .2, .5, .5);

        m1.setPower(-.2);
        m2.setPower(.2);
        m3.setPower(.2);
        m4.setPower(-.2);
        sleep (1500);
        grabber.openinst2();
        grabber.openinst1();
        sleep (1000);

        drive.encoderMoveMRGyro2(270,.1,.6,.5);
        sleep(300);
        drive.encoderMoveMRGyro2(90,.2,.6,.5);

    }
    public void stopMotors() {
        m1.setPower(0);
        m2.setPower(0);
        m3.setPower(0);
        m4.setPower(0);
    }
}