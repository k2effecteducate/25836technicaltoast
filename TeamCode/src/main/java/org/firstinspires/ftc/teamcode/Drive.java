package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Drive", group="Linear OpMode")


public class Drive extends LinearOpMode {

    DcMotorSimple motorZero = hardwareMap.get(DcMotorSimple.class, "frontLeft");
    DcMotorSimple motorOne = hardwareMap.get(DcMotorSimple.class, "frontRight");
    DcMotorSimple motorTwo = hardwareMap.get(DcMotorSimple.class, "backLeft");
    DcMotorSimple motorThree = hardwareMap.get(DcMotorSimple.class, "backRight");


    ElapsedTime runTime = new ElapsedTime();

    @Override
    public void runOpMode() {

        waitForStart();
        motorOne.setPower(0.5);
        motorTwo.setPower(0.5);
        motorZero.setPower(0.5);
        motorThree.setPower(0.5);


        //put movement
        resetRuntime();
        while (opModeIsActive() && (runTime.seconds() < 1)) ;
        {
            idle();
        }
        //put 0 movement
        motorOne.setPower(0);
        motorTwo.setPower(0);
        motorZero.setPower(0);
        motorThree.setPower(0);
        resetRuntime();
        while (opModeIsActive() && (runTime.seconds() < 1)) ;
        {
            idle();
        }

                motorOne.setPower(-0.5);
                motorTwo.setPower(-0.5);
                motorZero.setPower(0.5);
                motorThree.setPower(0.5);
                resetRuntime();
                while (opModeIsActive() && (runTime.seconds() < 1)) ;
                {
                    idle();
                }

                //put 0 movement
                motorOne.setPower(0);
                motorTwo.setPower(0);
                motorZero.setPower(0);
                motorThree.setPower(0);
                requestOpModeStop();
                {
                    telemetry.addData("Status", "Running");
                    telemetry.update();


                }
            }
        }

