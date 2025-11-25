package org.firstinspires.ftc.teamcode.decode;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.robot.Motors;
import org.firstinspires.ftc.teamcode.robot.Movement;

@Autonomous
public class BigZoneBlue extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    decode decode = new decode(this);
    Movement movement = new Movement(this);
    Motors motors = new Motors(this);


    @Override
    public void runOpMode() {
        Follower follower = Constants.createFollower(hardwareMap);


        decode.init();
        movement.init();
        motors.init();
        runtime.reset();
        waitForStart();
        follower.activateAllPIDFs();
        int firstForward = -12;
        int secondForward = -20;

        movement.odemetryForward(0, 0, firstForward, 0, 0);
        decode.autoShoot();
        sleep(3000);
        decode.everythingAutoShoot();
        sleep(500);
        motors.stopMotors();
        sleep(200);
        decode.autoShoot();
        sleep(1500);
        decode.servo2.setPower(.7);
        sleep(200);
        decode.everythingAutoShoot();
        sleep(2000);
        decode.motor1.setPower(0);
        movement.odemetryForward(firstForward, 0, secondForward, 0, 0);
        sleep(100);
        movement.turnLeft(-.4, 300);
        decode.collection();
        decode.motor2.setPower(-.9);
        sleep(100);
        movement.forward(.4, 600);
        decode.collection();
        sleep(2000);


//        movement.odemetryStrafe(-40, 0, -40, 20, 45);
//        sleep(100);
//        decode.collection();
//        sleep(2000);
//        movement.odemetryForward(-40, 0, -20, 20, 45);
//        sleep(100);
//        decode.collection();
//        sleep(2000);


    }
}


