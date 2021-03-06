package org.firstinspires.ftc.teamcode.Drive;

//Created by Mikko on 2017-10-24

import com.kauailabs.navx.ftc.AHRS;
import com.kauailabs.navx.ftc.navXPIDController;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ArbitraryDirectionDrive {

   public DcMotor m1, m2, m3, m4;
    double backLeftPower = 0;
    double frontRightPower = 0;
    double backRightPower = 0;
    double frontLeftPower = 0;
    boolean firstRun = false;

    int m1Start = 0;
    int m2Start = 0;
    int m3Start = 0;
    int m4Start = 0;
    int m1n, m2n, m3n, m4n;
    double length;

    ModernRoboticsI2cGyro gyro;

    HardwareMap hardMap;
    Telemetry telemetryy;
    //add.drive(Direction.N, .5, 10);

    int offsetMR = 0;

    public ArbitraryDirectionDrive(HardwareMap hMap, Telemetry telemetry) {

        telemetryy = telemetry;
        hardMap= hMap;
        init();
    }

     void init() {

        m1 = hardMap.dcMotor.get("m1");
        m2 = hardMap.dcMotor.get("m2");
        m3 = hardMap.dcMotor.get("m3");
        m4 = hardMap.dcMotor.get("m4");
        gyro = (ModernRoboticsI2cGyro) hardMap.gyroSensor.get("gyro");

        gyro.calibrate();

        try //Wait for gyro to calibrate
        {
            while (gyro.isCalibrating()) {
                Thread.sleep(50);
                telemetryy.addData("Status", "Calibrating Gyro");
                telemetryy.update();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        gyro.resetZAxisIntegrator(); //Reset heading
         telemetryy.addData("Status","done");
         telemetryy.update();
    }


    public void drive(double stickX, double stickY, double stickRot) //No rotation fixing from gyro data!
    {
        float facingDeg = -45; //Robot's rotation
        double facingRad = Math.toRadians(facingDeg); // Convert to radians

        double cs = Math.cos(facingRad);
        double sn = Math.sin(facingRad);

        double headX = stickX * cs - stickY * sn; //Rotated vector (Relative heading)
        double headY = stickX * sn + stickY * cs; //Each is in range -root 2 to root 2

        headX /= Math.sqrt(2); //In range -1 to 1
        headY /= Math.sqrt(2);





        //telemetryyaddData("absHead", "(" + stickX + ", " + stickY + ")");

        //telemetryyaddData("relHead", "(" + headX + ", " + headY + ")");

        double backLeftPower = limitToOne(-headX + stickRot );
        double frontRightPower = limitToOne(headX + stickRot );
        double backRightPower = limitToOne(headY + stickRot );
        double frontLeftPower = limitToOne(-headY + stickRot );


        telemetryy.addData("m1", frontLeftPower);
        telemetryy.addData("m2", frontRightPower);
        telemetryy.addData("m3", backRightPower);
        telemetryy.addData("m4", backLeftPower);

        m4.setPower(backLeftPower);
        m2.setPower(frontRightPower);
        m3.setPower(backRightPower);
        m1.setPower(frontLeftPower);
    }

    public void drivePolar(double speed, double direction) //No rotation fixing from gyro data!
    {
        double yComp = speed * Math.sin(Math.toRadians(direction));
        double xComp = speed * Math.cos(Math.toRadians(direction));
        driveCartesian(xComp, yComp, 0);

    }
    public void drivePolar2(double speed, double direction, double rotFactor) //No rotation fixing from gyro data!
    {
        double yComp = speed * Math.sin(Math.toRadians(direction));
        double xComp = speed * Math.cos(Math.toRadians(direction));
        driveCartesian2(xComp, yComp, 0, rotFactor);

    }
    public boolean distanceCheck(double magnitude){


        if (!firstRun) {
            m1Start = (m1.getCurrentPosition());
            m2Start = (m2.getCurrentPosition());
            m3Start = (m3.getCurrentPosition());
            m4Start = (m4.getCurrentPosition());
            firstRun = true;
        }

        int m1Current = (m1.getCurrentPosition());
        int m2Current = (m2.getCurrentPosition());
        int m3Current = (m3.getCurrentPosition());
        int m4Current = (m4.getCurrentPosition());


        int m1Calc = m1Current - m1Start;
        int m2Calc = m2Current - m2Start;
        int m3Calc = m3Current - m3Start;
        int m4Calc = m4Current - m4Start;


        int average1 = (m1Calc + m3Calc)/2;
        int average2 = (m2Calc + m4Calc)/2;

        length = Math.sqrt(average1 * average1+ average2* average2);
        //telemetryy.addData("mag", magnitude);
        //telemetryy.addData("meas", length);
        //telemetryy.update();
        double encMag = toEncoder(magnitude);
        if (length >= encMag) {
            firstRun = false;
            return false;
        } else {
            firstRun = true;
            return true;
        }


    }

    public boolean distanceCheckOld(double magnitude){


        if (!firstRun) {
            m1Start = Math.abs(m1.getCurrentPosition());
            m2Start = Math.abs(m2.getCurrentPosition());
            m3Start = Math.abs(m3.getCurrentPosition());
            m4Start = Math.abs(m4.getCurrentPosition());
            firstRun = true;
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
        //telemetryy.addData("mag", magnitude);
        //telemetryy.addData("meas", length);
        //telemetryy.update();
        double encMag = toEncoder(magnitude);
        if (length >= encMag) {
            firstRun = false;
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

    public int getHeadingWithOffset()
    {
        return ((gyro.getHeading() - offsetMR) + 720) % 360;
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
        heading = getHeadingWithOffset();

        if (heading <= 1 || heading >= 360 - 1) {
            turnpow = 0;
        } else if (heading <= 180) {
            turnpow = turnpower;
        } else {
            turnpow = -turnpower;
        }




        //telemetryy.addData("absHead", "(" + stickX + ", " + stickY + ")");

        //telemetryy.addData("relHead", "(" + headX + ", " + headY + ")");

        double backLeftPower = limitToOne(-headX + stickRot + turnpow);
        double frontRightPower = limitToOne(headX + stickRot + turnpow);
        double backRightPower = limitToOne(headY + stickRot + turnpow);
        double frontLeftPower = limitToOne(-headY + stickRot + turnpow);

        /*telemetryy.addData("heading", heading);
        telemetryy.addData("turnPow", turnpower);
        telemetryy.addData("m1", frontLeftPower);
        telemetryy.addData("m2", frontRightPower);
        telemetryy.addData("m3", backRightPower);
        telemetryy.addData("m4", backLeftPower);*/

        m4.setPower(backLeftPower);
        m2.setPower(frontRightPower);
        m3.setPower(backRightPower);
        m1.setPower(frontLeftPower);
    }

    public void driveCartesian2(double stickX, double stickY, float stickRot, double rotFactor) {
        float facingDeg = -45; //Robot's rotation
        double facingRad = Math.toRadians(facingDeg); // Convert to radians

        double cs = Math.cos(facingRad);
        double sn = Math.sin(facingRad);

        double headX = stickX * cs - stickY * sn; //Rotated vector (Relative heading)
        double headY = stickX * sn + stickY * cs; //Each is in range -root 2 to root 2

        headX /= Math.sqrt(2); //In range -1 to 1
        headY /= Math.sqrt(2);

        double heading = getHeadingWithOffset();

        //telemetryy.addData("Heading1", heading);

        if(heading > 180)
        {
            heading -= 360;
        }

        //telemetryy.addData("Heading2", heading);

        heading /= 180d;

        //telemetryy.addData("Heading3", heading);
        //telemetryy.addData("RotFactor", rotFactor);

        double turnpow = rotFactor * heading;

        //telemetryy.addData("Turnpow", turnpow);

        double backLeftPower = limitToOne(-headX + stickRot + turnpow);
        double frontRightPower = limitToOne(headX + stickRot + turnpow);
        double backRightPower = limitToOne(headY + stickRot + turnpow);
        double frontLeftPower = limitToOne(-headY + stickRot + turnpow);

        /*telemetryy.addData("m1", frontLeftPower);
        telemetryy.addData("m2", frontRightPower);
        telemetryy.addData("m3", backRightPower);
        telemetryy.addData("m4", backLeftPower);
        telemetryy.update();*/

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

    public void turn(double speed)
    {
        m1.setPower(speed);
        m2.setPower(speed);
        m3.setPower(speed);
        m4.setPower(speed);
    }

    public void updateMROffset(int offset)
    {
        offsetMR = offset;
    }

    public void addMROffset(int offset)
    {
        offsetMR += offset;
    }

    public void turnToGyro(int accuracy, double turnpower) //Turn until we are aligned
    {

        int heading = gyro.getHeading();
        double turnpow;

        telemetryy.addData("Action", "Turn to Gyro");
        telemetryy.addData("Heading", heading);

        if(heading <= accuracy || heading >= 360 - accuracy)
        {
            turnpow = 0;
        }
        else if(heading <= 180)
        {
            turnpow = -turnpower;
        }
        else
        {
            turnpow = turnpower;
        }

        m1.setPower(turnpow);
        m2.setPower(turnpow);
        m3.setPower(turnpow);
        m4.setPower(turnpow);
    }

    private AHRS navx;
    navXPIDController pidController;
    navXPIDController.PIDResult pidResult;
    double target;
    double absoluteTarget;
    double speed;
    double offset;
    double offsetConverted;

    public void turnNavxStart(double target, double accuracy, double speed)
    {
        // With target = -135....

        this.target = target;
        absoluteTarget = convertHeading(offset + target);
        // absoluteTarget = 45 | Should be -135

        pidController.reset();
        pidController.setSetpoint(absoluteTarget);
        pidController.setTolerance(navXPIDController.ToleranceType.ABSOLUTE, accuracy);
        pidController.enable(true);

        this.speed = speed;
    }

    public boolean turnNavxLoop()
    {
        try
        {
            if (pidController.waitForNewUpdate(pidResult, 500))
            {
                double power = pidResult.getOutput();

                telemetryy.addData("Status", "Turn");
                telemetryy.addData("Offset", offset);
                telemetryy.addData("Heading", navx.getYaw());
                telemetryy.addData("Target", target);
                telemetryy.addData("Absolute target", absoluteTarget);
                telemetryy.addData("Defined Speed", speed);
                telemetryy.addData("PID Speed", power);

                telemetryy.update();

                if (power > -.15 && power < .15)
                {
                    if (Math.abs(power) < .05)
                    {
                        return true;
                    }
                    if (power > 0 && power < .15)
                    {
                        power = .17;
                    }
                    if (power < 0 && power > -.15)
                    {
                        power = -.17;
                    }
                }

                m1.setPower(power * speed);
                m2.setPower(power * speed);
                m3.setPower(power * speed);
                m4.setPower(power * speed);

                return false;
            }
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }

        return false;
    }

    public void turnNavxStop()
    {
        m1.setPower(0);
        m2.setPower(0);
        m3.setPower(0);
        m4.setPower(0);

        offset += target;
        offsetConverted = convertHeading(offset);
    }

    private double convertHeading(double in) //0-360
    {
        while(in < 0) //Make sure in is positive
        {
            in += 360;
        }

        in %= 360; //Modulus

        if(in > 180)
        {
            return in - 360;
        }
        else
        {
            return in;
        }
    }

    public void close()
    {
        gyro.close();
    }
}




