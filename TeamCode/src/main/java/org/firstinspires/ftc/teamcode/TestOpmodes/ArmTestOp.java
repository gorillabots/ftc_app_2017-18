package org.firstinspires.ftc.teamcode.TestOpmodes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Components.JewelsJack;

/**
 * Created by mikko on 12/1/17.
 */
@Disabled
@TeleOp(name="ArmTestOp", group="Murica")
public class ArmTestOp extends OpMode
{
    boolean up = false;
    boolean down = false;
    boolean right = false;
    boolean left = false;

    JewelsJack jewelsJack;

    int ud = 50;
    int lr = 50;

    public void init()
    {
        jewelsJack = new JewelsJack(hardwareMap);
        jewelsJack.color.enableLed(true);
    }

    public void loop()
    {
        if(!up && gamepad1.dpad_up)
        {
            ud--;
        }

        if(!down && gamepad1.dpad_down)
        {
            ud++;
        }

        if(!left && gamepad1.dpad_left)
        {
            lr++;
        }

        if(!right && gamepad1.dpad_right)
        {
            lr--;
        }

        double ud2 = ud / 100d;
        double lr2 = lr / 100d;

        jewelsJack.baseServo.setPosition(ud2);
        jewelsJack.otherServo.setPosition(lr2);

        telemetry.addData("Arm", ud2);
        telemetry.addData("Rotate", lr2);
        telemetry.addData("R", jewelsJack.color.red());
        telemetry.addData("G", jewelsJack.color.green());
        telemetry.addData("B", jewelsJack.color.blue());
        telemetry.update();

        up = gamepad1.dpad_up;
        down = gamepad1.dpad_down;
        left = gamepad1.dpad_left;
        right = gamepad1.dpad_right;
    }
}
