package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "RobotCode2", group = "Linear OpMode")


public class RobotCode2 extends LinearOpMode {

    private DcMotor motorOne;
    private DcMotor motorZero;
    private DcMotor motorTwo;
    private DcMotor motorThree;


    @Override
    public void runOpMode() {

        motorThree = hardwareMap.get(DcMotor.class, "motorThree");
        motorTwo = hardwareMap.get(DcMotor.class, "motorTwo");
        motorOne = hardwareMap.get(DcMotor.class, "motorOne");
        motorZero = hardwareMap.get(DcMotor.class, "motorZero");


        motorZero.setDirection(DcMotor.Direction.REVERSE);
        motorTwo.setDirection(DcMotor.Direction.REVERSE);
        telemetry.addData("Initialized", "It is working yay!");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        // run until the end of the match (driver presses STOP)

        while (opModeIsActive()) {
//

         if (gamepad1.dpad_up) {
               // telemetry.addData("Status", "Running dpad_left");
                //telemetry.update();
                motorZero.setPower(0.5);
                motorTwo.setPower(0.5);
                motorOne.setPower(0.5);
                motorThree.setPower(0.5);
           // } else if (gamepad1.dpad_right) {
               // telemetry.addData("Status", "Running dpad_Right");
                //telemetry.update();
                //motorZero.setPower(-0.5);
                //motorTwo.setPower(-0.5);
                //motorOne.setPower(-0.5);
                //motorThree.setPower(-0.5);
            } else {
                /* telemetry.addData("Status", "Running forward");
                telemetry.update();*/
                motorTwo.setPower(gamepad1.right_stick_y);
                motorZero.setPower(gamepad1.right_stick_y);
                motorOne.setPower(gamepad1.left_stick_y);
                motorThree.setPower(gamepad1.left_stick_y);
             telemetry.addData("Motor Power", motorZero.getPower());
             telemetry.addData("Motor Power", motorOne.getPower());
             telemetry.addData("Motor Power", motorTwo.getPower());
             telemetry.addData("Motor Power", motorThree.getPower());
                telemetry.addData("Status", "Running");
            }
            while (opModeIsActive()) {
                telemetry.addData("Status", "Running");
                telemetry.update();

            }
        }
    }
}