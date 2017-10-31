package org.firstinspires.ftc.teamcode.Components;

import com.sun.tools.javac.util.List;

import java.util.Vector;

/**
 * Created by Jarred on 10/24/2017.
 */


public class MovementHistory {

    double[] storageArray;
    int storedMov;

    double calcX;
    double calcY;
    public MovementHistory(){
        storedMov = 0;
        storageArray=new double[50];
        calcX = 0;
        calcY = 0;

    }
    public void add(int degree,int magnitude){

        storageArray[storedMov] = (magnitude*Math.cos(degree));
        storageArray[storedMov+1] = (magnitude*Math.sin(degree));
        storedMov= storedMov +2;
    }

    public void calculate(){
        double tempX =0;
        double tempY = 0;
        for (int i =0; (i<storedMov); i=i+2){
            tempX= tempX+ storageArray[i];
            tempY= tempY+ storageArray[i+1];
        }
        calcX=tempX;
        calcY=tempY;



    }

    public int getMovementCount(){
        return storedMov;
    }

    public double getDegree(){
        return Math.atan(calcY/calcX);
    }

    public double getMagnitude(){
        double mag = Math.sqrt(Math.pow(calcX,2)+Math.pow(calcX,2));
        return mag;
    }

    

}
