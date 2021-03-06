package com.neueda.etiqet.selenium.browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.concurrent.TimeUnit;

@XmlRootElement(name = "Edge")
public class Edge extends Browser {

        @XmlAttribute
        private String name;

        @XmlAttribute(name="driver_path")
        private String driverPath;

        @XmlElement
        private boolean headless;

        @XmlElement
        private boolean closeOnExit;

        @XmlElement(name = "screenshot_on_exit")
        private boolean screenShotOnExitEnabled;

        @XmlElement(name = "implicit_wait")
        private long implicitWait;

        @XmlElement(name = "page_load_timeout", defaultValue = "-1")
        private long pageLoadTimeout;

        @XmlElement(name = "script_timeout")
        private long scriptTimeout;

        private WebDriver driver;

        @XmlElement(name = "Options")
        private Options options;

        public Edge(){
            options = new EdgeSettings();
            pageLoadTimeout = -1;
        }

        @Override
        public void setupDriver() {

            WebDriverManager.edgedriver().setup();

            logger.info("Launching browser: " + name);

            driver = new EdgeDriver();

            driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
            if (pageLoadTimeout >= 0) {
                driver.manage().timeouts().pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);
            }
            driver.manage().timeouts().setScriptTimeout(scriptTimeout, TimeUnit.SECONDS);
        }

        @Override
        public void close() {
            if (screenShotOnExitEnabled) {
                takeScreenshot();
            }
            logger.info("Closing browser: " + name);
            driver.close();
            logger.info("Browser closed: " + name);
        }

        @Override
        public boolean isHeadless() {
            return headless;
        }

        @Override
        public boolean shouldCloseOnExit() {
            return closeOnExit;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getDriverPath() {
            return driverPath;
        }

        @Override
        public WebDriver getDriver() {
            return driver;
        }

        @Override
        public boolean getScreenShotOnExitEnabled() {
            return screenShotOnExitEnabled;
        }

        @Override
        public long getImplicitWait() {
            return implicitWait;
        }

        @Override
        public long getPageTimeout() {
            return pageLoadTimeout;
        }

        @Override
        public long getScriptTimeout() {
            return scriptTimeout;
        }
}

