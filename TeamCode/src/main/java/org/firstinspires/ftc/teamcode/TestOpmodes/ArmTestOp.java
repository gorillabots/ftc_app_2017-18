package org.firstinspires.ftc.teamcode.TestOpmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Components.Jewels;

/**
 * Created by mikko on 12/1/17.
 */

@TeleOp(name="ArmTestOp", group="Murica")
public class ArmTestOp extends OpMode
{
    boolean up = false;
    boolean down = false;
    boolean right = false;
    boolean left = false;

    Jewels jewels;

    int ud = 50;
    int lr = 50;

    public void init()
    {
        jewels = new Jewels(hardwareMap);
        jewels.color.enableLed(true);
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

        jewels.baseServo.setPosition(ud2);
        jewels.otherServo.setPosition(lr2);

        telemetry.addData("Arm", ud2);
        telemetry.addData("Rotate", lr2);
        telemetry.addData("R", jewels.color.red());
        telemetry.addData("G", jewels.color.green());
        telemetry.addData("B", jewels.color.blue());
        telemetry.update();

        up = gamepad1.dpad_up;
        down = gamepad1.dpad_down;
        left = gamepad1.dpad_left;
        right = gamepad1.dpad_right;
    }
}
