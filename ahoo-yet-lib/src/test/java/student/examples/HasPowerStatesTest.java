package student.examples;


import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import student.examples.devices.HasPowerStates;
import student.examples.devices.PowerState;
import student.examples.devices.VacuumCleaner;

public class HasPowerStatesTest {
    private HasPowerStates hasPowerStates;

    @BeforeClass(alwaysRun = true)
    public void setup() {
        hasPowerStates = new VacuumCleaner(1, "Atom");
    }

    @Test(groups = {"unit"})
    public void testSwitchOn() {
        hasPowerStates.switchOn();
        Assert.assertTrue(hasPowerStates.isOn());
    }

    @Test(groups = {"unit"})
    public void testSwitchOff() {
        hasPowerStates.switchOff();
        Assert.assertFalse(hasPowerStates.isOn());
    }
}
