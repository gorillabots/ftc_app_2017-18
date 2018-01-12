package org.firstinspires.ftc.teamcode.Components;

//Created by Mikko on 2017-11-29

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Drive.Drive;

public class JewelsAndrew {

    public ColorSensor color;
    final double ARM_RAISED = .22;
    final double ARM_LOWERED = .9;
    public Servo jewelArm;
    public Servo swing;
    Telemetry telemetry;
    public boolean moveLeft;

    public ColorSensor secondColor;

    public JewelsAndrew(HardwareMap hm, Telemetry tele) {
        telemetry = tele;
        telemetry.addData("status", "init jewels");
        telemetry.update();

        color = hm.colorSensor.get("ballColor");
        jewelArm = hm.servo.get("arm");

        color.enableLed(false);
        color.enableLed(true);

        secondColor = hm.colorSensor.get("leftColor");
        secondColor.setI2cAddress(I2cAddr.create8bit(68));

        swing = hm.servo.get("swing");
        telemetry.addData("status", "finished jewel init");
        telemetry.update();

        reset();
    }

    public void ledState(boolean led) {
        color.enableLed(led);
        secondColor.enableLed(led);
    }

    public void reset() {
        jewelArm.setPosition(.15);
        swing.setPosition(.63);
        ledState(true);

    }

    public void toogleSwing(boolean bool) {
        if (bool) {
            swing.setPosition(.39);
        } else if (!bool) {
            swing.setPosition(.63);
        }
    }

    public void lowerArm() {
        jewelArm.setPosition(.9);

    }

    public void hitBallsOld(Drive drive, boolean color, boolean opposColor) {
        if (color || opposColor) {
            drive.turn(349, 2, .2, .1);
            this.reset();
            try {
                Thread.sleep(100, 0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            drive.turn(11, 2, .2, .1);
            // drive.encoderMoveMRGyro(90,.2,.5);
        } else {
            drive.turn(11, 2, .2, .1);
            this.reset();
            try {
                Thread.sleep(100, 0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            drive.turn(349, 2, .2, .1);
            //drive.encoderMoveMRGyro(270,.2,.5);
            moveLeft = true;
        }
    }

    public void hitBalls(boolean color, boolean opposColor) {
        if (color || opposColor) {
            swing.setPosition(.59);
            try {
                Thread.sleep(100, 0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            toogleSwing(true);
        } else {

            swing.setPosition(.28);
            try {
                Thread.sleep(100, 0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            toogleSwing(true);
        }
    }

    public void AHEhitBallsVariablesForBlue(boolean leftSensorblue, boolean leftSensorred, boolean rightSensorblue, boolean rightSensorred) {
        if (leftSensorblue && leftSensorred && rightSensorblue && rightSensorred) {
            swingLeft();
            swingRight();
        } else {
            if (leftSensorblue) {
                if (rightSensorred) {
                    swingRight();
                    try {
                        Thread.sleep(100, 0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    toogleSwing(true);
                } else {
                    if (leftSensorred) {
                        if (rightSensorred) {
                            swingRight();
                            try {
                                Thread.sleep(100, 0);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            toogleSwing(true);
                        } else {
                            swingLeft();
                            try {
                                Thread.sleep(100, 0);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            toogleSwing(true);
                        }
                    } else {
                    }
                }
            } else {
                if (rightSensorblue) {
                    swingLeft();
                    try {
                        Thread.sleep(100, 0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    toogleSwing(true);
                } else {
                }
            }
        }
    }

    public void AHEhitBallsVariablesForBlueVersionTwo(boolean leftSensorblue, boolean leftSensorred, boolean rightSensorblue, boolean rightSensorred) {
        if (leftSensorblue && leftSensorred && rightSensorblue && rightSensorred) {          // color sensors all fail
            swingLeft();
            try {
                Thread.sleep(100, 0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            swingRight();
            try {
                Thread.sleep(100, 0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (leftSensorblue && leftSensorred) { //left sensor fails
            if (rightSensorblue) {
                swingLeft();
                try {
                    Thread.sleep(100, 0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                swingRight();
                try {
                    Thread.sleep(100, 0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else if (rightSensorblue && rightSensorred) { //right sensor fails
            if (leftSensorblue) {
                swingRight();
                try {
                    Thread.sleep(100, 0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                swingLeft();
                try {
                    Thread.sleep(100, 0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else if ((rightSensorblue && leftSensorblue) || (leftSensorred && rightSensorred)) { //sensors both working but giving opposite readings
            // do nothing
        } else if (rightSensorblue) { //both sensors are reading
            swingLeft();
            try {
                Thread.sleep(100, 0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            swingRight();
            try {
                Thread.sleep(100, 0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void swingRight() {
        swing.setPosition(.28);
    }

    public void swingLeft() {
        swing.setPosition(.59);
    }

    public boolean isRedRight()
    {
        sleepCatch(10);
        return secondColor.red() >= secondColor.blue();
    }

    public boolean isRedLeft()
    {
        sleepCatch(10);
        return color.red() >= color.blue();
    }

    public boolean isBlueRight()
    {
        sleepCatch(10);
        return secondColor.red() >= secondColor.blue();
    }

    public boolean isBlueLeft()
    {
        sleepCatch(10);
        return color.blue() >= color.red();
    }

    public void sleepCatch(long millis)
    {
        try
        {
            Thread.sleep(millis);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
