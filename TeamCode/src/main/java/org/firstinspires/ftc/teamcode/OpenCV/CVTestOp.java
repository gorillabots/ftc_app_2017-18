package org.firstinspires.ftc.teamcode.OpenCV;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.opencv.core.Core;
import org.opencv.core.Mat;

//Created by Mikko on 2017-10-27

@TeleOp(group="Test", name="CVTestOp")
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
