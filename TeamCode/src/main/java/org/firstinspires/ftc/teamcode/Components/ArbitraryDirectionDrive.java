package org.firstinspires.ftc.teamcode.Components;

//Created by Mikko on 2017-10-24

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ArbitraryDirectionDrive {
    LinearOpMode opmode;
    DcMotor m1, m2, m3, m4;
    double backLeftPower = 0;
    double frontRightPower = 0;
    double backRightPower = 0;
    double frontLeftPower = 0;
    boolean firstRun = false;
    Telemetry telemetry;
    HardwareMap hardwareMap;
    int m1Start = 0;
    int m2Start = 0;
    int m3Start = 0;
    int m4Start = 0;
    int m1n, m2n, m3n, m4n;
    double length;

    ModernRoboticsI2cGyro gyro;
    //add.drive(Direction.N, .5, 10);

    public ArbitraryDirectionDrive(HardwareMap hMap, Telemetry telemetry) {

        this.telemetry = telemetry;
        this.hardwareMap = hMap;
        init();
    }

    public void init() {

        m1 = hardwareMap.dcMotor.get("m1");
        m2 = hardwareMap.dcMotor.get("m2");
        m3 = hardwareMap.dcMotor.get("m3");
        m4 = hardwareMap.dcMotor.get("m4");
        gyro = (ModernRoboticsI2cGyro) hardwareMap.gyroSensor.get("gyro");

        gyro.calibrate();

        try //Wait for gyro to calibrate
        {
            while (gyro.isCalibrating()) {
                Thread.sleep(50);
                telemetry.addData("Status", "Calibrating Gyro");
                telemetry.update();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        gyro.resetZAxisIntegrator(); //Reset heading
    }


    public void drive(double speed, double direction) //No rotation fixing from gyro data!
    {
        float facingDeg = -45; //Robot's rotation
        double facingRad = Math.toRadians(facingDeg);
        double m13, m24; //Amount to actually drive


        //Apply power to motors;


        m13 = limitToOne(Math.sin(facingRad) * speed);
        m24 = limitToOne(Math.cos(facingRad) * speed);

        telemetry.addData("speed", speed);
        telemetry.addData("direction", direction);
        telemetry.addData("m13", m13);
        telemetry.addData("m24", m24);
        telemetry.update();

        m1.setPower((m13));
        m2.setPower((m24));
        m3.setPower((-m13));
        m4.setPower((-m24));

    }

    public void drivePolar(double speed, double direction) //No rotation fixing from gyro data!
    {
        double yComp = speed * Math.sin(Math.toRadians(direction));
        double xComp = speed * Math.cos(Math.toRadians(direction));
        driveCartesian(xComp, yComp, 0);

    }
    public boolean distanceCheck(double magnitude){

        if (!firstRun) {
            m1Start = Math.abs(m1.getCurrentPosition());
            m2Start = Math.abs(m2.getCurrentPosition());
            m3Start = Math.abs(m3.getCurrentPosition());
            m4Start = Math.abs(m4.getCurrentPosition());
        }

        int m1Current = Math.abs(m1.getCurrentPosition());
        int m2Current = Math.abs(m2.getCurrentPosition());
        int m3Current = Math.abs(m3.getCurrentPosition());
        int m4Current = Math.abs(m4.getCurrentPosition());


        int m1Calc = m1Current - m1Start;
        int m2Calc = m2Current - m2Start;
        int m3Calc = m3Current - m3Start;
        int m4Calc = m4Current - m4Start;


        int average1 = (m1Calc + m3Calc)/2;
        int average2 = (m2Calc + m4Calc)/2;

        length = Math.sqrt(average1 * average1+ average2* average2);
        telemetry.addData("mag", magnitude);
        telemetry.addData("meas", length);
        telemetry.update();
        double encMag = toEncoder(magnitude);
        if (length >= encMag) {
            firstRun = true;
            return false;
        } else {
            firstRun = true;
            return true;
        }


    }


    public void stopMotors() {
        m1.setPower(0);
        m2.setPower(0);
        m3.setPower(0);
        m4.setPower(0);
    }

    public double toFeet(double encoder){
        return encoder/1935.48;
    }

    public double toEncoder(double feet){
        return feet*1935.48;
    }
    public void driveCartesian(double stickX, double stickY, float stickRot) {
        float facingDeg = -45; //Robot's rotation
        double facingRad = Math.toRadians(facingDeg); // Convert to radians

        double cs = Math.cos(facingRad);
        double sn = Math.sin(facingRad);

        double headX = stickX * cs - stickY * sn; //Rotated vector (Relative heading)
        double headY = stickX * sn + stickY * cs; //Each is in range -root 2 to root 2

        headX /= Math.sqrt(2); //In range -1 to 1
        headY /= Math.sqrt(2);

        int heading;
        double turnpow;
        double turnpower = 0.1;
        heading = gyro.getHeading();

        if (heading <= 1 || heading >= 360 - 1) {
            turnpow = 0;
        } else if (heading <= 180) {
            turnpow = turnpower;
        } else {
            turnpow = -turnpower;
        }




        //telemetry.addData("absHead", "(" + stickX + ", " + stickY + ")");

        //telemetry.addData("relHead", "(" + headX + ", " + headY + ")");

        double backLeftPower = limitToOne(-headX + stickRot + turnpow);
        double frontRightPower = limitToOne(headX + stickRot + turnpow);
        double backRightPower = limitToOne(headY + stickRot + turnpow);
        double frontLeftPower = limitToOne(-headY + stickRot + turnpow);

        telemetry.addData("heading", heading);
        telemetry.addData("turnPow", turnpower);
        telemetry.addData("m1", frontLeftPower);
        telemetry.addData("m2", frontRightPower);
        telemetry.addData("m3", backRightPower);
        telemetry.addData("m4", backLeftPower);

        m4.setPower(backLeftPower);
        m2.setPower(frontRightPower);
        m3.setPower(backRightPower);
        m1.setPower(frontLeftPower);
    }


    public double limitToOne(double in) {
        if (in < -1) {
            return -1;
        }
        if (in > 1) {
            return 1;
        }

        return in;
    }
}




