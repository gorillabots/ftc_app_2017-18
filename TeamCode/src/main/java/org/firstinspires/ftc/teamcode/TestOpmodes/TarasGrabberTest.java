package org.firstinspires.ftc.teamcode.TestOpmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Components.GrabberTaras;

/*
 * Created by mikko on 2/4/18.
 */

@TeleOp(name = "TarasGrabberTest", group = "Tests")
public class TarasGrabberTest extends LinearOpMode {
    GrabberTaras grabber;

    @Override
    public void runOpMode() {
        grabber = new GrabberTaras(hardwareMap);
        int bottomEncoder = grabber.raise.getCurrentPosition();

        waitForStart();

        while (opModeIsActive()) {
            grabber.raise(gamepad1.left_stick_y);

            if (gamepad1.left_bumper) {
                grabber.upperClose();
            }

            if (gamepad1.right_bumper) {
                grabber.lowerClose();
            }

            if (gamepad1.left_trigger > .5) {
                grabber.upperOpen();
            }

            if (gamepad1.right_trigger > .5) {
                grabber.lowerOpen();
            }
            if (gamepad1.x) {
                grabber.upperOpenWide();
            }
            if (gamepad1.b) {
                grabber.lowerOpenWide();
            }
            telemetry.addData("Encoder", grabber.raise.getCurrentPosition() - bottomEncoder);

            sleep(20);
        }

        grabber.raise(0);
    }
}
