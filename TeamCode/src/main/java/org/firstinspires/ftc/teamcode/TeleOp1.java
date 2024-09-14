package org.firstinspires.ftc.teamcode;

 import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
 import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name = "TeleOp1", group="Linear OpMode")


public class TeleOp1 extends LinearOpMode {
    private DcMotor frontRight;
    private DcMotor frontLeft ;
    private DcMotor backRight;
    private DcMotor backLeft;
    private DcMotor armMotor;
    private Servo servo1;
    private Servo servo2;
    private Servo servo3;
    //private DigitalChannel digitalTouch;
    //private DistanceSensor sensorColorRange;
    //private Servo servoTest;


    @Override
    public void runOpMode() {
        //    imu = hardwareMap.get(Gyroscope.class, "imu");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        armMotor = hardwareMap.get(DcMotor.class,"armMotor");
        servo1 = hardwareMap.get(Servo.class, "servo1");
        servo2 = hardwareMap.get(Servo.class,"servo2");
        servo3 = hardwareMap.get(Servo.class,"servo3");
        //digitalTouch = hardwareMap.get(DigitalChannel.class, "digitalTouch");
        //sensorColorRange = hardwareMap.get(DistanceSensor.class, "sensorColorRange");
        // servoTest = hardwareMap.get(Servo.class, "servoTest");

        telemetry.addData("Initialized", "is a win");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        while (opModeIsActive()) {
            frontRight.setDirection(DcMotor.Direction.REVERSE);
            backRight.setDirection(DcMotor.Direction.REVERSE);
            servo2.setDirection(Servo.Direction.REVERSE);
            // motorThree.setDirection(DcMotor.Direction.REVERSE);
            if (gamepad1.dpad_right) {

                frontRight.setPower(0.7);
                frontLeft.setPower(-0.7);
               backLeft.setPower(0.7);
                backRight.setPower(-0.7);
            } else if (gamepad1.dpad_left) {
                frontLeft.setPower(0.7);
                frontRight.setPower(-0.7);
                backLeft.setPower(-0.7);
                backRight.setPower(0.7);
            } else if (gamepad2.right_bumper) {
                servo3.setPosition(1);
            } else if (gamepad2.left_bumper) {
                servo3.setPosition(-1);
            } else if (gamepad2.b) {
                servo1.setPosition(.2);
                servo2.setPosition(.2);
         //   } else if (gamepad2.a) {



        } else {
                float forward = -this.gamepad1.left_stick_y;

               backLeft.setDirection(DcMotor.Direction.FORWARD);
               frontLeft.setDirection(DcMotor.Direction.FORWARD);
               frontLeft.setPower(forward);
                frontRight.setPower(forward);
              backLeft.setPower(forward);
                backRight.setPower(forward);


                float fowardSideways = this.gamepad1.left_stick_x;
                float reverseSideways = -this.gamepad1.left_stick_x;
               frontLeft.setPower(reverseSideways);
               backLeft.setPower(reverseSideways);
                frontRight.setPower(fowardSideways);
               backRight.setPower(fowardSideways);
                float turn = -this.gamepad1.right_stick_y;
                float rTurn = this.gamepad1.right_stick_y;
               frontLeft.setPower(turn);
               backLeft.setPower(rTurn);
                frontRight.setPower(rTurn);
               backRight.setPower(turn);
               armMotor.setPower(0);
               servo2.setPosition(0);
               servo1.setPosition(0);
               servo3.setPosition(0);

            }
        }while (opModeIsActive()) {
            telemetry.addData("Status", "Running");
            telemetry.update();


        }
    }
}