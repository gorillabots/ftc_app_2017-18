package org.firstinspires.ftc.teamcode.Vision;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by mikko on 11/22/17.
 */

@TeleOp(group="Test", name="VuMarkTestOp")
public class VuMarkTestOp extends OpMode
{
    VuMarkRecognition vuMarks;

    @Override
    public void init()
    {
        vuMarks = new VuMarkRecognition(this.hardwareMap, this.telemetry);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    @Override
    public void loop()
    {
        int vm = vuMarks.getVuMark();
        telemetry.addData("VM", vm);
        telemetry.update();
    }
}
