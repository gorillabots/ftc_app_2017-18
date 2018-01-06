package org.firstinspires.ftc.teamcode.OpModes.AndyTests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Components.GrabberJack;
import org.firstinspires.ftc.teamcode.Components.JewelsAndrew;
import org.firstinspires.ftc.teamcode.Components.RangeCrypto;
import org.firstinspires.ftc.teamcode.Drive.Drive;
import org.firstinspires.ftc.teamcode.Vision.VuMarkRecognition;

/**
 * Created by xiax on 12/29/2017.
 */
@Disabled
@Autonomous(name = "Lima", group = "AAA")
public class L extends LinearOpMode {
    final double ARM_RAISED = .22;
    final double ARM_LOWERED = .9;//.88

    Drive drive;

    JewelsAndrew jewel;
    VuMarkRecognition vuMark;
    GrabberJack grabber;
    RangeCrypto rangeCrypto;
    DcMotor rotateOne;
    DcMotor rotateTwo;
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {

        drive = new Drive(this.hardwareMap, this.telemetry);
        rotateOne = hardwareMap.dcMotor.get("rotateOne");
        rotateTwo = hardwareMap.dcMotor.get("rotateTwo");
        jewel = new JewelsAndrew(this.hardwareMap, this.telemetry);
        jewel.reset();
        jewel.toggleSwing(false);

        grabber = new GrabberJack(this.hardwareMap, this.telemetry);
        grabber.closeinst2();
        grabber.closeinst1();

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        drive.encoderMoveMRGyro2(90, .1, .3, 0.5);
        telemetry.addData("Status", "A");
        telemetry.update();

        drive.encoderMoveMRGyro2(90, .14, .3, 0.5);
        telemetry.addData("Status", "B");
        telemetry.update();

        drive.encoderMoveMRGyro2(90, .18, .3, 0.5);
        telemetry.addData("Status", "C");
        telemetry.update();

        drive.encoderMoveMRGyro2(90, .22, .3, 0.5);
        telemetry.addData("Status", "D");
        telemetry.update();

        drive.encoderMoveMRGyro2(90, .26, .3, 0.5);
        telemetry.addData("Status", "E");
        telemetry.update();

        drive.encoderMoveMRGyro2(270,.5, .3, 0.5);
        telemetry.addData("Status", "REVERSE");
        telemetry.update();
        sleep (2000);
    }
}
