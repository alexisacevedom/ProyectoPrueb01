package com.testng.selenium;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CrossBrowserTesting {
	private WebDriver driver;
	By cajaBusqueda = By.name("q");
	By seleniumLinkLocator = By.cssSelector("a[href='https://www.selenium.dev/']");
	
	
	@BeforeClass
	@Parameters({"url", "navegador"}) // Estos parametros vienen del archivo testng.xml
	public void setup(String url, String navegador) {
		if (navegador.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (navegador.equalsIgnoreCase("Firefox")){
			System.setProperty("webdriver.gecko.driver", "./src/test/resources/drivers/geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (navegador.equalsIgnoreCase("Edge")) {
			System.setProperty("webdriver.edge.driver", "./src/test/resources/drivers/msedgedriver.exe");
			driver = new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		driver.get(url);
		
		System.out.println("Iniciando navegador: " + navegador);
	}
	
	@Test
	public void busquedaMultiNavegador() {
		WebElement cajaBusquedaElemento = driver.findElement(cajaBusqueda);
		cajaBusquedaElemento.clear();
		cajaBusquedaElemento.sendKeys("selenium", Keys.ENTER);
		
		WebDriverWait espera = new WebDriverWait(driver, 10);
		espera.until(ExpectedConditions.presenceOfElementLocated(seleniumLinkLocator));
		
		assertTrue(driver.findElement(seleniumLinkLocator).isDisplayed());
	}
	
	
	@AfterClass
	public void tearDown() {
		//driver.quit();
	}

}