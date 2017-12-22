package org.firstinspires.ftc.teamcode.Components;

//Created by Mikko on 2017-12-19

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorMRRangeSensor;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Drive.ArbitraryDirectionDrive;

public class RangeCrypto
{
    HardwareMap hardwareMap;
    Telemetry telemetry;

    ArbitraryDirectionDrive add;

    ModernRoboticsI2cRangeSensor range;

    double offset;

    public RangeCrypto(HardwareMap hardwareMap, Telemetry telemetry, ArbitraryDirectionDrive add)
    {
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;

        this.add = add;

        range = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "range");
    }

    public void updateOffset()
    {
        offset = range.cmUltrasonic();
    }

    final int PILLAR_ACTIVATION_DISTANCE = 7; //cm

    public void go(int numPillars)
    {
        int pillarsLeft = numPillars;
        boolean lastPollIsPillar = false;
        double criticalDistance = offset - PILLAR_ACTIVATION_DISTANCE;

        telemetry.addData("Offset", offset);
        telemetry.addData("Pillars Left", pillarsLeft);
        telemetry.update();

        while(pillarsLeft > 0)
        {
            add.drivePolar(.5, 180);

            if(range.cmUltrasonic() <= criticalDistance)
            {
                //Pillar!

                if(!lastPollIsPillar)
                {
                    pillarsLeft--;

                    telemetry.addData("Offset", offset);
                    telemetry.addData("Pillars Left", pillarsLeft);
                    telemetry.update();
                }

                lastPollIsPillar = true;
            }
            else
            {
                lastPollIsPillar = false;
            }
        }

        add.stopMotors();
    }
}
