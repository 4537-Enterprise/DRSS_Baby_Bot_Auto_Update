package org.firstinspires.ftc.teamcode.subsystems.drivetrains;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.Arrays;

/**
 * Created by Antoine on 7/4/2021
 */
public class MecanumDrive {

    DcMotorEx[] motors = new DcMotorEx[4];
    double[] motorPowers = new double[4];

    Telemetry telemetry;
    TelemetryPacket packet = new TelemetryPacket();
    FtcDashboard dashboard = FtcDashboard.getInstance();

    public MecanumDrive(HardwareMap map, Telemetry telemetry) {
        this.telemetry = telemetry;

        motors[0] = map.get(DcMotorEx.class, "FR");
        motors[1] = map.get(DcMotorEx.class, "FL");
        motors[2] = map.get(DcMotorEx.class, "BR");
        motors[3] = map.get(DcMotorEx.class, "BL");

        motors[0].setDirection(DcMotorSimple.Direction.FORWARD);
        motors[1].setDirection(DcMotorSimple.Direction.REVERSE);
        motors[2].setDirection(DcMotorSimple.Direction.FORWARD);
        motors[3].setDirection(DcMotorSimple.Direction.REVERSE);

        setModeRunWithoutEncoder();
        setZeroPowerBehavior();

        telemetry.addData("DriveTrain", "Initialized");
        telemetry.update();

        packet.put("DriveTrain", "Initialized");
        dashboard.sendTelemetryPacket(packet);
    }

    public void setModeRunWithoutEncoder() {
        for (DcMotorEx motor : motors) {
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }

    public void setZeroPowerBehavior() {
        for (DcMotorEx motor : motors) {
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
    }

    public void setMotorPowers(double FR, double FL, double BR, double BL) {
        motorPowers[0] = FR;
        motorPowers[1] = FL;
        motorPowers[2] = BR;
        motorPowers[3] = BL;

        runMotors();
    }

    public void setMotorPowers(double x, double y, double r) {
        double[] sortedMotorPowers = new double[4];

        motorPowers[0] = y + x + r;
        motorPowers[1] = y - x - r;
        motorPowers[2] = y - x + r;
        motorPowers[3] = y + x + r;

        sortedMotorPowers[0] = Math.abs(motorPowers[0]);
        sortedMotorPowers[1] = Math.abs(motorPowers[1]);
        sortedMotorPowers[2] = Math.abs(motorPowers[2]);
        sortedMotorPowers[3] = Math.abs(motorPowers[3]);
        Arrays.sort(sortedMotorPowers);

        if (Math.abs(sortedMotorPowers[3]) > 1) {
            motorPowers[0] /= sortedMotorPowers[3];
            motorPowers[1] /= sortedMotorPowers[3];
            motorPowers[2] /= sortedMotorPowers[3];
            motorPowers[3] /= sortedMotorPowers[3];
        }

        runMotors();
    }

    public void stopMotors() {
        motorPowers[0] = 0;
        motorPowers[1] = 0;
        motorPowers[2] = 0;
        motorPowers[3] = 0;

        runMotors();
    }

    private void runMotors() {
        motors[0].setPower(motorPowers[0]);
        motors[1].setPower(motorPowers[1]);
        motors[2].setPower(motorPowers[2]);
        motors[3].setPower(motorPowers[3]);
    }
}
