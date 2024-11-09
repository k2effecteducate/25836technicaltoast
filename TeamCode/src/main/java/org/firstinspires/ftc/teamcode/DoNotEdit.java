package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotor;
import  com.qualcomm.robotcore.hardware.Gyroscope;
import  com.qualcomm.robotcore.hardware.DigitalChannel;
import  com.qualcomm.robotcore.hardware.DistanceSensor;
import  com.qualcomm.robotcore.hardware.Servo;
@TeleOp(name = "DoNotEdit", group="Linear OpMode")


public class DoNotEdit extends LinearOpMode {
    private DcMotor motorOne;
    private DcMotor motorZero;
    private DcMotor motorTwo;
    private DcMotor motorThree;
    //private DigitalChannel digitalTouch;
    //private DistanceSensor sensorColorRange;
    //private Servo servoTest;


    @Override
    public void runOpMode() {
        //imu = hardwareMap.get(Gyroscope.class, "imu");
        motorThree = hardwareMap.get(DcMotor.class, "motorThree");
        motorTwo = hardwareMap.get(DcMotor.class, "motorTwo");
        motorOne = hardwareMap.get(DcMotor.class, "motorOne");
        motorZero = hardwareMap.get(DcMotor.class, "motorZero");
        //digitalTouch = hardwareMap.get(DigitalChannel.class, "digitalTouch");
        //sensorColorRange = hardwareMap.get(DistanceSensor.class, "sensorColorRange");
        // servoTest = hardwareMap.get(Servo.class, "servoTest");

        telemetry.addData ("Initialized","is a win");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        // run until the end of the match (driver presses STOP)
        // line 43 and 54 is a loop in the () it says while in this case our code is active run and it repeats it over and over
        // just a reminder it reads it line by line not all at once
        while (opModeIsActive()) {
            float tgtPower = -this.gamepad1.left_stick_y;
            motorOne.setPower(tgtPower);
            motorTwo.setPower(tgtPower);
            motorThree.setPower(tgtPower);
            motorZero.setPower(tgtPower);
            //tgtPower can be named anything it is just a place holder for now
            //Inside the () in line 48 basically says this is how you move this motor
            // motorZero.setPower( -this.gamepad1.right_stick_y);
            // line 50 to 52 just sends data to the hub this is an example and isn't
            telemetry.addData("Target Power", tgtPower);
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
