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
                servos.servo4.setPosition(.7);
            } else {
                servos.servo4.setPosition(0);
            }

            switch (robotState) {

                case SHOOT:

                    decode.resetDistanceCounter();
                    decode.teleOpShoot();
                    servos.stopServos();

                    if (gamepad1.a) {

                        robotState = RobotState.COLLECTION_A;
                    }
                    if (gamepad1.b) {

                        robotState = RobotState.DISABLE;
                    }


                    break;


                case COLLECTION_A:
                    decode.isArtifactThere();
                    motors.stopMotors();

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
                case STUCK:
                    if (gamepad1.y) {
                        robotState = RobotState.SHOOT;
                    }
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

