package org.firstinspires.ftc.teamcode.robot;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.IMU;

public class Sensors {
    private LinearOpMode opMode;
    public DigitalChannel slideTouch;
    public IMU imu;
    public ColorSensor colorSensor;
   // public DistanceSensor distanceSensor;


    public Sensors(LinearOpMode myOpMode) {
        opMode = myOpMode;
    }
    public void init() {
        imu = opMode.hardwareMap.get(IMU.class, "imu");
        slideTouch = opMode.hardwareMap.get(DigitalChannel.class, "slideTouch");
        slideTouch.setMode(DigitalChannel.Mode.INPUT);
        colorSensor = opMode.hardwareMap.get(ColorSensor.class,"color");
       // distanceSensor = opMode.hardwareMap.get(DistanceSensor.class,"distance");



        }

        public void colorBlue(){

            // a way to read blue sample


        }
        public  void colorRed(){

        }


    }


