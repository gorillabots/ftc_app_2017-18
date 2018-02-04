package org.firstinspires.ftc.teamcode.OpModes.AndrewAutos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
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
@Autonomous(name = "farRed", group = "AndrewBot")
public class FarRed extends LinearOpMode {
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

        m1 = hardwareMap.dcMotor.get("m1");
        m2 = hardwareMap.dcMotor.get("m2");
        m3 = hardwareMap.dcMotor.get("m3");
        m4 = hardwareMap.dcMotor.get("m4");

        drive = new Drive(this.hardwareMap, this.telemetry);

        rotateOne = hardwareMap.dcMotor.get("rotateOne");
        rotateTwo = hardwareMap.dcMotor.get("rotateTwo");

        jewel = new JewelsAndrew(this);
        jewel.stow();

        vuMark = new VuMarkRecognition(this.hardwareMap, this.telemetry);

        grabber.closeinst2();
        grabber.closeinst1();
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        grabber.closeinst2();
        grabber.closeinst1();
        waitForStart();
        int goodCol = vuMark.getVuMark();

        grabber.rotateTwo(0.5); //start rotation

        jewel.lowerArm();
        sleep(500);
        jewel.hitBalls(JewelsAndrew.BallColor.BLUE);
        jewel.upright();
        sleep(500);
        jewel.stow();
        grabber.rotateTwo(0);
        runtime.reset();

        drive.encoderMoveMRGyro2(270, .6, .5, 0.5); //off the platform

        m1.setPower(-.2);
        m2.setPower(.2);
        m3.setPower(.2);
        m4.setPower(-.2);
        sleep(1000);
        stopMotors();
        sleep(300);

        drive.encoderMoveMRGyro2(270, .18, .3, 0.5);

        drive.turn(-90, 2, .5, .1);
        sleep(300);

        m1.setPower(.3);
        m2.setPower(-.3);
        m3.setPower(-.3);
        m4.setPower(.3);
        sleep(2000);
        stopMotors();
        sleep(100);

        if (goodCol == 3) {
            drive.encoderMoveMRGyro2(90, Constants.rightColumnDistance, .5, 0.5);
        } else if (goodCol == 1) {
            drive.encoderMoveMRGyro2(90, Constants.leftColumnDistance, .5, 0.5);
        } else {
            drive.encoderMoveMRGyro2(90, Constants.centerColumnDistance, .5, 0.5);
        }
        sleep(400);

        drive.turn(-90, 2, .25, .1);

        drive.encoderMoveMRGyro2(90, .1, .3, 0.5);

        grabber.openinst1();
        grabber.openinst2();

        drive.encoderMoveMRGyro2(270, .125, .3, .5);
        sleep(300);

        drive.turn(180, 2, 1, .1);
        sleep(300);

        stopMotors();

        m1.setPower(.3);
        m2.setPower(-.3);
        m3.setPower(-.3);
        m4.setPower(.3);
        sleep(2000);
        stopMotors();

        drive.encoderMoveMRGyro2(90, .15, .3, .5);

        if (goodCol == 1) {
            drive.encoderMoveMRGyro2(0, .2, .6, .5);
        } else if (goodCol == 3) {
            drive.encoderMoveMRGyro2(180, .3, .6, .5);
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
