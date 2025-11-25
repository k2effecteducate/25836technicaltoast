package org.firstinspires.ftc.teamcode.decode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robot.Motors;
import org.firstinspires.ftc.teamcode.robot.Movement;
import org.firstinspires.ftc.teamcode.robot.Sensors;
import org.firstinspires.ftc.teamcode.robot.Servos;


@TeleOp(name = "Competition2 ", group = "Linear OpMode")

public class Competition2 extends LinearOpMode {

    public enum RobotState {
        COLLECTION_A, STUCK, SHOOT, SHOOT_REST, SPIT_OUT_B, DISABLE, LINE_UP
    }

    RobotState robotState = RobotState.DISABLE;


    @Override
    public void runOpMode() {
        telemetry.addData("Initialized", "Press Start");
        telemetry.update();
        decode decode = new decode(this);
        Movement movement = new Movement(this);
        Motors motors = new Motors(this);
        Servos servos = new Servos(this);
        movement.init();
        servos.init();
        motors.init();
        decode.init();


        waitForStart();
        while (opModeIsActive()) {
            telemetry.addData("state", robotState);
            movement.teleOpControls();
//            if (gamepad1.left_bumper) {
//                decode.resetOdometry();
//                telemetry.addData("resetOdometry", "reset");
//            }
            if (gamepad1.x) {
                decode.motor2.setPower(-.95);
                servos.servo2.setPower(1);
            } else {
                decode.motor2.setPower(0);
            }
            if (gamepad1.dpad_up) {

                decode.motor2.setPower(.9);
            } else {
                decode.motor2.setPower(0);
            }


            switch (robotState) {

                case SHOOT:

                    decode.resetDistanceCounter();
                    decode.teleOpShoot();
                    decode.shootPusher();
                    decode.servo2.setPower(0);


                    if (gamepad1.a) {

                        robotState = RobotState.COLLECTION_A;
                    }
                    if (gamepad1.b) {

                        robotState = RobotState.DISABLE;
                    }
                    if (gamepad1.dpad_left) {
                        robotState = RobotState.SPIT_OUT_B;
                    }


                    break;


                case COLLECTION_A:
                    decode.motor1.setPower(0);
                    decode.collection();
                    motors.stopMotors();
                    decode.isArtifactThere();

//                    if (sensors.isObjectDetected()) {
//                        sleep(100);
//                        robotState = RobotState.SHOOT;
//                    }

                    if (gamepad1.y) {
                        robotState = RobotState.SHOOT;
                    }
                    if (gamepad1.b) {

                        robotState = RobotState.DISABLE;
                    }


                    if (gamepad1.dpad_left) {
                        robotState = RobotState.SPIT_OUT_B;
                    }
                    telemetry.update();

                    break;

                case SHOOT_REST:
                    decode.resetDistanceCounter();
                    decode.ShootStop();
                    if (gamepad1.y) {
                        robotState = RobotState.SHOOT;
                    }
                    if (gamepad1.a) {
                        robotState = RobotState.COLLECTION_A;
                    }
                    break;
                case SPIT_OUT_B:
                    decode.resetDistanceCounter();
                    decode.spitOutCollection();
                    motors.stopMotors();
                    decode.motor2.setPower(0);
                    if (gamepad1.y) {
                        robotState = RobotState.SHOOT;
                    }
                    if (gamepad1.a) {
                        robotState = RobotState.COLLECTION_A;
                    }


                    if (gamepad1.b) {
                        robotState = RobotState.DISABLE;
                    }

                    break;
                case DISABLE:
                    decode.resetDistanceCounter();
                    motors.stopMotors();
                    servos.stopServos();
                    decode.motor1.setPower(0);


                    if (gamepad1.a) {
                        robotState = RobotState.COLLECTION_A;
                    }
                    if (gamepad1.y) {
                        robotState = RobotState.SHOOT;
                    }
                    if (gamepad1.dpad_left) {
                        robotState = RobotState.SPIT_OUT_B;
                    }
//                    if (gamepad1.start) {
//                        robotState = RobotState.LINE_UP;
//                    }
                    break;
//                case LINE_UP:
//                    decode.resetDistanceCounter();
//                    decode.lineUp();
//                    if (gamepad1.a) {
//                        robotState = RobotState.COLLECTION_A;
//                    }
//                    if (gamepad1.y) {
//                        robotState = RobotState.SHOOT;
//                    }
//
//                    if (gamepad1.b) {
//                        robotState = RobotState.DISABLE;
//                    }
//
//
//                    break;
//            }

            }
            telemetry.update();
        }
    }
}

