package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Components.JewelsAndrew;

//import org.firstinspires.ftc.teamcode.Components.TestArmExtender;


/**
 * Created by Owner on 10/6/2017.
 */


@TeleOp(name="Servo Test", group="Backup")
public class TeleOpOp extends LinearOpMode{


    private LinearOpMode opMode;

    Servo arm;

    Servo clawTop;
    Servo clawBottom;
    Servo linkage;
    ColorSensor ballColor;
    JewelsAndrew jewel;


    public void init_() {


        // armExtender = new TestArmExtender(hardwareMap, telemetry);
        //grabber = new GrabberJack(hardwareMap, telemetry);
        clawBottom = hardwareMap.servo.get ("clawBottom");
        clawTop = hardwareMap.servo.get("clawTop");
        linkage = hardwareMap.servo.get ("linkage");

        arm = hardwareMap.servo.get("arm");

        ballColor = hardwareMap.colorSensor.get("ballColor");
        jewel = new JewelsAndrew(this.hardwareMap, this.telemetry);
        ballColor.enableLed(false);
        ballColor.enableLed(true);
        jewel.reset();
        jewel.toogleSwing(false);
    }
    @Override
    public void runOpMode() throws InterruptedException {
        init_();

        waitForStart();
        while(opModeIsActive()) {

            //arm.setPosition(gamepad1.left_trigger);
            if(gamepad1.a){
                clawBottom.setPosition(.5);
                clawTop.setPosition(.7);
            }
            if(gamepad1.b){
                clawTop.setPosition(.25);
            }
            if (gamepad1.y){
                clawTop.setPosition(.75);
            }
            if(gamepad1.x){
                clawTop.setPosition(1);
            }

            if(gamepad1.dpad_down){
                clawBottom.setPosition(0);//close?
                clawTop.setPosition(1);
            }
            if(gamepad1.dpad_up){

            }
            if(gamepad1.dpad_left){

            }

            telemetry.addData("arm", arm.getPosition());

            telemetry.addData("red", ballColor.red());
            telemetry.addData("blue", ballColor.blue());
            telemetry.update();
        }
    }
}
