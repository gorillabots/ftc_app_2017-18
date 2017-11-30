package org.firstinspires.ftc.teamcode.Components;

/**
 * Created by Jarred on 10/22/2017.
 */

import com.kauailabs.navx.ftc.AHRS;
import com.kauailabs.navx.ftc.navXPIDController;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.sun.tools.javac.util.List;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.internal.android.dx.rop.cst.CstArray;
import org.firstinspires.ftc.teamcode.Components.Constants;

import java.util.concurrent.Callable;


public class Drive {


    private ArbitraryDirectionDrive driveTrain;

    //Motors
    private DcMotor frontRight, backRight, frontLeft, backLeft;

    //Sensors
    //private ModernRoboticsI2cGyro gyro;
    private TouchSensor wallTouch;

    //Navx gyro constants
    private final int NAVX_DIM_I2C_PORT = 5;
    private final byte NAVX_DEVICE_UPDATE_RATE_HZ = 50;
    private int NAVX_TIMEOUT_MS = 5000;

    //Navx stuff
    private AHRS navx;
    private navXPIDController pidController;
    private navXPIDController.PIDResult pidResult;

    //Navx Constants
    private final double TOLERANCE_DEGREES = 2.0;
    private final double MIN_MOTOR_OUTPUT_VALUE = -1.0;
    private final double MAX_MOTOR_OUTPUT_VALUE = 1.0;
    private final double YAW_PID_P = 0.005;
    private final double YAW_PID_I = 0.0;
    private final double YAW_PID_D = 0.0;

    private double offset;
    private double offsetConverted;
    LinearOpMode linOp;


    public Drive(LinearOpMode opMode) {
        linOp = opMode;
        this.init(0);

    }

