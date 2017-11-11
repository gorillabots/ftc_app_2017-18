package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Components.ArbitraryDirectionDrive;
import org.firstinspires.ftc.teamcode.Components.Grabber1;
import org.firstinspires.ftc.teamcode.Components.Grabber2;
import org.firstinspires.ftc.teamcode.Components.ArmExtender1;
import org.firstinspires.ftc.teamcode.Interfaces.ArmExtender;

/**
 * Created by Owner on 10/6/2017.
 */
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="drive", group="Backup")
public class TeleOp extends LinearOpMode{

    ArbitraryDirectionDrive driveTrain;
    ArmExtender armExtender;
    Grabber1 grabber;
    private LinearOpMode opMode;

    Servo claw;
    Servo spin;


     public void init_() {

        driveTrain = new ArbitraryDirectionDrive(this.hardwareMap,this.telemetry);
       // armExtender = new TestArmExtender(hardwareMap, telemetry);
        //grabber = new Grabber1(hardwareMap, telemetry);




    }
        @Override
        public void runOpMode() throws InterruptedException {
            init_();

            waitForStart();
            while(opModeIsActive()) {


               driveTrain.driveCartesian(gamepad1.left_stick_x,gamepad1.left_stick_y,gamepad1.right_stick_x);


                //Graber
                if (gamepad2.right_bumper) {
                    grabber.open1();
                }
                else if (gamepad2.left_bumper) {
                    grabber.close1();}


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
