package org.firstinspires.ftc.teamcode.OpModes.AndyTests;

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
@Autonomous(name = "KnockTheJewelOffOnRedSide", group = "AAA")
public class jewelTestRed extends LinearOpMode {
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
        drive = new Drive(this);
        rotateOne = hardwareMap.dcMotor.get("rotateOne");
        rotateTwo = hardwareMap.dcMotor.get("rotateTwo");
        jewel = new JewelsAndrew(this.hardwareMap, this.telemetry);
        jewel.reset();
        jewel.toogleSwing(false);
        grabber = new GrabberAndrew(this);


        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        jewel.toogleSwing(true);
        jewel.lowerArm();
        sleep(500);
        jewel.color.enableLed(true);

        telemetry.addData("isBlueLeft", jewel.isBlueLeft());
        telemetry.addData("isRedLeft", jewel.isRedLeft());
        telemetry.addData("isBlueRight", jewel.isBlueRight());
        telemetry.addData("isRedRight", jewel.isRedRight());
        telemetry.update();
        sleep(5000);
        jewel.AHEhitBallsVariablesForBlueVersionTwo( //FOR RED ACTUALLY
                jewel.isRedRight(),
                jewel.isBlueRight(),
                jewel.isRedLeft(),
                jewel.isBlueLeft()

        )
        ;
        sleep(500);
        jewel.reset();
        jewel.toogleSwing(false);
        sleep(500);
    }
}
