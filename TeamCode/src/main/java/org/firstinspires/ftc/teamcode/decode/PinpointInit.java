package org.firstinspires.ftc.teamcode.decode;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.robot.Motors;
import org.firstinspires.ftc.teamcode.robot.Movement;
import org.firstinspires.ftc.teamcode.robot.Odometry;
import org.firstinspires.ftc.teamcode.robot.Sensors;
import org.firstinspires.ftc.teamcode.robot.Servos;

@TeleOp(name = "pinpointInit", group = "")


public class PinpointInit extends LinearOpMode {
    public Motors motors;
    public Movement movement;
    public Servos servos;
    public Sensors sensors;


    GoBildaPinpointDriver pinpoint;

    @Override
    public void runOpMode() {
        Movement movement = new Movement(this);
        Odometry odometry = new Odometry(this);
        // Motors motors = new Motors(this);
        //Servos servos = new Servos(this);
        //Sensors sensors = new Sensors(this);
        movement.init();
        odometry.init();
        //servos.init();
        //motors.init();
        //sensors.init();


        // Get a reference to the sensor

        pinpoint = hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint");

        // Configure the sensor
        odometry.configurePinpoint();

        // Set the location of the robot - this should be the place you are starting the robot from
        odometry.resetToZero();
        waitForStart();
        while (opModeIsActive()) {

            telemetry.addLine("Push your robot around to see it track");
            telemetry.addLine("Press A to reset the position");
            if (gamepad1.a) {
                // You could use readings from April Tags here to give a new known position to the pinpoint
                odometry.setPosition(0, 0, 0);
            }
            pinpoint.update();
            Pose2D pose2D = pinpoint.getPosition();

            telemetry.addData("X coordinate (IN)", pose2D.getX(DistanceUnit.INCH));
            telemetry.addData("Y coordinate (IN)", pose2D.getY(DistanceUnit.INCH));
            telemetry.addData("Heading angle (DEGREES)", pose2D.getHeading(AngleUnit.DEGREES));
            telemetry.update();
        }
    }
}

