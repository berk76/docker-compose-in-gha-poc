package cz.poc;

import java.io.IOException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class MainTest {

    @Test
    @DisplayName("Selenium")
    public void testRunTests() throws IOException, SvjisSeleniumException {
        Main.runTests();
    }
}
