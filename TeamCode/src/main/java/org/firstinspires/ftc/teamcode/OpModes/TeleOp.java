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
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="drive", group="Backup")
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



    }
        @Override
        public void runOpMode() throws InterruptedException {
            init_();
            while(opModeIsActive()) {
                double stickX = (gamepad1.left_stick_x);
                double stickY = (gamepad1.right_stick_y); // range between -1 to 1

               driveTrain.oneStickLoop(gamepad1.right_stick_x,gamepad1.right_stick_y,gamepad1.left_stick_x);

                /*
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
                if (gamepad2.x) {
                    grabber.open(90, 005);
                } else if (gamepad2.x) {
                    grabber.open(0, 005);
                }
                if (gamepad2.y) {
                    claw.close();
                } else if (gamepad2.y) {
                    claw.close();
                }
                if (gamepad2.a) {
                    grabber.rotate(90, 005);
                } else if (gamepad2.b) {
                    grabber.rotate(-90, 005);
                }
                */

            }
    }
}
