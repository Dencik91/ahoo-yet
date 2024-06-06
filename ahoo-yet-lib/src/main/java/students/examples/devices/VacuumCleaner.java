package students.examples.devices;

import static students.examples.devices.PowerState.OFF;
import static students.examples.devices.PowerState.ON;

public class VacuumCleaner implements HasPowerOnOff{

    private PowerState powerState;

    public VacuumCleaner() {
        switchOff();
    }

    @Override
    public boolean switchOn() {
        powerState = ON;
        return true;
    }

    @Override
    public boolean switchOff() {
        powerState = OFF;
        return false;
    }

    @Override
    public boolean isOn() {
        return powerState != OFF;
    }
}
