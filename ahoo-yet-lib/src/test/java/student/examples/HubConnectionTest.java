package student.examples;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HubConnectionTest {
    @BeforeTest(alwaysRun = true)
    public void setup() {}

    @Test(groups = {"integration"})
    public void testConnection() {}

    @AfterTest(alwaysRun = true)
    public void cleanup() {}


}
