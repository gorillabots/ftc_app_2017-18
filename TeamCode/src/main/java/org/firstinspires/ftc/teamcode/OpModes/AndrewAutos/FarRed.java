package org.firstinspires.ftc.teamcode.OpModes.AndrewAutos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Components.GrabberJack;
import org.firstinspires.ftc.teamcode.Components.JewelsAndrew;
import org.firstinspires.ftc.teamcode.Components.RangeCrypto;
import org.firstinspires.ftc.teamcode.Drive.Drive;
import org.firstinspires.ftc.teamcode.Vision.VuMarkRecognition;

/**
 * Created by Andy on 12/15/2017.
 */
@Autonomous(name = "farRedAndy", group = "AndrewBot")
public class FarRed extends LinearOpMode {
    final double ARM_RAISED = .22;
    final double ARM_LOWERED = .9;//.88

    Drive drive;
    DcMotor m1;DcMotor m2;DcMotor m3;DcMotor m4;
    JewelsAndrew jewel;
    VuMarkRecognition vuMark;
    GrabberJack grabber;
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
        jewel.toggleSwing(false);
        vuMark = new VuMarkRecognition(this.hardwareMap, this.telemetry);


        grabber = new GrabberJack(this.hardwareMap, this.telemetry);
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
        jewel.toggleSwing(true);
        jewel.lowerArm();
        sleep(500);
        jewel.color.enableLed(true);

        telemetry.addData("blue left", jewel.leftSensorIsBlue());
        telemetry.addData("red left", jewel.leftSensorIsRed());
        telemetry.addData("blue right", jewel.rightSensorIsBlue());
        telemetry.addData("red right", jewel.rightSensorIsRed());
        telemetry.addData("col", goodCol);
        telemetry.update();
        //jewel.hitBalls(jewel.leftSensorIsRed(), jewel.rightSensorIsBlue());
        //jewel.AHEhitBallsVariablesForBlue(jewel.leftSensorIsRed(),jewel.leftSensorIsBlue(),jewel.rightSensorIsRed(),jewel.rightSensorIsBlue());
        jewel.hitBalls(
                jewel.leftSensorIsBlue(),
                jewel.leftSensorIsRed(),
                jewel.rightSensorIsBlue(),
                jewel.rightSensorIsRed()
        );

        telemetry.addData("status", "dunzo");
        telemetry.update();
        jewel.reset();
        jewel.toggleSwing(false);
        sleep(2300);
        grabber.rotateTwo(0);
        runtime.reset();


        telemetry.addData("zone mabob", goodCol);
        telemetry.update();
        //---------------------------------jewel↑↑↑

        //------↓ post platform aligning ↓---------------
        drive.encoderMoveMRGyro2(270, .7, .3, 0.5);
        //↑ go forward
        drive.encoderMoveMRGyro2(90, .2, .3, 0.5);
        //↑ align via platform
        drive.encoderMoveMRGyro2(270, .2, .3, 0.5);
        //↑ slightly fowrward
        //------↑ post platform aligning ↑-------
        drive.turn(-90,1,.4,.1);

        drive.driveTrain.m1.setPower(.3);
        drive.driveTrain.m2.setPower(-.3);
        drive.driveTrain.m3.setPower(-.3);
        drive.driveTrain.m4.setPower(.3);
        sleep (2000);
        drive.driveTrain.stopMotors();
        sleep (100);
        //------↓ align to the correct column ↓---
        double finishTime;
        if(goodCol == 3)
        {
            telemetry.addData("Going for", "R");
            //telemetry.update();
            //drive.encoderMoveMRGyro2(90, .5, .6, 0.5);
            finishTime=drive.encoderMoveMRGyro3(90, .45, .6, 0.5);
            telemetry.addData("Finish time", finishTime);
            telemetry.update();
            drive.turn (-90,1,.4,.1);
            sleep (400);
        }
        else if (goodCol == 1)
        {
            telemetry.addData("Going for", "L");
            finishTime=drive.encoderMoveMRGyro3(90, .95, .6, 0.5);
            telemetry.addData("Finish time", finishTime);
            telemetry.update();
            drive.turn (-90,1,.4,.1);
            sleep (400);
        }
        else
        {
            telemetry.addData("Going for", "C");
            finishTime=drive.encoderMoveMRGyro3(90, .70, .6, 0.5);
            telemetry.addData("Finish time", finishTime);
            telemetry.update();
            drive.turn (-90,1,.4,.1);
            sleep (400);
        }
        //------↑ align to the correct column ↑---

        //------↓ dropping and pushing in glyph ↓--- (not done)
        telemetry.addData("Step", "A");
        telemetry.update();
        drive.encoderMoveMRGyro2(90, .2, .3, 0.5);
        sleep (400);
        grabber.openinst1();
        grabber.openinst2();
        drive.encoderMoveMRGyro2(270, .3,.3,.5);
        sleep (400);
        drive.turn(180,1,.3,.1);
        sleep (400);
        drive.driveTrain.stopMotors();

        drive.driveTrain.m1.setPower(.3);
        drive.driveTrain.m2.setPower(-.3);
        drive.driveTrain.m3.setPower(-.3);
        drive.driveTrain.m4.setPower(.3);
        sleep (2000);
        drive.driveTrain.stopMotors();

        drive.encoderMoveMRGyro2(90,.2,.3,.5);
    }
}
