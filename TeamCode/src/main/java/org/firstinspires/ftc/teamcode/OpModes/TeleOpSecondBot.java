package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Components.ArbitraryDirectionDrive;
import org.firstinspires.ftc.teamcode.Components.Constants;
//import org.firstinspires.ftc.teamcode.Components.TestArmExtender;
import org.firstinspires.ftc.teamcode.Interfaces.ArmExtender;

/**
 * Created by Owner on 10/6/2017.
 */
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="driveSecond", group="Backup")
public class TeleOpSecondBot extends LinearOpMode{

    ArbitraryDirectionDrive driveTrain;
    //ArmExtender armExtender;
    //Grabber grabber;
    private LinearOpMode opMode;

    Servo clawOne;
    Servo clawTwo;
    ModernRoboticsI2cColorSensor ballColor;
    DcMotor extend;
    DcMotor rotateOne;
    DcMotor rotateTwo;

    double oneOpen = Constants.leftOpen;
    double oneClose = Constants.leftClose;
    double twoOpen = Constants.rightOpen;
    double twoClose = Constants.rightClose;

    public void init_() {

        driveTrain = new ArbitraryDirectionDrive(this.hardwareMap,this.telemetry);
        // armExtender = new TestArmExtender(hardwareMap, telemetry);
        //grabber = new Grabber1(hardwareMap, telemetry);
        extend = hardwareMap.dcMotor.get("extend");
        rotateOne = hardwareMap.dcMotor.get("rotateOne");
        rotateTwo = hardwareMap.dcMotor.get("rotateTwo");

        clawOne = hardwareMap.servo.get("clawOne");
        clawTwo = hardwareMap.servo.get("clawTwo");

        clawOne.setPosition(oneOpen);
        clawTwo.setPosition(twoOpen);

    }
    @Override
    public void runOpMode() throws InterruptedException {
        init_();

        waitForStart();
        while(opModeIsActive()) {


            driveTrain.driveCartesian(gamepad1.left_stick_x,gamepad1.left_stick_y,gamepad1.right_stick_x);

            if(gamepad2.left_bumper){
                clawOne.setPosition(oneClose);
            }

            else if(gamepad2.left_trigger >= .5){
                clawOne.setPosition(oneOpen);
            }





            if(gamepad2.right_bumper){
                clawTwo.setPosition(twoClose);
            }
            else if(gamepad2.right_trigger >= .5){
                clawTwo.setPosition(twoOpen);
            }

            if(gamepad2.dpad_up){
                extend.setPower(1);
            }
            else if(gamepad2.dpad_down){
                extend.setPower(-1);
            }
            else{
                extend.setPower(0);
            }

            rotateOne.setPower(gamepad2.left_stick_y*.75);
            rotateTwo.setPower(gamepad2.right_stick_y*.75);


                /*
                if (gamepad2.right_trigger > .9) {
                    grabber.rotate(+.25);
                } else if (gamepad2.left_trigger > .9) {
                    grabber.rotate(-.25);
                } else {
                    grabber.rotate(0);
                }

                */

        }
    }
}
