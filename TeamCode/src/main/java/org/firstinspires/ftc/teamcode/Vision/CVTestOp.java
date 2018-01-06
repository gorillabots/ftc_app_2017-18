package org.firstinspires.ftc.teamcode.Vision;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.opencv.core.Core;

//Created by Mikko on 2017-10-27
@Disabled
@TeleOp(group="Test", name="CVTestOp2")
public class CVTestOp extends OpMode
{
    @Override
    public void init()
    {
        String cvVersion = Core.VERSION;
        telemetry.addData("OpenCV Version", cvVersion);
    }

    @Override
    public void loop()
    {

    }
}
