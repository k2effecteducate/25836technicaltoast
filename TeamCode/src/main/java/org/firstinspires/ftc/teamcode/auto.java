package org.firstinspires.ftc.teamcode;

import java.lang.Math;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous (name = "auto", group="Linear OpMode")
public class auto extends LinearOpMode {

        private DcMotor frontRight;
        private DcMotor frontLeft;
        private DcMotor backRight;
        private DcMotor backLeft;
        private DcMotor armMotor;
      //  private Servo servo1;
        private Servo servo2;
       // private Servo servo3;

        @Override
        public void runOpMode() {
            //    imu = hardwareMap.get(Gyroscope.class, "imu");
            backRight = hardwareMap.get(DcMotor.class, "backRight");
            backLeft = hardwareMap.get(DcMotor.class, "backLeft");
            frontRight = hardwareMap.get(DcMotor.class, "frontRight");
            frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
            armMotor = hardwareMap.get(DcMotor.class, "armMotor");
           // servo1 = hardwareMap.get(Servo.class, "servo1");


            servo2 = hardwareMap.get(Servo.class, "servo2");
            //servo3 = hardwareMap.get(Servo.class, "servo3");

            frontLeft.setDirection(DcMotor.Direction.REVERSE);
            backLeft.setDirection(DcMotor.Direction.REVERSE);

            //  servo2.setDirection(Servo.Direction.REVERSE);
            servo2.setPosition(.4);

            telemetry.addData("Initialized", "is a win");
            telemetry.update();
            waitForStart();
            // while (opModeIsActive()) {



            frontLeft.setPower(-.4);
            frontRight.setPower(-.4);
            backLeft.setPower(-.4);
            backRight.setPower(-.4);
            sleep(100);
            frontLeft.setPower(.4);
            frontRight.setPower(-.4);
            backLeft.setPower(-.4);
            backRight.setPower(.4);
            sleep(3100);
            frontLeft.setPower(-.4);
            frontRight.setPower(.4);
            backLeft.setPower(.4);
            backRight.setPower(-.4);
            sleep(6000);





            telemetry.addData("Status", "Running");
            telemetry.update();
        }
}

