package org.firstinspires.ftc.teamcode.Components;

//Created by Mikko on 2017-12-19

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorMRRangeSensor;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Drive.ArbitraryDirectionDrive;

public class RangeCrypto
{
    LinearOpMode opMode;
    HardwareMap hardwareMap;
    Telemetry telemetry;

    ArbitraryDirectionDrive add;

    public ModernRoboticsI2cRangeSensor range;

    double offset;

    public RangeCrypto(LinearOpMode opMode, ArbitraryDirectionDrive add)
    {
        this.opMode = opMode;
        this.hardwareMap = opMode.hardwareMap;
        this.telemetry = opMode.telemetry;

        this.add = add;

        range = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "range");
    }

    public void updateOffset()
    {
        offset = range.cmUltrasonic();
    }

    final double PILLAR_ACTIVATION_DISTANCE = 7; //cm

    public void go(int numPillars)
    {
        int pillarsLeft = numPillars;
        boolean lastPollIsPillar = false;
        double criticalDistance = offset - PILLAR_ACTIVATION_DISTANCE;

        while(pillarsLeft > 0 && opMode.opModeIsActive())
        {
            add.drivePolar(.5, 180); //180 - right, 0 - left

            double dist = range.cmUltrasonic();

            telemetry.addData("Offset", offset);
            telemetry.addData("Pillars Left", pillarsLeft);
            telemetry.addData("Critical Point", criticalDistance);
            telemetry.addData("UltrasonicDistance", dist);
            telemetry.addData("LastPollIsPillar", lastPollIsPillar);

            if(dist <= criticalDistance)
            {
                //Pillar!
                telemetry.addData("Info", "UnderCrit");

                if(!lastPollIsPillar)
                {
                    pillarsLeft--;

                    telemetry.addData("Info", "Pillar");
                }

                lastPollIsPillar = true;
            }
            else
            {
                lastPollIsPillar = false;
            }

            telemetry.update();
        }

        add.stopMotors();
    }

    public void approach(int target, double speed)
    {
        while(range.cmUltrasonic() > target)
        {
            add.drivePolar2(speed, 90, .5);
        }

        add.stopMotors();
    }
}
