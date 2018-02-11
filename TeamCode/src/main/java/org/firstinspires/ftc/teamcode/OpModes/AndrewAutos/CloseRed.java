package org.firstinspires.ftc.teamcode.OpModes.AndrewAutos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Components.Constants;
import org.firstinspires.ftc.teamcode.Components.GrabberAndrew;
import org.firstinspires.ftc.teamcode.Components.JewelsAndrew;
import org.firstinspires.ftc.teamcode.Components.RangeCrypto;
import org.firstinspires.ftc.teamcode.Drive.Drive;
import org.firstinspires.ftc.teamcode.Vision.VuMarkRecognition;

/**
 * Created by Andy on 12/15/2017.
 */

@Autonomous(name = "closeRed", group = "AndrewBot")
public class CloseRed extends LinearOpMode {
    final double ARM_RAISED = .22;
    final double ARM_LOWERED = .9;//.88



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

        grabber = new GrabberAndrew(this);
        grabber.closeinst2();
        grabber.closeinst1();
        drive = new Drive(this.hardwareMap, this.telemetry);
        rotateOne = hardwareMap.dcMotor.get("rotateOne");
        rotateTwo = hardwareMap.dcMotor.get("rotateTwo");
        jewel = new JewelsAndrew(this);
        jewel.stow();
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
        grabber.rotateTwo(0.5); //start rotation

        jewel.lowerArm();
        sleep(400);
        jewel.hitBalls(JewelsAndrew.BallColor.BLUE);
        jewel.upright();
        sleep(500);
        jewel.stow();
        grabber.rotateTwo(0);
        runtime.reset();

        drive.encoderMoveMRGyro2(270, .75, .3, 0.5);

        m1.setPower(-.2);
        m2.setPower(.2);
        m3.setPower(.2);
        m4.setPower(-.2);
        sleep(1500);
        stopMotors();
        sleep(400);

        if (goodCol == 1) {
            drive.encoderMoveMRGyro2(270, Constants.leftColumnDistance, .3, .5);
        } else if (goodCol == 3) {
            // drive.encoderMoveMRGyro2(270, 0, .3, .5); do nothing due to bot allaignment
        } else {
            drive.encoderMoveMRGyro2(270, Constants.centerColumnDistance, .3, .5);
        }

        drive.turn(90, 2, .25, .1);
        sleep (400);

        drive.encoderMoveMRGyro2(90, .2, .3, .5);

        grabber.openinst1();
        grabber.openinst2();

        drive.encoderMoveMRGyro2(270, .2, .3, .5);

        drive.turn(180, 2, 1, .1);
        sleep(400);

        stopMotors();
        m1.setPower(.3);
        m2.setPower(-.3);
        m3.setPower(-.3);
        m4.setPower(.3);
        sleep(1500);
        stopMotors();
        sleep(500);

        drive.encoderMoveMRGyro2(90, .15, .3, .2);

        if (goodCol == 1) {
            drive.encoderMoveMRGyro2(0, .15, .6, .5);
        } else if (goodCol == 3) {
            drive.encoderMoveMRGyro2(180, .2, .6, .5);
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
