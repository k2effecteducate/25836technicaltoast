package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.java_websocket.framing.ContinuousFrame;


@TeleOp(name = "Gamepad1", group="Linear OpMode")

public class Gamepad1 extends LinearOpMode {

    private DcMotor frontRight;
    private DcMotor frontLeft;
    private DcMotor backRight;
    private DcMotor backLeft;
    private DcMotor armMotor;
    private Servo servo2;
    private Servo servo1;
    private  DcMotor slideMotor;
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
        slideMotor= hardwareMap.get(DcMotor.class, "slideMotor");


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


            if (gamepad1.left_bumper) {
                servo2.setPosition(-.5);

            } else if (gamepad1.right_bumper) {
                servo2.setPosition(.5);
            } else {
//                double modifier = 1;
//                if (gamepad1.b)
//                    modifier = 2;


                double drive = -gamepad1.left_stick_y;
                double turn = gamepad1.left_stick_x;
                double strafe = gamepad1.right_stick_x;
                double MrArm = gamepad1.left_trigger;
                double slide = -gamepad1.right_trigger;
                //  double clipTurn = -gamepad2.left_trigger;
                //  double Strait = gamepad2.right_trigger;

                //    double MrSide = -gamepad2.left_stick_x;
                if (gamepad1.a)
                    servo1.setPosition(0);
                else
                    servo1.setPosition(1);
              if (gamepad1.dpad_up)
                            slideMotor.setPower(1);
                       if (gamepad1.dpad_down)
                           slideMotor.setPower(-.5);
                       else
                           slideMotor.setPower(0);

                    backLeft.setPower((drive - strafe - turn) / 2);
                    frontLeft.setPower((drive - strafe + turn) /2);
                    backRight.setPower((drive + strafe + turn) / 2);
                    frontRight.setPower((drive + strafe - turn) /2);
                    //servo1.setPosition(MrHand);
                    armMotor.setPower(MrArm);
                    servo2.setPosition(0);
                    armMotor.setPower(-gamepad1.right_trigger);



                    // servo2.setPosition(MrSide);


                }
            }
        }
    }
