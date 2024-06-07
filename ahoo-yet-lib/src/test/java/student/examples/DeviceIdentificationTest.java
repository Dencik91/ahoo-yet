package student.examples;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import student.examples.devices.Device;
import student.examples.devices.VacuumCleaner;

public class DeviceIdentificationTest {
    Device device;

    @BeforeClass(alwaysRun = true)
    public void setup() {
        device = new VacuumCleaner();
    }

    @Test(groups = {"unit"})
    public void testNegativeID() {
        int beforeID = device.getId();
        device.setId(-1);
        Assert.assertEquals(device.getId(), beforeID);
    }
}
