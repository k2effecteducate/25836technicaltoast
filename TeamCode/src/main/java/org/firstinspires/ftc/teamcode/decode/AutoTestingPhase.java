package org.firstinspires.ftc.teamcode.decode;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.robot.Limelight;
import org.firstinspires.ftc.teamcode.robot.Motors;
import org.firstinspires.ftc.teamcode.robot.Movement;
import org.firstinspires.ftc.teamcode.robot.Odometry;

@Autonomous
public class AutoTestingPhase extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    decode decode = new decode(this);
    Movement movement = new Movement(this);
    Motors motors = new Motors(this);
    Odometry odometry = new Odometry(this);
    private Limelight3A limelight;
    private IMU imu;


    @Override
    public void runOpMode() {
        Follower follower = Constants.createFollower(hardwareMap);
        limelight.start();
        limelight.pipelineSwitch(0);
        imu = hardwareMap.get(IMU.class, "imu");
        RevHubOrientationOnRobot revHubOrientationOnRobot = new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD);
        imu.initialize(new IMU.Parameters(revHubOrientationOnRobot));

        odometry.init();
        decode.init();
        movement.init();
        motors.init();
        runtime.reset();
        waitForStart();
        follower.activateAllPIDFs();
        int firstForward = -12;
        int secondForward = -40;
        int thirdForward = 20;

        YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
        limelight.updateRobotOrientation(orientation.getYaw());
        LLResult llResult = limelight.getLatestResult();
        if (llResult != null && llResult.isValid()) {
            Pose3D botPose = llResult.getBotpose_MT2();
            telemetry.addData("Tx", llResult.getTx());
            telemetry.addData("Ty,", llResult.getTy());
            telemetry.addData("Ta", llResult.getTa());

        }
        if (llResult != null) {
            movement.forward(0, 0);
        } else {
            movement.forward(.5, 20);
        }


        telemetry.update();

//        movement.odemetryForward(0, 0, firstForward, 0, 0);
//        decode.autoShoot();
//        sleep(2000);
//        decode.everythingAutoShoot();
//        sleep(100);
//        motors.stopMotors();
//        sleep(200);
//        decode.autoShoot();
//        sleep(1500);
//        decode.servo2.setPower(.7);
//        sleep(200);
//        decode.everythingAutoShoot();
//        sleep(2000);
//        movement.odemetryForward(firstForward, 0, secondForward, 0, 0);
//        sleep(100);
//        movement.turnLeft(-.7, 400);
//        decode.collection();
//        movement.forward(.7, 700);
//        decode.collection();
//        sleep(500);

//don't un-comment below
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


