package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

public class Servos {
    private LinearOpMode opMode;

    public Servo servo1;
    public CRServo servo2;
    public Servo servo3;
    //public Servo servo4;


    public Servos(LinearOpMode myOpMode) {
        opMode = myOpMode;

    }

    public void init() {
        //    imu = hardwareMap.get(Gyroscope.class, "imu");

        servo1 = opMode.hardwareMap.get(Servo.class, "servo1");
        servo2 = opMode.hardwareMap.get(CRServo.class, "servo2");
        servo3 = opMode.hardwareMap.get(Servo.class, "servo3");
        // servo4 = opMode.hardwareMap.get(Servo.class, "servo4");


    }


    public void servoBasketDrop() {
        // servo3.setDirection(Servo.Direction.REVERSE);
        // servo3.setPosition(.34);

    }

    public void servo3Zero() {
        // servo3.setPosition(0);


    }

    public void servo2Zero() {
        //   servo2.setPosition(0);


    }

    public void servo4Zero() {
        //    servo4.setPosition(0);


    }

    public void servo1Zero() {
        servo1.setPosition(0);


    }


    public void servoBasketNormal() {
        //   servo3.setPosition(0);


    }

    public void disableServo3() {
        //  servo3.getController().pwmDisable();

    }

    public void disableServo2() {
        servo2.getController().pwmDisable();

    }

    public void disableServo1() {
        servo1.getController().pwmDisable();

    }

    public void disableServo4() {
        //  servo4.getController().pwmDisable();

    }
}