package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

//import org.firstinspires.ftc.teamcode.Components.TestArmExtender;


/**
 * Created by Owner on 10/6/2017.
 */

@Disabled
@TeleOp(name="Servo Test", group="Backup")
public class TeleOpOp extends LinearOpMode{


    private LinearOpMode opMode;

    Servo arm;
    Servo rotateArm;

    ColorSensor ballColor;


    public void init_() {


        // armExtender = new TestArmExtender(hardwareMap, telemetry);
        //grabber = new GrabberAndrew(hardwareMap, telemetry);


        arm = hardwareMap.servo.get("arm");
        rotateArm = hardwareMap.servo.get("rotateArm");
        ballColor = hardwareMap.colorSensor.get("ballColor");

        ballColor.enableLed(false);
        ballColor.enableLed(true);
    }
    @Override
    public void runOpMode() throws InterruptedException {
        init_();

        waitForStart();
        while(opModeIsActive()) {

            //arm.setPosition(gamepad1.left_trigger);
            if(gamepad1.a){
                arm.setPosition(0.08);
            }
            if(gamepad1.b){
                arm.setPosition(.77);
            }
            if (gamepad1.y){
                arm.setPosition(.6);
            }
            if(gamepad1.x){
                arm.setPosition(.82);
            }

            if(gamepad1.dpad_down){
                rotateArm.setPosition(0);

            }
            if(gamepad1.dpad_up){
                rotateArm.setPosition(1);
            }
            if(gamepad1.dpad_left){
                rotateArm.setPosition(.76);
            }

            telemetry.addData("arm", arm.getPosition());
            telemetry.addData("armRotate", rotateArm.getPosition());
            telemetry.addData("red", ballColor.red());
            telemetry.addData("blue", ballColor.blue());
            telemetry.update();
        }
    }
}
