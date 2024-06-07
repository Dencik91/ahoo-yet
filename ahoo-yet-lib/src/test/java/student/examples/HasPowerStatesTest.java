package student.examples;

import junit.framework.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import student.examples.devices.HasPowerStates;
import student.examples.devices.PowerState;
import student.examples.devices.VacuumCleaner;

public class HasPowerStatesTest {
    private HasPowerStates hasPowerStates;

    @BeforeEach
    public void setup() {
        hasPowerStates = new VacuumCleaner(1, "Atom");
    }

    @Test
    public void testSwitchOn() {
        hasPowerStates.switchOn();
        Assert.assertTrue(hasPowerStates.isOn());
    }

    @Test
    public void testSwitchOff() {
        hasPowerStates.switchOff();
        Assert.assertFalse(hasPowerStates.isOn());
    }
}
