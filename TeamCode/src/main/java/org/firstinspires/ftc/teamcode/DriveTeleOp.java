package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.internal.camera.delegating.DelegatingCaptureSequence;
import org.firstinspires.ftc.teamcode.robot.ArmServos;
import org.firstinspires.ftc.teamcode.robot.Basket;
import org.firstinspires.ftc.teamcode.robot.Movement;
import org.java_websocket.framing.ContinuousFrame;


@TeleOp(name = "DriveTeleOp", group = "Linear OpMode")

public class DriveTeleOp extends LinearOpMode {



    @Override
    public void runOpMode() {



        //  servo2.setDirection(Servo.Direction.REVERSE);

        telemetry.addData("Initialized", "is a win");
        telemetry.update();
        Movement movement = new Movement(this);
        ArmServos armServos = new ArmServos(this);
        Basket basket = new Basket(this);

        movement.init();
        basket.init();
        armServos.init();
        //  armServos.servo3.setPosition(0);
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        while (opModeIsActive()) {
//            telemetry.addData("on", "on");
       //     telemetry.addData("touchSensor", armServos.slideTouch.getState());
//            telemetry.addData("gamepad.2,y", gamepad2.y);
//            telemetry.addData("d-pad_up", gamepad2.dpad_up);
//            telemetry.addData("d-pad_down", gamepad2.dpad_down);
//            telemetry.addData("d-pad_left", gamepad2.dpad_left);
//            telemetry.addData("d-pad_right", gamepad2.dpad_right);
//            telemetry.addData("slide", armServos.slideMotor.getCurrentPosition());
//            telemetry.addData("armMotor", armServos.armMotor.getCurrentPosition());
//            telemetry.addData("armStick", gamepad2.right_stick_y);

            double modifier = 1;
            if (gamepad1.b) {
                modifier = 2;
            }
            double drive = -gamepad1.left_stick_y;
            double turn = gamepad1.left_stick_x;
            double strafe = gamepad1.right_stick_x;
            double MrHand = -gamepad2.left_stick_y;
            movement.backLeft.setPower((drive - strafe + turn) / modifier);
            movement.frontLeft.setPower((drive - strafe - turn) / modifier);
            movement.backRight.setPower((drive + strafe - turn) / modifier);
            movement.frontRight.setPower((drive + strafe + turn) / modifier);
            armServos.armMotor.setPower(gamepad2.right_stick_y);


            //arm Motor
            if (gamepad2.back) {
                movement.backLeft.setPower((drive - strafe + turn) / modifier);
                movement.frontLeft.setPower((drive - strafe - turn) / modifier);
                movement.backRight.setPower((drive + strafe - turn) / modifier);
                movement.frontRight.setPower((drive + strafe + turn) / modifier);
                basket.servo3.setPosition(.75);
                sleep(2000);
                armServos.setArmPosition(1,-50000,0);
               // movement.forwardDistance(1,10000);
                armServos.setArmPosition(1, 1000,1000000000);
//                armServos.armMotor.setPower(-1);
//                sleep(100000000);
            } else {
                armServos.armMotor.setPower(gamepad2.right_stick_y);
            }
            if (gamepad2.dpad_left) {
                movement.backLeft.setPower((drive - strafe + turn) / modifier);
                movement.frontLeft.setPower((drive - strafe - turn) / modifier);
                movement.backRight.setPower((drive + strafe - turn) / modifier);
                movement.frontRight.setPower((drive + strafe + turn) / modifier);
                armServos.closeServoTurn();
                basket.startSlide();
                armServos.setArmPosition(.5, -1555, 1000);



            }

                //servos
                if (gamepad2.right_bumper) {
                    armServos.closeServoTurn();

                }
                if (gamepad2.left_bumper) {
                    armServos.servo2.setPosition(0);
                }

            if (gamepad2.x) {
                //basket
                basket.servoBasketDrop();
            } else {
                basket.servoBasketNormal();
            }

                //Slide Motor
                if (gamepad2.y) {
                    basket.startSlide();
                }


            if (gamepad2.dpad_down) {
                  //  basket.slideTeleOp(.7, -1200);
                    basket.closeSlideTouch();


                }
                if (gamepad2.dpad_up) {
                    basket.slideTeleOp(.7, 1550);


                    //Drive
                }

                armServos.servo1.setPosition(-gamepad2.left_stick_y);

               telemetry.update();
            }
        }
    }








