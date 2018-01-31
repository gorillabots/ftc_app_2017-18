package org.firstinspires.ftc.teamcode.OpModes.AndyTests;

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
 * Created by xiax on 1/28/2018.
 */
@Autonomous(name = "rangeRed", group = "AAA")
public class redRangeAndy extends LinearOpMode {

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
    ModernRoboticsI2cRangeSensor range;
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
        range = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "range");

        rotateOne = hardwareMap.dcMotor.get("rotateOne");
        rotateTwo = hardwareMap.dcMotor.get("rotateTwo");

        jewel = new JewelsAndrew(this.hardwareMap, this.telemetry);
        jewel.reset();
        jewel.toogleSwing(false);

        vuMark = new VuMarkRecognition(this.hardwareMap, this.telemetry);

        grabber.closeinst2();
        grabber.closeinst1();
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        grabber.closeinst2();
        grabber.closeinst1();

        waitForStart();

        int goodCol = vuMark.getVuMark();

        double startdis = range.cmUltrasonic();
        double detectdist = range.cmUltrasonic();

        m1.setPower(.3);
        m2.setPower(.3);
        m3.setPower(-.3);
        m4.setPower(-.3);

        if (goodCol == 3) {

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
    }

    public void stopMotors() {
        m1.setPower(0);
        m2.setPower(0);
        m3.setPower(0);
        m4.setPower(0);
    }
}
