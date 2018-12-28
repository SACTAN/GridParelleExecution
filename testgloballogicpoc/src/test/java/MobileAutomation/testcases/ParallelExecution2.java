package MobileAutomation.testcases;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

    public class ParallelExecution2 {

        public static RemoteWebDriver driver;
        WebDriverWait wait;
        public static String appURL = "http://www.google.com";

        @BeforeClass
        @Parameters({ "browser" })
        public void setUp(String browser) throws MalformedURLException {
            if(browser.equalsIgnoreCase("Android")){
                DesiredCapabilities capabilities = new DesiredCapabilities();

                // Name of mobile web browser to automate. ‘Safari’ for iOS and ‘Chrome’
                // or ‘Browser’ for Android
                capabilities.setCapability("browsername", "chrome");

                // The kind of mobile device or emulator to use - iPad Simulator, iPhone
                // Retina 4-inch, Android Emulator, Galaxy S4 etc
                capabilities.setCapability("deviceName", "3551b5500104");

                // Which mobile OS platform to use - iOS, Android, or FirefoxOS
                capabilities.setCapability("platformName", "Android");

                // Java package of the Android app you want to run- Ex:
                // com.example.android.myApp
                capabilities.setCapability("appPackage", "com.android.chrome");

                // Activity name for the Android activity you want to launch from your
                // package
                capabilities.setCapability("appActivity", "com.google.android.apps.chrome.Main");

                // Initialize the driver object with the URL to Appium Server and
                // passing the capabilities
                driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
                wait = new WebDriverWait(driver, 5);
            }
            else {
                System.out.println("*******************");
                driver = Browser.getDriver(browser);
                driver.manage().window().maximize();
            }
        }

        @Test
        public void testGooglePageTitleInChrome() {
            driver.navigate().to("http://www.google.com");
            String strPageTitle = driver.getTitle();
            Assert.assertTrue(strPageTitle.equalsIgnoreCase("Google"), "Page title doesn't match");
        }

        @Test
        public void testSearchGoogle() {
            System.out.println("Opening Google..");
            driver.navigate().to(appURL);
            driver.findElement(By.name("q")).sendKeys("Selenium Easy Grid Tutorials");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.findElement(By.name("btnK")).click();
        }

        @AfterClass
        public void tearDown() {
            if(driver!=null) {
                System.out.println("Closing browser");
                driver.quit();
            }
        }
    }

