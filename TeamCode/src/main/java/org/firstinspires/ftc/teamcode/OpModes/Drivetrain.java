package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Jarred on 11/4/2017.
 */
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="driveOpOp", group="Backup")
public class Drivetrain extends OpMode {

    DcMotor m1, m2, m3, m4;

    public void init() {

        m1 = hardwareMap.dcMotor.get("m1");
        m2 = hardwareMap.dcMotor.get("m2");
        m3 = hardwareMap.dcMotor.get("m3");
        m4 = hardwareMap.dcMotor.get("m4");
    }

    public void loop() {

        /*
        double r = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
        double robotAngle = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
        double rightX = gamepad1.right_stick_x;
        final double v1 = r * Math.cos(robotAngle) + rightX;
        final double v2 = r * Math.sin(robotAngle) - rightX;
        final double v3 = r * Math.sin(robotAngle) + rightX;
        final double v4 = r * Math.cos(robotAngle) - rightX;

        m1.setPower(v1);
        m2.setPower(v2);
        m4.setPower(v4);
        m3.setPower(v3);
        */
        if(gamepad1.a){
            m1.setPower(.25);
            m2.setPower(+.25);
            m3.setPower(-.25);
            m4.setPower(-.25);
        }
        else{
            m1.setPower(0);
            m2.setPower(0);
            m3.setPower(0);
            m4.setPower(0);
        }
    }
}
