package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;



@TeleOp(name = "TeleOpControls", group="Linear OpMode")


public class TeleOpControls extends LinearOpMode {
    private DcMotor motorOne;
    private DcMotor motorZero;
    private DcMotor motorTwo;
    private DcMotor motorThree;
    //private DigitalChannel digitalTouch;
    //private DistanceSensor sensorColorRange;
    //private Servo servoTest;


    @Override
    public void runOpMode() {
        //    imu = hardwareMap.get(Gyroscope.class, "imu");
        motorThree = hardwareMap.get(DcMotor.class, "backRight");
        motorTwo = hardwareMap.get(DcMotor.class, "backLeft");
        motorOne = hardwareMap.get(DcMotor.class, "frontRight");
        motorZero = hardwareMap.get(DcMotor.class, "frontLeft");
        //digitalTouch = hardwareMap.get(DigitalChannel.class, "digitalTouch");
        //sensorColorRange = hardwareMap.get(DistanceSensor.class, "sensorColorRange");
        // servoTest = hardwareMap.get(Servo.class, "servoTest");

        telemetry.addData("Initialized", "is a win");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        while (opModeIsActive()) {
            motorZero.setDirection(DcMotor.Direction.REVERSE);
            motorTwo.setDirection(DcMotor.Direction.REVERSE);
            // motorThree.setDirection(DcMotor.Direction.REVERSE);
            if (gamepad1.dpad_right) {

                motorZero.setPower(0.7);
                motorTwo.setPower(0.7);
                motorOne.setPower(0.7);
                motorThree.setPower(0.7);
            } else if (gamepad1.dpad_left) {
                motorZero.setPower(-0.7);
                motorTwo.setPower(-0.7);
                motorOne.setPower(-0.7);
                motorThree.setPower(-0.7);
          /*  } else if (gamepad1.left_bumper) {
                motorTwo.setDirection(DcMotor.Direction.FORWARD);
                motorZero.setDirection(DcMotor.Direction.FORWARD);
                motorZero.setPower(.6);
                motorTwo.setPower(-.6);
                motorOne.setPower(-.6);
                motorThree.setPower(.6);
            } else if (gamepad1.right_bumper) {
                motorTwo.setDirection(DcMotor.Direction.FORWARD);
                motorZero.setDirection(DcMotor.Direction.FORWARD);
                motorZero.setPower(-.6);
                motorTwo.setPower(.6);
                motorOne.setPower(.6);
                motorThree.setPower(-.6); */


            } else {
                float forward = -this.gamepad1.left_stick_y;

                motorTwo.setDirection(DcMotor.Direction.FORWARD);
                motorZero.setDirection(DcMotor.Direction.FORWARD);
                motorTwo.setPower(forward);
                motorZero.setPower(forward);
                motorOne.setPower(forward);
                motorThree.setPower(forward);


                float fowardSideways = this.gamepad1.left_stick_x;
                float reverseSideways = -this.gamepad1.left_stick_x;
                motorZero.setPower(reverseSideways);
                motorTwo.setPower(reverseSideways);
                motorOne.setPower(fowardSideways);
                motorThree.setPower(fowardSideways);
                float turn = -this.gamepad1.right_stick_x;
                float rTurn = this.gamepad1.right_stick_x;
                motorZero.setPower(turn);
                motorTwo.setPower(rTurn);
                motorOne.setPower(rTurn);
                motorThree.setPower(turn);
            }
        }while (opModeIsActive()) {
                    telemetry.addData("Status", "Running");
                    telemetry.update();


                }
            }
        }