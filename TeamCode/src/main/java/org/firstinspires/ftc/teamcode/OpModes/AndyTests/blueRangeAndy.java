package org.firstinspires.ftc.teamcode.OpModes.AndyTests;

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
 * Created by xiax on 1/28/2018.
 */
@Disabled
@Autonomous(name = "rangeBlue", group = "AAA")
public class blueRangeAndy extends LinearOpMode {

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

    public double startdis = 0;
    public double detectdist = 0;
    public int sensitivity = 3;

    public void detectColumn() {
        while (startdis - detectdist < sensitivity) {
            detectdist = range.cmUltrasonic();
        }
        sleep(100);
    }

    public void detectWall() {
        while (startdis - detectdist > sensitivity) {
            detectdist = range.cmUltrasonic();
        }
        sleep(100);
    }

    public void stopMotors() {
        m1.setPower(0);
        m2.setPower(0);
        m3.setPower(0);
        m4.setPower(0);
    }

    public void goRightForever(double power) {
        m1.setPower(-power);
        m2.setPower(-power);
        m3.setPower(power);
        m4.setPower(power);
    }

    public void init_() {

        grabber = new GrabberAndrew(this);

        grabber.closeinst2();
        grabber.closeinst1();

        m1 = hardwareMap.dcMotor.get("m1");
        m2 = hardwareMap.dcMotor.get("m2");
        m3 = hardwareMap.dcMotor.get("m3");
        m4 = hardwareMap.dcMotor.get("m4");


        drive = new Drive(this);
        rotateOne = hardwareMap.dcMotor.get("rotateOne");
        rotateTwo = hardwareMap.dcMotor.get("rotateTwo");

        range = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "range");

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
    }

    @Override
    public void runOpMode() {

        init_();

        waitForStart();

        int goodCol = vuMark.getVuMark();

        startdis = range.cmUltrasonic();
        detectdist = range.cmUltrasonic();

        sleep(50);

        goRightForever(.22);

        if (goodCol == 1) {
            detectColumn();
            detectWall();
            detectColumn();
            stopMotors();
        } else if (goodCol == 3) {
            detectColumn();
            detectWall();
            detectColumn();
            detectWall();
            detectColumn();
            detectWall();
            detectColumn();
            stopMotors();
        } else {
            detectColumn();
            detectWall();
            detectColumn();
            detectWall();
            detectColumn();
            stopMotors();
        }
    }

}
