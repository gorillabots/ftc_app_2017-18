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
@Autonomous(name = "closeRedAndy", group = "AndrewBot")
public class Jan4CloseRed extends LinearOpMode {
    final double ARM_RAISED = .22;
    final double ARM_LOWERED = .9;//.88

    final double leftColumnDistance = .5;
    final double centerColumnDistance = .25;
    final double rightColumnDistance = 0;

    Drive drive;
    DcMotor m1;DcMotor m2;DcMotor m3;DcMotor m4;
    JewelsAndrew jewel;
    VuMarkRecognition vuMark;
    GrabberAndrew grabber;
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
        jewel.toogleSwing(false);
        vuMark = new VuMarkRecognition(this.hardwareMap, this.telemetry);
        m1 = hardwareMap.dcMotor.get("m1");
        m2 = hardwareMap.dcMotor.get("m2");
        m3 = hardwareMap.dcMotor.get("m3");
        m4 = hardwareMap.dcMotor.get("m4");


        grabber = new GrabberAndrew(this.hardwareMap, this.telemetry);
        grabber.closeinst2();
        grabber.closeinst1();

        rangeCrypto = new RangeCrypto(this, drive.driveTrain);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        int goodCol = vuMark.getVuMark();
        runtime.reset();
        while(runtime.seconds()<0.00001){
            grabber.rotateTwo(0.2);
        }
        //-------------------------------------------jewel↓↓↓↓
        jewel.toogleSwing(true);
        jewel.lowerArm();
        sleep(500);
        jewel.color.enableLed(true);

        telemetry.addData("blue left", jewel.isBlueLeft());
        telemetry.addData("red left", jewel.isRedLeft());
        telemetry.addData("blue right", jewel.isBlueRight());
        telemetry.addData("red right", jewel.isRedRight());
        telemetry.addData("col", goodCol);
        telemetry.update();
        sleep(500);
        //jewel.hitBalls(jewel.isRedLeft(), jewel.isBlueRight());
        //jewel.AHEhitBallsVariablesForBlue(jewel.isRedLeft(),jewel.isBlueLeft(),jewel.isRedRight(),jewel.isBlueRight());
        jewel.AHEhitBallsVariablesForBlueVersionTwo(
                jewel.isBlueLeft(),
                jewel.isRedLeft(),
                jewel.isBlueRight(),
                jewel.isRedRight()
        )
        ;
        sleep(500);
        telemetry.addData("status", "dunzo");
        telemetry.update();
        jewel.reset();
        jewel.toogleSwing(false);
        sleep(2000);
        grabber.rotateTwo(0);
        runtime.reset();


        telemetry.addData("zone mabob", goodCol);
        telemetry.update();
        //---------------------------------jewel↑↑↑

        drive.encoderMoveMRGyro2(270, 1, .3, 0.5);


        //↓ needs testing if we want to score glyph


        drive.driveTrain.m1.setPower(-.2);
        drive.driveTrain.m2.setPower(.2);
        drive.driveTrain.m3.setPower(.2);
        drive.driveTrain.m4.setPower(-.2);
        sleep (1300);

        drive.driveTrain.stopMotors();
        sleep (500);

        if (goodCol == 1)
        {
            drive.encoderMoveMRGyro2(270, .6, .3, .5);
        }
        else if (goodCol == 3)
        {
           // drive.encoderMoveMRGyro2(270,0, .3, .5); do nothing
        }
        else
        {
            drive.encoderMoveMRGyro2(270, .27, .3, .5);
        }

        sleep (500);
        drive.turn(90,2,.5,.1);

        drive.encoderMoveMRGyro2(90,.3, .3, .5);

        grabber.openinst1();
        grabber.openinst2();

        drive.encoderMoveMRGyro2(270 , .4, .3, .5);

        drive.turn(180,2,.3,.2);
        sleep (500);
        stopMotors();
        m1.setPower(.3);
        m2.setPower(-.3);
        m3.setPower(-.3);
        m4.setPower(.3);
        sleep(1500);
        stopMotors();
        sleep (500);

        drive.encoderMoveMRGyro2(90,.2,.3,.2);

    }
    public void stopMotors() {
        m1.setPower(0);
        m2.setPower(0);
        m3.setPower(0);
        m4.setPower(0);
    }


}
