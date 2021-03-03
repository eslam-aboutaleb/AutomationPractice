/* **************************************************** */
/* Name: Eslam Ehab Aboutaleb							*/
/* Project: Automationpractice							*/
/* Module/Test suite: Check out							*/
/* Date: 3/3/2020										*/
/* **************************************************** */

package AutomationTask.Practice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class checkOut {
	
	private ChromeDriver driver;
	public String BaseUrl = "http://automationpractice.com/index.php";

	private String ValidEmail;
	private String Password;
	
	
	public checkOut(){
		this.Password = "eslamehab";
		this.ValidEmail = "eslmam@yahoo.com";
	}

	@BeforeTest
	public void setup() 
	{
		String ChromePath = System.getProperty("user.dir")+"\\Resources\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver",ChromePath	);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test(priority = 0)
	public void OpenURL() {
		System.out.println("Opening website");
		driver.navigate().to(BaseUrl);
		driver.findElement(By.className("login")).click();
		driver.findElement(By.id("passwd")).sendKeys(Password);
		driver.findElement(By.id("email")).sendKeys(ValidEmail);
		driver.findElement(By.id("SubmitLogin")).click();
		driver.navigate().to(BaseUrl);
	}
	String Tshirtslink ="http://automationpractice.com/index.php?id_product=1&controller=product";
	String ShoppinCartLink ="http://automationpractice.com/index.php?controller=order&step=1";
	
	
	@Test(priority = 1)
	public void GotoCartEmpty()
	{
		driver.navigate().to( ShoppinCartLink);
		driver.findElement(By.xpath("//*[@id=\"center_column\"]/p"));
	}
	
	@Test(priority = 2)
	public void findatleastshirt()
	{
		driver.navigate().to( Tshirtslink);
	}
	
	@Test (priority = 3)
	public void ClickAddItem()
	{
		WebElement butncontainer= driver.findElement(By.className("box-cart-bottom"));
		WebElement btnID = butncontainer.findElement(By.id("add_to_cart"));
		btnID.findElement(By.name("Submit")).click();
	}
	
	@Test(priority = 4)
	public void GotoShopCart() {
		driver.navigate().to( ShoppinCartLink);
		driver.findElement(By.xpath("//*[@id=\"center_column\"]/form/p/button")).click();
		driver.findElement(By.xpath("//*[@id=\"cgv\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"form\"]/p/button/span/i")).click();
		driver.findElement(By.xpath("//*[@id=\"HOOK_PAYMENT\"]/div[1]/div/p/a")).click();
		driver.findElement(By.xpath("//*[@id=\"cart_navigation\"]/button/span/i")).click();

	}
	
	@AfterTest
	public void ExitDriver()
	{
		driver.quit();
	}
}
