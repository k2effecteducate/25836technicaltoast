package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;



/* This is an empty code that does nothing.
If you want try something quickly this is the place to do it.
If you want to copy and paste it to make live easier for yourself go ahead.
When you use it please add comments to what the stuff does so our fellow team mates know what's happening. Thank You! */



    @TeleOp(name = "Runtest1", group="Linear OpMode")
// This is set up for TeleOp but you can change it

    public class Runtest1 extends LinearOpMode {

        private DcMotor motorOne;
        private DcMotor motorZero;
        private DcMotor motorTwo;
        private DcMotor motorThree;

        @Override public void runOpMode() {
          //this is where it "gets" the data from our config file
            motorThree = hardwareMap.get(DcMotor.class, "motorThree");
            motorTwo = hardwareMap.get(DcMotor.class, "motorTwo");
            motorOne = hardwareMap.get(DcMotor.class, "motorOne");
            motorZero = hardwareMap.get(DcMotor.class, "motorZero");

            telemetry.addData("Initialized", "is a win");
            telemetry.update();
            // Wait for the game to start (driver presses PLAY)
            waitForStart();

            while (opModeIsActive()) {
              //This is where you put your code
                float sideways = (-gamepad1.left_stick_x);
               float y =(-gamepad1.left_stick_y);
               float clock =(-gamepad1.left_trigger);
               float counterClock =(-gamepad1.right_trigger);
                motorOne.setPower(y);
                motorTwo.setPower(y);
                motorZero.setPower(y);
                motorThree.setPower(y);
                // below goes side ways
                motorOne.setPower(-sideways);
                motorTwo.setPower(-sideways);
                motorZero.setPower(sideways);
                motorThree.setPower(sideways);

                motorZero.setPower(clock);
                motorOne.setPower(-clock);
                motorTwo.setPower(clock);
                motorThree.setPower(-clock);

                motorZero.setPower(-counterClock);
                motorOne.setPower(counterClock);
                motorTwo.setPower(-counterClock);
                motorThree.setPower(counterClock);


                while (opModeIsActive()) {
                    telemetry.addData("Status", "Running");
                    telemetry.update();

                }
            }
        }
    }