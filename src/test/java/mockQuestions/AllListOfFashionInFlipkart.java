package mockQuestions;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class AllListOfFashionInFlipkart 
{
	@Test
	public void ListOfFashion() throws InterruptedException
	{
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		
		driver.get("https://www.flipkart.com/");
		
		int retry = 0;
//		while(true) {
//			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
//			try {
//				wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//span[text() = '✕']"))));
//				Reporter.log("Popup got closed now", true);
//				break;
//			}
//			catch(Exception e) {
//				if(retry < 2) {
//					Reporter.log("retry: " + (retry + 1), true);
//					retry++;
//				}
//				else
//					break;
//			}
//		}
		

		retry = 0;
		while(true)
		{
			List<WebElement> closePopup = driver.findElements(By.xpath("//span[text() = '✕']"));
			if(!closePopup.isEmpty()) {
				closePopup.get(0).click();
			}
			else if(retry < 2) 
			{
				Reporter.log("retry: " + (retry + 1), true);
				retry++;
			}
			else
				break;
		}
		
		
	//	Fashion Tab
		WebElement fashionTab = driver.findElement(By.xpath("//span[text() = 'Fashion']"));
		Actions action = new Actions(driver);
		action.moveToElement(fashionTab).perform();
		
	//	List of Fashion Wear
		List<WebElement> fashionList = fashionTab.findElements(By.xpath("//a[contains(@class,'_1BJVlg')]"));
		System.out.println("List in Fashion\n================");
		for(WebElement name : fashionList)
		{
			System.out.println("\n" + name.getText() + " ----->");
			action.moveToElement(name).perform();
			
		//	List of sub Category of Fashion wear
			List<WebElement> nameSubList = driver.findElements(By.xpath("//a[@class = '_3490ry']"));
			for(WebElement subName : nameSubList)
				System.out.println("\t" + subName.getText());
		}
	}
}
