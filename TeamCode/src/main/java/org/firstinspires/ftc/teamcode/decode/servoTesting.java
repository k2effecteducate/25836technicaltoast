package org.firstinspires.ftc.teamcode.decode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.robot.Movement;


@TeleOp(name = "servoTesting", group = "Linear OpMode")

public class servoTesting extends LinearOpMode {
    public CRServo servo1;
    public CRServo servo2;
    public CRServo servo3;
    public DcMotor motor1;
    public DcMotor motor2;
    public Servo servo4;

    Movement movement = new Movement(this);


    @Override
    public void runOpMode() {
        movement.init();
        servo1 = hardwareMap.get(CRServo.class, "servo1");
        servo2 = hardwareMap.get(CRServo.class, "servo2");
        servo3 = hardwareMap.get(CRServo.class, "servo3");
        servo4 = hardwareMap.get(Servo.class, "servo4");
        motor1 = hardwareMap.get(DcMotor.class, "motor1");
        motor2 = hardwareMap.get(DcMotor.class, "motor2");

        waitForStart();
        while (opModeIsActive()) {
            movement.teleOpControls();

            double powerMotor1 = .9;
            double powerMotor2 = .7;
            if (gamepad1.dpad_down) {

                servo4.setPosition(.8);

            } else {
                servo4.setPosition(0);
            }
            if (gamepad1.a) {
                servo1.setPower(-1);
                servo2.setPower(1);
                servo3.setPower(1);

            }
            if (gamepad1.b) {
                servo1.setPower(1);
                servo2.setPower(-1);
                servo3.setPower(-1);

            }
            if (gamepad1.x) {
                servo1.setPower(0);
                servo2.setPower(0);
                servo3.setPower(0);

            }
            if (gamepad1.left_bumper) {


                motor1.setPower(powerMotor1);
                motor2.setPower(-powerMotor2);

            }
            if (gamepad1.dpad_up) {

                motor1.setPower(.7);
                motor2.setPower(.4);
                //motor2.setPower(-powerMotor2);

            }
            if (gamepad1.right_bumper) {
                motor1.setPower(-powerMotor1);
                motor2.setPower(powerMotor2);

            }
            if (gamepad1.y) {
                motor1.setPower(0);
                motor2.setPower(0);


            }
        }
    }

}

