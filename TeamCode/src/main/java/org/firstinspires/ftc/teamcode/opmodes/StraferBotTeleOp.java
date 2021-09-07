package org.firstinspires.ftc.teamcode.opmodes;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.opmodes.controlSchemes.ControlSchemes;
import org.firstinspires.ftc.teamcode.subsystems.drivetrains.straferbot.StraferBot;

/**
 * Created by Antoine on 7/8/2021
 */
@Disabled
@TeleOp
public class StraferBotTeleOp extends LinearOpMode {

    StraferBot robot;

    GamepadEx gamepadEx1 = new GamepadEx(gamepad1);
    GamepadEx gamepadEx2 = new GamepadEx(gamepad2);
    ControlSchemes controls = new ControlSchemes(gamepadEx1, gamepadEx2, ControlSchemes.ControlScheme.TestControlScheme);

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new StraferBot(hardwareMap, telemetry);

        waitForStart();

        while(opModeIsActive()) {
            robot.updateDriveTrain(gamepadEx1);
            robot.updateTelemetry();
            robot.checkWarnings();

            if (controls.testButton.wasJustPressed()) {
                //do something
            }

            if (controls.testButton2.wasJustPressed()) {
                //do something
            }

            controls.readButtons();
        }
    }
}
