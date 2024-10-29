package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.java_websocket.framing.ContinuousFrame;


@TeleOp(name = "DriveTeleOp", group="Linear OpMode")

    public class DriveTeleOp extends LinearOpMode {

    private DcMotor frontRight;
    private DcMotor frontLeft;
    private DcMotor backRight;
    private DcMotor backLeft;
    private DcMotor armMotor;
    private Servo servo2;
    private Servo servo1;
    // private Servo servo3;

    @Override
    public void runOpMode() {
        //    imu = hardwareMap.get(Gyroscope.class, "imu");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        armMotor = hardwareMap.get(DcMotor.class, "armMotor");
        servo1 = hardwareMap.get(Servo.class, "servo1");


        servo2 = hardwareMap.get(Servo.class, "servo2");
        // servo3 = hardwareMap.get(Servo.class, "servo3");

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);

        //  servo2.setDirection(Servo.Direction.REVERSE);

        telemetry.addData("Initialized", "is a win");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("on", "on");
            telemetry.update();


            if (gamepad2.left_bumper) {
                servo2.setPosition(-.5);

            } else if (gamepad2.right_bumper) {
                servo2.setPosition(.5);
            } else {
                double modifier = 1;
                if (gamepad1.b)
                    modifier = 2;

                double drive = -gamepad1.left_stick_y;
                double turn = gamepad1.left_stick_x;
                double strafe = gamepad1.right_stick_x;
                double MrArm = gamepad2.right_stick_y;
                double MrHand = -gamepad2.left_stick_y;

                backLeft.setPower((drive - strafe + turn) / modifier);
                frontLeft.setPower((drive - strafe - turn) / modifier);
                backRight.setPower((drive + strafe - turn) / modifier);
                frontRight.setPower((drive + strafe + turn) / modifier);
                servo1.setPosition(MrHand);
                armMotor.setPower(MrArm);
                servo2.setPosition(0);



                // servo2.setPosition(MrSide);


                }
            }
        }
    }





