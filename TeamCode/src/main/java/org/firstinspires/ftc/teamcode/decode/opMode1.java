package org.firstinspires.ftc.teamcode.decode;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

//import org.firstinspires.ftc.teamcode.robot.Basket;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.robot.DriveToPoint;
import org.firstinspires.ftc.teamcode.robot.Motors;
import org.firstinspires.ftc.teamcode.robot.Movement;
import org.firstinspires.ftc.teamcode.robot.Odometry;
import org.firstinspires.ftc.teamcode.robot.Sensors;
import org.firstinspires.ftc.teamcode.robot.Servos;


@TeleOp(name = "forward", group = "Linear OpMode")

public class opMode1 extends LinearOpMode {

//    public enum RobotState {
//        COLLECTION_Y, ARM_UP, INTAKE_DUMP_LEFT, TURN, SLIDE_IN_A, SLIDE_OUT_Y2, INTAKE_OUT_RIGHT, INTAKE_RESET, SPIT_OUT_B, DISABLE, COLLECT_START
//    }

    // opMode1.RobotState robotState = RobotState.TURN;

    @Override
    public void runOpMode() {
        telemetry.addData("Initialized", "Press Start");
        telemetry.update();
        Movement movement = new Movement(this);
        DriveToPoint nav = new DriveToPoint(this);

        // Motors motors = new Motors(this);
        //Servos servos = new Servos(this);
        //Sensors sensors = new Sensors(this);
        movement.init();
        //servos.init();
        //motors.init();
        //sensors.init();

        Odometry odometry = new Odometry(this);
        odometry.init();


        // Wait for the game to start (driver presses PLAY)

        // Configure the sensor
        odometry.configurePinpoint();

        // Set the location of the robot - this should be the place you are starting the robot from
        odometry.pinpoint.setPosition(new Pose2D(DistanceUnit.INCH, 0, 0, AngleUnit.DEGREES, 0));
        waitForStart();
        while (opModeIsActive()) {
            // telemetry.addData("servo2", servos.servo1.getPosition());
            // telemetry.addData("state", robotState);


            if (gamepad1.a) {
               
            }
            odometry.pinpoint.update();
            Pose2D pose2D = odometry.pinpoint.getPosition();
            movement.teleOpControls();
            telemetry.addData("X coordinate (IN)", pose2D.getX(DistanceUnit.INCH));
            telemetry.addData("Y coordinate (IN)", pose2D.getY(DistanceUnit.INCH));
            telemetry.addData("Heading angle (DEGREES)", pose2D.getHeading(AngleUnit.DEGREES));


        }
        telemetry.update();
    }
}



