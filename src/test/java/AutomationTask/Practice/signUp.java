/* **************************************************** */
/* Name: Eslam Ehab Aboutaleb							*/
/* Project: Automationpractice							*/
/* Module/Test suite: Sign Up							*/
/* Date: 3/3/2020										*/
/* **************************************************** */

package AutomationTask.Practice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.Assert;

public class signUp {

	ChromeDriver driver;
	public String expected = null;
	public String actual = null;
	public String BaseUrl = "http://automationpractice.com/index.php";
	private String ValidEmail = "eslamehab@yahoo.com";
	private String AlreadyRegisteredEmail="eslaaam@yahoo.com";
	private String InValidEmail = "eslaaam";


	private String FirstName, LastName, Password,
	Date, Month, Year, Company, Address1, City, zip, State,
	Mobile1;

	public String[] email = {"n","","n.com"};
	public Select ActDate,ActMonth,ActYear,ActState;
	public signUp(){
		this.FirstName = "Eslam";
		this.LastName = "Aboutaleb";
		this.Password = "eslamehab";
		this.Date = "28";
		this.Month ="10";
		this.Year = "1996";
		this.Company = "Eslam Company";
		this.Address1 = "Elhosary, Front of Diamond mall";
		this.City = "6th of October city";
		this.zip = "19000";
		this.State = "7";
		this.Mobile1 = "01002044529";
	}

	@BeforeTest(description = "Create chrome driver and implement an implicit delay")
	public void setup() 
	{
		String ChromePath = System.getProperty("user.dir")+"\\Resources\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver",ChromePath	);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test(testName = "Home page is opened without errors")
	public void OpenURL() {
		System.out.println("Opening website");
		driver.navigate().to(BaseUrl);
	}

	@Test(testName = "Login button directs to the authentication page")
	public void openAuthenticationPage(){
		System.out.println("Opening Login page");

		//Open the authentication page contain sign in and sign up form
		driver.findElement(By.className("login")).click();

		/*verify the title of authentication page*/
		expected = "Login - My Store";
		actual = driver.getTitle();
		Assert.assertEquals(actual, expected);
	}
	
	@Test(testName = "Register with invalid email")
	public void regUsingInvalidEmail()
	{
		/* register using invalid email address */
		driver.findElement(By.id("email_create")).sendKeys(InValidEmail);
		driver.findElement(By.id("SubmitCreate")).submit();
	}

	@Test(testName = "Register with registered email")
	public void regUsingRegisteredEmail()
	{
		/* register using registered email address */
		driver.findElement(By.id("email_create")).clear();
		driver.findElement(By.id("email_create")).sendKeys(AlreadyRegisteredEmail);
		driver.findElement(By.id("SubmitCreate")).submit();
	}

	@Test(testName = "Register with valid email")
	public void regUsingValidEmail(){
		/* register using valid email address */
		driver.findElement(By.id("email_create")).clear();
		driver.findElement(By.id("email_create")).sendKeys(ValidEmail);
		driver.findElement(By.id("SubmitCreate")).submit();		
	}
	

	
	@Test(testName = "Register an account with blank data" , priority = 0)
	public void registerWithBlankInput(){
		
		//fill the fields on the registration form using invalid data
		driver.findElement(By.id("id_gender1")).click();
		driver.findElement(By.id("customer_firstname")).clear();
		driver.findElement(By.id("customer_lastname")).clear();
		//driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("passwd")).clear();
		driver.findElement(By.id("newsletter")).click();
		driver.findElement(By.id("optin")).click();
		driver.findElement(By.id("firstname")).clear();
		driver.findElement(By.id("lastname")).clear();
		driver.findElement(By.id("company")).clear();
		driver.findElement(By.id("address1")).clear();
		//driver.findElement(By.id("address2")).clear()
		driver.findElement(By.id("city")).clear();
		driver.findElement(By.id("postcode")).clear();
		ActState = new Select(driver.findElement(By.id("id_state")));
		ActState.selectByValue("");
		driver.findElement(By.id("other")).clear();
		driver.findElement(By.id("phone")).clear();
		driver.findElement(By.id("phone_mobile")).clear();
		
		//submit the registration form and display the error message
		driver.findElement(By.id("submitAccount")).click();
		if(driver.findElement(By.xpath("//*[@id='center_column']/div")).isDisplayed()){
			System.out.println("validation error: "+driver.findElement(By.xpath("//*[@id='center_column']/div")).getText());
		}
		
	}
	
	@Test(testName = "Register an account with valid data" , priority = 1)
	public void registerWithBlank(){
		driver.findElement(By.id("id_gender1")).click();
		driver.findElement(By.id("passwd")).sendKeys(Password);
		driver.findElement(By.id("customer_firstname")).sendKeys(FirstName);
		driver.findElement(By.id("customer_lastname")).sendKeys(LastName);
		Assert.assertEquals(driver.findElement(By.id("email")).getAttribute("value"), ValidEmail);
		ActDate = new Select(driver.findElement(By.id("days")));
		ActMonth = new Select(driver.findElement(By.id("months")));
		ActYear = new Select(driver.findElement(By.id("years")));
		ActState = new Select(driver.findElement(By.id("id_state")));
		ActDate.selectByValue(Date);
		ActMonth.selectByValue(Month);
		ActYear.selectByValue(Year);
		driver.findElement(By.id("company")).sendKeys(Company);
		driver.findElement(By.id("address1")).sendKeys(Address1);
		driver.findElement(By.id("city")).sendKeys(City);
		driver.findElement(By.id("postcode")).sendKeys(zip);
		ActState.selectByValue(State);
		driver.findElement(By.id("phone")).sendKeys(Mobile1);

		driver.findElement(By.id("submitAccount")).click();

	}
	
	@AfterTest(description = "Return to home page")
	public void backToHomepage(){
		driver.navigate().to(BaseUrl);
		driver.quit();
	}
}


