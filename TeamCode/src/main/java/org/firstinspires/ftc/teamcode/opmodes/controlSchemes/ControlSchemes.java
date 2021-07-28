package org.firstinspires.ftc.teamcode.opmodes.controlSchemes;

import com.arcrobotics.ftclib.gamepad.ButtonReader;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;

public class ControlSchemes {

    public enum ControlScheme {
        TestControlScheme,
        ControlScheme2
    }

    private GamepadEx gamepadEx1;
    private GamepadEx gamepadEx2;

    public ButtonReader testButton;
    public ButtonReader testButton2;

    public ControlSchemes(GamepadEx gamepadEx1, GamepadEx gamepadEx2, ControlScheme controlScheme) {
        this.gamepadEx1 = gamepadEx1;
        this.gamepadEx2 = gamepadEx2;

        switch (controlScheme) {
            case TestControlScheme:
                testControlScheme();

            case ControlScheme2:
                controlScheme2();

            default:
                testControlScheme();
        }
    }

    public void testControlScheme() {
        testButton = new ButtonReader(
                gamepadEx1, GamepadKeys.Button.A
        );

        testButton2 = new ButtonReader(
                gamepadEx1, GamepadKeys.Button.B
        );
    }

    public void controlScheme2() {
        testButton = new ButtonReader(
                gamepadEx1, GamepadKeys.Button.A
        );

        testButton2 = new ButtonReader(
                gamepadEx1, GamepadKeys.Button.B
        );
    }

    public void readButtons() {
        gamepadEx1.readButtons();
        gamepadEx2.readButtons();
    }
}
