package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.util.ElapsedTime;

public class PIDController {

    private double Kp = 0;
    private double Ki = 0;
    private double Kd = 0;

    private double error;

    private double integralSum = 0;
    private double lastError = 0;
    private double derivative;

    private ElapsedTime timer;

    private double out;

    /**
     * construct PID controller
     * // @param Kp Proportional coefficient
     * // @param Ki Integral coefficient
     * // @param Kd Derivative coefficient
     */
    public PIDController(double kp, double ki, double kd) {
        timer = new ElapsedTime();
        Kp = kp;
        Ki = ki;
        Kd = kd;
    }

    /**
     * update the PID controller output
     *
     * @param target where we would like to be, also called the reference
     * @param state  where we currently are, I.E. motor position
     * @return the command to our motor, I.E. motor power
     */
    public double update(double target, double state) {

        error = target - state;
        derivative = (error - lastError) / timer.seconds();

        // sum of all error over time
        integralSum = integralSum + (error * timer.seconds());

        out = (Kp * error) + (Ki * integralSum) + (Kd * derivative);

        lastError = error;

        return out;
    }
}