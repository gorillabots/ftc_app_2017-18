package org.firstinspires.ftc.teamcode.TestOpmodes;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by Owner on 11/10/2017.
 */
@Disabled
public class GyroTest extends OpMode {
    ModernRoboticsI2cGyro modernRoboticsI2cGyro;

    @Override
    public void init() {
        modernRoboticsI2cGyro = hardwareMap.get(ModernRoboticsI2cGyro.class, "gyro");

    }

    @Override
    public void loop() {
        telemetry.addData("Angular Orientation",modernRoboticsI2cGyro.getAngularOrientationAxes());
        telemetry.addData("Heading Mode",modernRoboticsI2cGyro.getHeadingMode());
        telemetry.addData("Heading",modernRoboticsI2cGyro.getHeading());
        telemetry.addData("Z Ax Offset",modernRoboticsI2cGyro.getZAxisOffset());
        telemetry.addData("Z Ax Scaling Coefficient",modernRoboticsI2cGyro.getZAxisScalingCoefficient());
        telemetry.addData("Angular Orientation Ax",modernRoboticsI2cGyro.getAngularOrientationAxes());
    }
}
