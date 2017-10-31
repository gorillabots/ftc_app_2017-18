package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Components.ArbitraryDirectionDrive;
import org.firstinspires.ftc.teamcode.Components.TestArmExtender;
import org.firstinspires.ftc.teamcode.Components.TestGrabber;
import org.firstinspires.ftc.teamcode.Interfaces.ArmExtender;
import org.firstinspires.ftc.teamcode.Interfaces.Grabber;

/**
 * Created by Owner on 10/6/2017.
 */

public class TeleOp extends LinearOpMode{

    ArbitraryDirectionDrive driveTrain;
    ArmExtender armExtender;
    Grabber grabber;
    private LinearOpMode opMode;

    Servo claw;
    Servo spin;


     public void init_() {
        driveTrain = new ArbitraryDirectionDrive(this.opMode);
        armExtender = new TestArmExtender(hardwareMap, telemetry);
        grabber = new TestGrabber(hardwareMap, telemetry);

        claw = hardwareMap.servo.get("claw");
        spin = hardwareMap.servo.get("spin");
         

    }
        @Override
        public void runOpMode() throws InterruptedException {
            init_();
            while(opModeIsActive()) {
                double stickX = (gamepad1.left_stick_x);
                double stickY = (gamepad1.right_stick_y); // range between -1 to 1

                driveTrain.drive((int)Math.atan(stickY/stickX),Math.sqrt(Math.pow(stickX,2)+ Math.pow(stickY,2)));

                //Arm extention
                if (gamepad2.right_stick_y <= .7) {
                    armExtender.extend(50);
                } else if (gamepad2.right_stick_y >= .7) {
                    armExtender.extend(0);
                }
                if (gamepad2.right_stick_y <= -.7) {
                    armExtender.extend(-50);
                } else if (gamepad2.right_stick_y >= -.7) {
                    armExtender.extend(0);
                }

                //Graber
                if (gamepad2.right_bumper) {
                    grabber.open();
                }
                else if (gamepad2.left_bumper) {
                    grabber.close();}

                if (gamepad2.right_trigger > .9) {
                    grabber.rotate(+.25);
                } else if (gamepad2.left_trigger > .9) {
                    grabber.rotate(-.25);
                } else {
                    grabber.rotate(0);
                }

            }
    }
}
