package com.ProQuest.selenium.Test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.ProQuest.selenium.Utils.CommonUtils;
import com.ProQuest.selenium.Utils.Constants;

public class ProQuestCodeTest {

	WebDriver driver;

	@BeforeMethod
	@BeforeTest
	public void setUp() {
		// Setting path variable for chrome driver. If path is set already in Environment variable no need of the below line
		System.setProperty("webdriver.chrome.driver",
				"C:\\Aravind\\Learning\\Selenium\\Softwares\\webdrivers\\chromedriver.exe");
		
		// Creating an instance for chrome driver with necessary timeouts
		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	
	@AfterMethod
	@AfterTest
	public void tearDown() {
		// Terminate the driver instance
		driver.quit();
	}

	@Test
	public void googlesearchProquestTest() throws Exception {
		
		// Load a new web page with google URL
		driver.get("https://www.google.com/");
		
		// Entering the ProQuest text in search box and then click search button
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys("ProQuest");
		driver.findElement(By.xpath("//input[@value='Google Search']")).click();

		// Identifying titles in the search results
		String xPathForTitles = "//div[@id='search']/descendant::div[@class='r']/descendant::h3[@class='LC20lb']";
		List<WebElement> titlesList = driver.findElements(By
				.xpath(xPathForTitles));
		
		// Looping through the searched titles and appending it to string
		int i = 1;
		StringBuilder titles = new StringBuilder();
		for (WebElement title : titlesList) {
			String titleAttribute = title.getAttribute("innerHTML");		
			System.out.println(i + ") " + titleAttribute);
			titles.append(i + ") " + titleAttribute + " \n");
			i++;
		}
		
		// Writing Titles to a file
		CommonUtils.fileWrite(titles.toString(),
				Constants.SEARCH_TITLES_FILENAME);
	}
	
	@Test
	public void topNavSearch() throws Exception{
		// Navigate directly to the website URL 
		driver.get("http://www.proquest.com/");
		
 		/* Or can navigate to the search results and click the first link 
		List<WebElement> sitenames = driver.findElements(By
				.xpath("//div[@id='search']/descendant::div[@class='r']/descendant::h3[@class='LC20lb']"));
		sitenames.get(0).click();
		*/ 
		
		// Wait added to load popup frame and switch to that frame
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.switchTo().frame(0);

		// Completing actions for cookies
		driver.findElement(
				By.xpath("//div[@class='mainContent']/descendant::a[text()='Agree and Proceed']"))
				.click();
		driver.findElement(By.id("gwt-debug-close_id")).click();
		
		// Switch back control to Parent frame
		driver.switchTo().defaultContent();

		// Identify & Click on top nav search button
		WebElement topNavSearchButtonElement = driver
				.findElement(By
						.xpath("//li[contains(@class,'hidden-xs')]//a[contains(@class,'dropdown-toggle')]"));
		topNavSearchButtonElement.click();
		
		// Submitting form with 'QA' as search text
		driver.findElement(
				By.xpath("//form[@id='search-form']//input[contains(@name,'searchKeyword')]"))
				.sendKeys("QA");
		driver.findElement(By.xpath("//form[@id='search-form']")).submit();
		
		// Capture screenshot for QA search result
		CommonUtils.captureScreenshot(driver, Constants.SCREENSHOT_FILENAME);

	}
}
