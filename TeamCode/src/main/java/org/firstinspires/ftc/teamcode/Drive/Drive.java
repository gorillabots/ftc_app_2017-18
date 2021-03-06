package org.firstinspires.ftc.teamcode.Drive;

/**
 * Created by Jarred on 10/22/2017.
 */

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Components.ColorHelper;


public class Drive {


    public ArbitraryDirectionDrive driveTrain;

    //Motors
    private DcMotor frontRight, backRight, frontLeft, backLeft;

    //Sensors
    //private ModernRoboticsI2cGyro gyro;
    private TouchSensor wallTouch;

    Telemetry telemetry;
    HardwareMap hardwareMap;
    private double offset;
    private double offsetConverted;
    LinearOpMode linOp;

    ModernRoboticsI2cRangeSensor rangeSensor;

   ElapsedTime timer;
    public Drive(LinearOpMode opMode) {

        hardwareMap = opMode.hardwareMap;
        telemetry = opMode.telemetry;

        telemetry.addData("made","the first part");
        telemetry.update();

        timer= new ElapsedTime();
        timer.startTime();
        init(0);

        linOp = opMode;
    }

    public double getMag(double x, double y) {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public double getDir(double y, double x) {
        return Math.atan(y / x);
    }

     void init(double offset) //Get hardware from hardwareMap
    {


        driveTrain = new ArbitraryDirectionDrive(hardwareMap, telemetry);

        this.offset = offset;
        offsetConverted = convertHeading(offset);
        rangeSensor = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "range");
    }



    public void resetGyro(){
        driveTrain.gyro.resetZAxisIntegrator();

    }

    public void updateOffset(double offset) {
        this.offset = offset;
        offsetConverted = convertHeading(offset);
    }

    public void encoderMoveMRGyro(double angle, double distance, double power) //Move forwards by distance
    {

        while (driveTrain.distanceCheck(distance)) {

            driveTrain.drivePolar(power, angle);
            telemetry.addData("Status", "Encoder movement");
            telemetry.update();

        }

        driveTrain.stopMotors();

    }

    public void encoderMoveMRGyro2(double angle, double distance, double power, double turnFactor) //Move forwards by distance
    {
        timer.reset();
        while (driveTrain.distanceCheck(distance)&& timer.seconds()<5) {
            driveTrain.drivePolar2(power, angle, turnFactor);
            telemetry.addData("Status", "Encoder movement");
            telemetry.addData("first run status",driveTrain.firstRun);
            telemetry.addData("measured length", driveTrain.length);
            telemetry.addData("final", driveTrain.toEncoder(distance));
            telemetry.update();
        }
        driveTrain.stopMotors();
    }

    public double encoderMoveMRGyro3(double angle, double distance, double power, double turnFactor) //Move forwards by distance
    {   double finishTime=-1;
        timer.reset();
        while (driveTrain.distanceCheck(distance)&& timer.seconds()<5) {
            driveTrain.drivePolar2(power, angle, turnFactor);
            telemetry.addData("Status", "Encoder movement");
            telemetry.addData("first run status",driveTrain.firstRun);
            telemetry.addData("measured length", driveTrain.length);
            telemetry.addData("final", driveTrain.toEncoder(distance));
            finishTime=timer.milliseconds();
            telemetry.addData("finish time", finishTime);
            telemetry.update();
        }
        driveTrain.stopMotors();
        return finishTime;
    }

    public void timeMove(double angle, double time, double power, double turnFactor)
    {
        timer.reset();

        double elapsedTime;

        while((elapsedTime = timer.seconds()) <= time)
        {
            driveTrain.drivePolar2(power, angle, turnFactor);
            telemetry.addData("Status", "Encoder movement");
            telemetry.addData("first run status",driveTrain.firstRun);
            telemetry.addData("time", elapsedTime);
            telemetry.update();
        }

        driveTrain.stopMotors();
    }

    public void colorMove(ColorSensor floorColor, double power, int angle) //Move forwards to white line
    {

        telemetry.addData("Status", "Starting Move to Color");
        telemetry.update();

        //try
        //{
        while (!ColorHelper.isFloorWhiteTest(floorColor) && linOp.opModeIsActive()) {

            driveTrain.drivePolar(power, angle);
            telemetry.addData("Status", "ForwardsToLine");
            telemetry.addData("R", floorColor.red());
            telemetry.addData("G", floorColor.green());
            telemetry.addData("B", floorColor.blue());
            telemetry.update();

        }

        driveTrain.stopMotors();

    }

    private double getRotation() //Get rotation
    {
        return (driveTrain.m1.getCurrentPosition() + driveTrain.m2.getCurrentPosition() +
                driveTrain.m3.getCurrentPosition() + driveTrain.m4.getCurrentPosition()) / 4;
    }

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

    public void rangeMove(double distance, double power, double angle){

        telemetry.addData("starting", "range move");
        telemetry.update();

        while(!inRange(distance, .05, rangeSensor.cmUltrasonic()*0.0328084 )){
            driveTrain.drivePolar(power,angle);
            telemetry.addData("running","range move");
            telemetry.addData("done", "range move");
        }
        driveTrain.stopMotors();
    }

    public void close()
    {
        driveTrain.close();
        this.close();
    }

    public void turn(int target, int error, double maxSpeed, double minSpeed)
    {
        driveTrain.offsetMR += target;

        double heading;
        double dir;

        double speedFactor = maxSpeed - minSpeed;

        while(!inRange(0, error, (heading = driveTrain.getHeadingWithOffset())))
        {
            if(heading > 180)
            {
                heading -= 360;
                dir = -1;
            }
            else
            {
                dir = 1;
            }

            heading /= 180d;

            double turnpow = heading * speedFactor + minSpeed * dir;

            driveTrain.turn(turnpow);
        }

        driveTrain.stopMotors();
    }

    public void zeroWall(double speed, long time){
        driveTrain.m1.setPower(speed);
        driveTrain.m2.setPower(-speed);
        driveTrain.m3.setPower(-speed);
        driveTrain.m4.setPower(speed);
        linOp.sleep(time);
        driveTrain.stopMotors();
        resetGyro();


    }
}