    public double getMag(double x, double y) {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public double getDir(double y, double x) {
        return Math.atan(y / x);
    }

    public void init(double offset) //Get hardware from hardwareMap
    {


        driveTrain = new ArbitraryDirectionDrive(linOp.hardwareMap, linOp.telemetry);
        //Sensors
        /*
        navx = AHRS.getInstance(linOp.hardwareMap.deviceInterfaceModule.get("gyrobox"),
                NAVX_DIM_I2C_PORT,
                AHRS.DeviceDataType.kProcessedData,
                NAVX_DEVICE_UPDATE_RATE_HZ);

        while (navx.isCalibrating()) {
            linOp.sleep(5);
        }

        pidController = new navXPIDController(navx, navXPIDController.navXTimestampedDataSource.YAW);
        pidController.setContinuous(true);
        pidController.setOutputRange(MIN_MOTOR_OUTPUT_VALUE, MAX_MOTOR_OUTPUT_VALUE);
        pidController.setTolerance(navXPIDController.ToleranceType.ABSOLUTE, TOLERANCE_DEGREES);
        pidController.setPID(YAW_PID_P, YAW_PID_I, YAW_PID_D);
        //pidController.enable(true);

        pidResult = new navXPIDController.PIDResult();
        */
        //wallTouch = linOp.hardwareMap.touchSensor.get("wallTouch");

        this.offset = offset;
        offsetConverted = convertHeading(offset);
    }

    public void stop() {
        pidController.close();
        navx.close();

    }

    void resetPid() {
        pidController.reset();
        pidController.setSetpoint(offsetConverted);
        pidController.enable(true);
    }

    public void resetNavX() //Define the current heading as 0 degrees
    {
        navx.zeroYaw();
    }

    public void resetGyro(){
        driveTrain.gyro.resetZAxisIntegrator();
    }
    public void updateOffset(double offset) {
        this.offset = offset;
        offsetConverted = convertHeading(offset);
    }


    public void encoderMoveNAVX(int angle, double distance, double power) //Move forwards by distance
    {
        resetPid();

        double pidOutput;

        try {
            while (driveTrain.distanceCheck(distance) && linOp.opModeIsActive()) {
                if (pidController.waitForNewUpdate(pidResult, NAVX_TIMEOUT_MS)) {
                    pidOutput = 0;

                    if (!pidController.isOnTarget()) {
                        pidOutput = pidResult.getOutput();
                    }

                    // driveTrain.drive(angle, power);

                    linOp.telemetry.addData("Status", "Encoder movement");
                    linOp.telemetry.update();

                    linOp.sleep(5);

                }
            }

            driveTrain.stopMotors();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }



    public void encoderMoveMRGyro(double angle, double distance, double power) //Move forwards by distance
    {
        //resetPid();

        double pidOutput;

        while (driveTrain.distanceCheck(distance) && linOp.opModeIsActive()) {
            driveTrain.drivePolar(power, angle);

            //linOp.telemetry.addData("Status", "Encoder movement");
            //linOp.telemetry.update();

            linOp.sleep(5);

        }


        driveTrain.stopMotors();

    }

    public void lineMove(ColorSensor floorColor, double power, int angle) //Move forwards to white line
    {
        //pidController.reset();
        //pidController.setSetpoint(offsetConverted);
        //pidController.enable(true);

        double pidOutput;

        linOp.telemetry.addData("Status", "Moving to line");
        linOp.telemetry.update();

        //try
        //{
        while (!ColorHelper.isFloorWhiteTest(floorColor) && linOp.opModeIsActive()) {
            //boolean pidUpdated = pidController.waitForNewUpdate(pidResult, NAVX_TIMEOUT_MS);

            //telemetry.addData("PID Updated", pidUpdated);
            //telemetry.update();

            pidOutput = 0;

            //if(pidUpdated && !pidController.isOnTarget())
            //{
            //    pidOutput = pidResult.getOutput();
            //}

            //driveTrain.drive(angle, power);

            linOp.telemetry.addData("Status", "ForwardsToLine");
            linOp.telemetry.addData("Heading", navx.getYaw());
            linOp.telemetry.addData("Target", offsetConverted);
            //telemetry.addData("PID Output", pidOutput);
            //telemetry.addData("PID Updated", pidUpdated);
            linOp.telemetry.addData("R", floorColor.red());
            linOp.telemetry.addData("G", floorColor.green());
            linOp.telemetry.addData("B", floorColor.blue());
            linOp.telemetry.update();

            linOp.sleep(5);
        }

        driveTrain.stopMotors();
        //}
        //catch(InterruptedException e)
        //{
        //    e.printStackTrace();
        //}
    }


    public void turn(double target, double accuracy, double speed) {
        // With target = -135....

        double absoluteTarget = convertHeading(offset + target);
        // absoluteTarget = 45 | Should be -135

        pidController.reset();
        pidController.setSetpoint(absoluteTarget);
        pidController.setTolerance(navXPIDController.ToleranceType.ABSOLUTE, accuracy);
        pidController.enable(true);

        linOp.telemetry.addData("spot", 1);
        linOp.telemetry.update();

        try {
            while (!pidResult.isOnTarget() && linOp.opModeIsActive()) {
                if (pidController.waitForNewUpdate(pidResult, 500)) {
                    double power = pidResult.getOutput();

                    linOp.telemetry.addData("Status", "Turn");
                    linOp.telemetry.addData("Offset", offset);
                    linOp.telemetry.addData("Heading", navx.getYaw());
                    linOp.telemetry.addData("Target", target);
                    linOp.telemetry.addData("Absolute target", absoluteTarget);
                    linOp.telemetry.addData("Defined Speed", speed);
                    linOp.telemetry.addData("PID Speed", power);

                    linOp.telemetry.update();

                    if (power > -.15 && power < .15) {
                        if (Math.abs(power) < .05) {
                            break;
                        }
                        if (power > 0 && power < .15) {
                            power = .17;
                        }
                        if (power < 0 && power > -.15) {
                            power = -.17;
                        }
                    }

                    driveTrain.m1.setPower(power * speed);
                    driveTrain.m2.setPower(power * speed);
                    driveTrain.m3.setPower(power * speed);
                    driveTrain.m4.setPower(power * speed);

                    linOp.sleep(5);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driveTrain.stopMotors();

        offset += target;
        offsetConverted = convertHeading(offset);
    }

    public void goToDistance(ModernRoboticsI2cRangeSensor range, double target, double accuracy, double power, int angle) //Go to distance using range sensor
    {
        pidController.reset();
        pidController.setSetpoint(offsetConverted);
        pidController.enable(true);

        double distance = range.cmUltrasonic();

        double pidOutput;

        try {
            while (!inRange(target, accuracy, distance) && linOp.opModeIsActive()) {
                if (pidController.waitForNewUpdate(pidResult, NAVX_TIMEOUT_MS)) {
                    pidOutput = 0;

                    if (!pidController.isOnTarget()) {
                        pidOutput = pidResult.getOutput();
                    }

                    linOp.telemetry.addData("Status", "GoToDistance");
                    linOp.telemetry.addData("Distance", distance);
                    linOp.telemetry.addData("Target", target);

                    if (distance > target) //Too far, move right
                    {
                        //driveTrain.drive(angle, power);
                        ;
                        backLeft.setPower(+power + pidOutput);

                        linOp.telemetry.addData("until distnace", "forward");
                    } else //Too close, move left
                    {
                        // driveTrain.drive(angle, -power);

                        linOp.telemetry.addData("until distance", "reverse");
                    }

                    linOp.telemetry.update();

                    linOp.sleep(5);

                    distance = range.cmUltrasonic();
                }
            }

            frontRight.setPower(0);
            backRight.setPower(0);
            frontLeft.setPower(0);
            backLeft.setPower(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private double getRotation() //Get rotation
    {
        return (driveTrain.m1.getCurrentPosition() + driveTrain.m2.getCurrentPosition() +
                driveTrain.m3.getCurrentPosition() + driveTrain.m4.getCurrentPosition()) / 4;
    }

    /*
     * TODO: This is the problem with BlueCenterDisruptive
     * When input value = -135 this returns 45, should actually return -135
    */
    private double convertHeading(double in) //0-360
    {
        while (in < 0) //Make sure in is positive
        {
            in += 360;
        }

        in %= 360; //Modulus

        if (in > 180) {
            return in - 360;
        } else {
            return in;
        }
    }

    private boolean inRange(double target, double accuracy, double reading) {
        return (Math.abs(target - reading) < accuracy); //If abs of difference is less than accuracy, we are in range
    }

    public void close()
    {
        driveTrain.close();
    }
}

