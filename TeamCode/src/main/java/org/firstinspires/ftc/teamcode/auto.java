package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous (name = "auto", group="Linear OpMode")
public class auto extends LinearOpMode {
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

            motorThree.setPower(1);
            motorThree.setPower(1);
            motorThree.setPower(1);
            motorThree.setPower(1);


            telemetry.addData("Status", "Running");
        }
        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");
            telemetry.update();
        }
    }
}
