package org.firstinspires.ftc.teamcode.subsystems.pidcontroller;

/**
 * Created by Antoine on 06/18/21
 */
public class PID {

    //PID Constants
        double kp; //Proportional
        double ki; //Integral
        double kd; //Derivative

    //PID Variables
        long currentTime, previousTime;
        double elapsedTime;
        double setPoint;
        double error, previousError;
        double cumulativeError, errorRate;

    /**
     * Set all PID Constants
     * @param kp the proportional gain PID Constant
     * @param ki the integral gain PID Constant
     * @param kd the derivative gain PID Constant
     */
    public void SetConstants(double kp, double ki, double kd) {
        //Each constant has to be individually tuned for each application
        this.kp = kp;
        this.ki = ki;
        this.kd = kd;
    }

    /**
     * Set setPoint for PID Compute
     * @param setPoint the wanted value the PID controller will iterate to
     */
    public void SetPoint(double setPoint) {
        this.setPoint = setPoint;
    }

    /**
     * Compute output value from input and PID Constants. Needs to be looped through
     * @param input the sensor reading that will tell the PID controller how close we are to the set point
     */
    public double Compute(double input) {
        currentTime = System.currentTimeMillis(); //Get current time
        elapsedTime = currentTime - previousTime; //Calculate elapsed time between previous iteration and current iteration

        error = setPoint - input; //Calculate error between current value and wanted value
        cumulativeError += error * elapsedTime; //Add error to integral. Allows for more power to compensate over time
        errorRate = (error - previousError)/elapsedTime; //Calculate rate of error

        double output = (kp * error) + (ki * cumulativeError) + (kd * errorRate); //Calculate output value from PID Constants and PID Variables calculated above

        previousError = error; //Set current error as previous error for next iteration
        previousTime = currentTime; //Set current time as previous time for next iteration

        return output; //Return our calculated output
    }

    /**
     * Reset all PID Variables for next Compute run
     */
    public void Reset() {
        previousTime = 0;
        elapsedTime = 0;
        setPoint = 0;
        error = 0;
        previousError = 0;
        cumulativeError = 0;
        errorRate = 0;
    }
}
