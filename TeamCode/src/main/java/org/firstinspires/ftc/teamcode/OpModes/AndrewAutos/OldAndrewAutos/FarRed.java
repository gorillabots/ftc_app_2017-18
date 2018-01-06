package org.firstinspires.ftc.teamcode.OpModes.AndrewAutos.OldAndrewAutos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Components.GrabberJack;
import org.firstinspires.ftc.teamcode.Components.JewelsAndrew;
import org.firstinspires.ftc.teamcode.Drive.Drive;
import org.firstinspires.ftc.teamcode.Interfaces.Grabber;
import org.firstinspires.ftc.teamcode.Vision.VuMarkRecognition;

/**
 * Created by Jarred on 12/15/2017.
 */
@Autonomous(name="FarRed", group="AndrewBot")
public class FarRed extends LinearOpMode { final double ARM_RAISED = .22;
    final double ARM_LOWERED = .9;//.88

    Drive drive;
    JewelsAndrew jewel;
    VuMarkRecognition vuMark;
    GrabberJack grabber;
    boolean whereIsDatBall;
    @Override
    public void runOpMode()
    {
        drive = new Drive(this);
        jewel = new JewelsAndrew(this.hardwareMap,this.telemetry);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        jewel.reset();
        grabber = new GrabberJack(this.hardwareMap,this.telemetry);
        grabber.closeinst2();
        grabber.closeinst1();
         vuMark = new VuMarkRecognition(this.hardwareMap, this.telemetry);
        waitForStart();
        int goodCol = vuMark.getVuMark();
        //drive.encoderMoveMRGyro(270,.5,.5);
        jewel.lowerArm();
        sleep(500);
        jewel.color.enableLed(true);
        if(jewel.rightSensorIsBlue() || jewel.leftSensorIsRed()){
            whereIsDatBall = true;
        }


        jewel.hitBalls(
                jewel.leftSensorIsBlue(),
                jewel.leftSensorIsRed(),
                jewel.rightSensorIsBlue(),
                jewel.rightSensorIsRed()
        );

        sleep(500);
        telemetry.addData("status", "dunzo");
        telemetry.update();
        jewel.reset();
        sleep(500);
        if(jewel.moveLeft){
            drive.encoderMoveMRGyro(180,2,.5);
            drive.encoderMoveMRGyro(90,4.166,.8);
        }

    }
}
