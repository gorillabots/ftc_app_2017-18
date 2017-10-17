package org.firstinspires.ftc.teamcode.TestOpmodes;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Jarred on 10/13/2017.
 */
@TeleOp (name="Blue Beacons Shoot Utica", group="Backup")
public class RangeDebug extends OpMode {

    ModernRoboticsI2cRangeSensor debug;

    @Override
    public void init(){
        debug = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "range");

    }
    @Override
    public void loop(){
        telemetry.addData("distance", debug.cmUltrasonic());
        telemetry.update();
    }

}
