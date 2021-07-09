package org.firstinspires.ftc.teamcode.subsystems.revhub;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.openftc.revextensions2.ExpansionHubEx;

/**
 * Created by Antoine on 7/4/2021
 */
public class RevHub {

    public ExpansionHubEx hub;

    public RevHub(HardwareMap map, String hubName) {
        hub = map.get(ExpansionHubEx.class, hubName);
    }

    public void setLedColor(int r, int g, int b) {
        hub.setLedColor(r, g, b);
    }

    public double getTotalCurrentDraw() {
        return hub.getTotalModuleCurrentDraw(ExpansionHubEx.CurrentDrawUnits.AMPS);
    }

    public double getVoltage5v() {
        return hub.read5vMonitor(ExpansionHubEx.VoltageUnits.VOLTS);
    }

    public double getVoltage12v() {
        return hub.read12vMonitor(ExpansionHubEx.VoltageUnits.VOLTS);
    }

    public double getHubInternalTemp() {
        return hub.getInternalTemperature(ExpansionHubEx.TemperatureUnits.FAHRENHEIT);
    }

    public boolean checkTempWarning() {
        return hub.isModuleOverTemp();
    }
}
