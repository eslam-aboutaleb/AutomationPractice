/* **************************************************** */
/* Name: Eslam Ehab Aboutaleb							*/
/* Project: Automationpractice							*/
/* Module/Test suite: Login								*/
/* Date: 3/3/2020										*/
/* **************************************************** */

package AutomationTask.Practice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Login {

	private ChromeDriver driver;
	public String expected = null;
	public String actual = null;
	public String BaseUrl = "http://automationpractice.com/index.php";
	private String InvalidPassword;	
	private String ValidEmail;
	private String Password;
	private String UnRegisteredEmail;
	
	
	public Login(){
		this.Password = "eslamehab";
		this.InvalidPassword="WrongPassword";
		this.UnRegisteredEmail="eslamaboutaleb@gmail.com";
		this.ValidEmail = "eslmam@yahoo.com";
	}
	
	@BeforeTest
	public void setup() 
	{
		String ChromePath = System.getProperty("user.dir")+"\\Resources\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver",ChromePath	);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test(priority = 0)
	public void OpenURL() {
		System.out.println("Opening website");
		driver.navigate().to(BaseUrl);
	}

	@Test(testName = "Open authentication page without errors", priority = 1)
	public void openAuthenticationPage(){
		System.out.println("Opening Login page");

		//Open the authentication page contain sign in and sign up form
		driver.findElement(By.className("login")).click();

		//verify the title of authentication page
		expected = "Login - My Store";
		actual = driver.getTitle();
		Assert.assertEquals(actual, expected);
	}
	
	@Test(testName = "Login with unregistered email",priority = 2)
	public void loginUsingUnregisteredEmail(){
		
		//fill the password field
		driver.findElement(By.cssSelector("input#passwd")).sendKeys(Password);
		
		//fill the email field using unregistered email
		driver.findElement(By.id("email")).sendKeys(UnRegisteredEmail);
		
		//submit the form and display the error message
		driver.findElement(By.id("SubmitLogin")).click();	
		if(driver.findElement(By.xpath("//*[@class='alert alert-danger']/ol/li")).isDisplayed()){
			System.out.println("Error: "+driver.findElement(By.xpath("//*[@class='alert alert-danger']/ol/li")).getText());
		}
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("passwd")).clear();
	}
	
	@Test(testName = "Login using correct email and invalid password", priority=3)
	public void loginUsingInvalidPassword(){
		//navigate to user authentication page
		driver.findElement(By.className("login")).click();
		
		//fill the password field
		driver.findElement(By.id("passwd")).sendKeys(InvalidPassword);
		
		//fill the email field using registered email
		driver.findElement(By.id("email")).sendKeys(ValidEmail);
		
		//submit the form and display the error message
		driver.findElement(By.id("SubmitLogin")).click();
		if(driver.findElement(By.xpath("//*[@class='alert alert-danger']/ol/li")).isDisplayed()){
			System.out.println("Error: "+driver.findElement(By.xpath("//*[@class='alert alert-danger']/ol/li")).getText());
		}
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("passwd")).clear();		
	}
	
	@Test(testName = "Check reset password functionality with registered email", priority=4)
	public void resetPassword(){
		//navigate to user authentication page
		driver.findElement(By.className("login")).click();
		
		//reset password
		driver.findElement(By.linkText("Forgot your password?")).click();
		driver.findElement(By.id("email")).sendKeys(ValidEmail);
		driver.findElement(By.id("form_forgotpassword")).submit();		
	}
	
	@Test(testName = "Check reset password functionality with unregistered email", priority=5)
	public void resetPasswordInvalid(){
		//navigate to user authentication page
		driver.findElement(By.className("login")).click();
		
		//reset password
		driver.findElement(By.linkText("Forgot your password?")).click();
		driver.findElement(By.id("email")).sendKeys(UnRegisteredEmail);
		driver.findElement(By.id("form_forgotpassword")).submit();		
	}
	
	@Test(testName = "Login with valid email and password",priority=6)
	public void loginUsingValidData(){	
		//navigate to user authentication page
		driver.findElement(By.className("login")).click();
		
		//login using valid data
		driver.findElement(By.id("passwd")).sendKeys(Password);
		driver.findElement(By.id("email")).sendKeys(ValidEmail);
		driver.findElement(By.id("SubmitLogin")).click();		
	}
	
	@AfterTest
	public void ExitDriver()
	{
		driver.quit();
	}

}
