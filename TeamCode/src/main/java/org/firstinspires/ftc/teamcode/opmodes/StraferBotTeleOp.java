package org.firstinspires.ftc.teamcode.opmodes;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.subsystems.drivetrains.straferbot.StraferBot;

/**
 * Created by Antoine on 7/8/2021
 */
public class StraferBotTeleOp extends LinearOpMode {

    StraferBot robot;

    GamepadEx driveTrainController;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new StraferBot(hardwareMap, telemetry);

        driveTrainController = new GamepadEx(gamepad1);

        waitForStart();

        while(opModeIsActive()) {
            robot.updateDriveTrain(driveTrainController);
            robot.updateTelemetry();
            robot.checkWarnings();
        }
    }
}
