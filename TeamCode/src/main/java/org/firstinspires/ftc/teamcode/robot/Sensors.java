package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class Sensors {
    private LinearOpMode opMode;
    // public DigitalChannel slideTouch1;
    //  public DigitalChannel slideTouch2;
    //public IMU imu;
    //public ColorSensor colorSensor;
    public DigitalChannel distanceSensor;


    public Sensors(LinearOpMode myOpMode) {
        opMode = myOpMode;
    }

    public void init() {
        //  imu = opMode.hardwareMap.get(IMU.class, "imu");
        //  slideTouch1 = opMode.hardwareMap.get(DigitalChannel.class, "slideTouch1");
        // slideTouch1.setMode(DigitalChannel.Mode.INPUT);
        //  slideTouch2 = opMode.hardwareMap.get(DigitalChannel.class, "slideTouch2");
        //  slideTouch2.setMode(DigitalChannel.Mode.INPUT);
        //  colorSensor = opMode.hardwareMap.get(ColorSensor.class,"color");
        distanceSensor = opMode.hardwareMap.get(DigitalChannel.class, "distance");
        distanceSensor.setMode(DigitalChannel.Mode.INPUT);

    }

    public boolean isObjectDetected() {

        return distanceSensor.getState();


    }

}


