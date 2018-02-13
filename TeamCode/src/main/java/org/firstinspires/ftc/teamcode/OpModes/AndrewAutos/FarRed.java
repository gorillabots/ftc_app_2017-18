package org.firstinspires.ftc.teamcode.OpModes.AndrewAutos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

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

    double centerColumnDistance = .5;//.51
    double distanceBetween = .183;

    double leftColumnDistance = centerColumnDistance + distanceBetween;
    double rightColumnDistance = centerColumnDistance - distanceBetween;

    Drive drive;
    DcMotor m1;
    DcMotor m2;
    DcMotor m3;
    DcMotor m4;
    JewelsAndrew jewel;
    VuMarkRecognition vuMark;
    GrabberAndrew grabber;
    DcMotor rotateOne;
    DcMotor rotateTwo;

    Servo linkage;
    Servo clawTop;
    Servo clawBottom;
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        grabber = new GrabberAndrew(this);
        linkage = hardwareMap.servo.get("linkage");
        clawTop = hardwareMap.servo.get("clawTop");
        clawBottom = hardwareMap.servo.get("clawBottom");
        linkage.setPosition(.858);
        clawBottom.setPosition(0);
        clawTop.setPosition(1);
        grabber.closeinst1();

        m1 = hardwareMap.dcMotor.get("m1");
        m2 = hardwareMap.dcMotor.get("m2");
        m3 = hardwareMap.dcMotor.get("m3");
        m4 = hardwareMap.dcMotor.get("m4");

        drive = new Drive(this);

        rotateOne = hardwareMap.dcMotor.get("rotateOne");
        rotateTwo = hardwareMap.dcMotor.get("rotateTwo");

        jewel = new JewelsAndrew(this.hardwareMap, this.telemetry);
        jewel.reset();
        jewel.toogleSwing(false);

        vuMark = new VuMarkRecognition(this.hardwareMap, this.telemetry);


        telemetry.addData("Status", "Initialized");
        telemetry.update();
        clawBottom.setPosition(0);
        clawTop.setPosition(1);
        linkage.setPosition(.858);
        grabber.closeinst1();
        waitForStart();
        int goodCol = vuMark.getVuMark();

        grabber.rotateTwo(0.5); //start rotation

        jewel.toogleSwing(true);
        jewel.lowerArm();
        sleep(400);
        jewel.color.enableLed(true);
        jewel.AHEhitBallsVariablesForBlueVersionTwo( //FOR RED ACTUALLY
                jewel.isRedRight(),
                jewel.isBlueRight(),
                jewel.isRedLeft(),
                jewel.isBlueLeft()

        )
        ;grabber.rotateOne(0);
        jewel.reset();

        jewel.toogleSwing(false);
        sleep(500);
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
            drive.encoderMoveMRGyro2(90, rightColumnDistance, .5, 0.5);
        } else if (goodCol == 1) {
            drive.encoderMoveMRGyro2(90, leftColumnDistance, .5, 0.5);
        } else {
            drive.encoderMoveMRGyro2(90, centerColumnDistance, .5, 0.5);
        }
        sleep(400);

        drive.turn(-90, 2, .25, .1);

        drive.encoderMoveMRGyro2(90, .1, .3, 0.5);

        clawTop.setPosition(.33);
        clawBottom.setPosition(.5);

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
