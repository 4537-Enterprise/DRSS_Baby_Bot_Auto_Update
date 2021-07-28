package org.firstinspires.ftc.teamcode.opmodes;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.opmodes.controlSchemes.TestControlScheme;
import org.firstinspires.ftc.teamcode.subsystems.drivetrains.straferbot.StraferBot;

/**
 * Created by Antoine on 7/8/2021
 */
public class StraferBotTeleOp extends LinearOpMode {

    StraferBot robot;

    GamepadEx gamepadEx1 = new GamepadEx(gamepad1);
    GamepadEx gamepadEx2 = new GamepadEx(gamepad2);
    TestControlScheme control = new TestControlScheme(gamepadEx1, gamepadEx2);

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new StraferBot(hardwareMap, telemetry);

        waitForStart();

        while(opModeIsActive()) {
            robot.updateDriveTrain(gamepadEx1);
            robot.updateTelemetry();
            robot.checkWarnings();

            if (control.testButton.wasJustPressed()) {
                //do something
            }

            if (control.testButton2.wasJustPressed()) {
                //do something
            }

            control.readButtons();
        }
    }
}
