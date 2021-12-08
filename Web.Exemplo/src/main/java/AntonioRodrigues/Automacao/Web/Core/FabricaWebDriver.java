package AntonioRodrigues.Automacao.Web.Core;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.ie.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FabricaWebDriver {
	
	private static WebDriver driver;
	
	public static WebDriver CriarWebDriver(HostExecucao hostExecucao) {
		WebDriver retorno;
		
		if ("".equals(hostExecucao.CaminhoWebDriver))
			retorno = CriarWebDriver(hostExecucao.Navegador);
		else
			retorno = CriarWebDriver(hostExecucao.Navegador, hostExecucao.CaminhoWebDriver);
		

		retorno.manage().window().maximize();
		retorno.navigate().to(hostExecucao.Url);
		
		return retorno;
	}
	
	/**
	 * Método responsável pela criação da instância webdriver que fara acesso ao navegador
	 * 
	 * @param navegador - Enumerador indicador de qual navegador será utilizado no teste
	 * @param caminhoWebDriver - Caminho para o webdriver
	 * @return WebDriver - Objeto responsável para interação com telas web
	 */
	public static WebDriver CriarWebDriver(Navegador navegador, String caminhoWebDriver) {
		
		try {
			
			PageLoadStrategy pageLoadStrategy = PageLoadStrategy.NORMAL;
			
			switch (navegador) {
			case GoogleChrome:
				System.setProperty("webdriver.chrome.driver", caminhoWebDriver);
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.setPageLoadStrategy(pageLoadStrategy);
				driver = new ChromeDriver(chromeOptions);
				break;
			case MozillaFirefox:
				System.setProperty("webdriver.gecko.driver", caminhoWebDriver);
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				firefoxOptions.setPageLoadStrategy(pageLoadStrategy);
				driver = new FirefoxDriver(firefoxOptions);
				break;
			case InternetExplorer:
				System.setProperty("webdriver.ie.driver", caminhoWebDriver);
				InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
				internetExplorerOptions.setPageLoadStrategy(pageLoadStrategy);
				driver = new InternetExplorerDriver(internetExplorerOptions);
				break;
			case MicrosoftEdge:
				System.setProperty("webdriver.edge.driver", caminhoWebDriver);
				EdgeOptions edgeOptions = new EdgeOptions();
				edgeOptions.setPageLoadStrategy(pageLoadStrategy.toString());
				driver = new EdgeDriver(edgeOptions);
				break;
			case Safari:
				break;
			}
			
		} catch (Exception e) {
//			Assert.fail(e.toString());
		}
		
		return driver;
	}
	
	
	
	/**
	 * Método responsável pela criação da instância webdriver que fara acesso ao navegador sem passa o path do driver.
	 * 
	 * @param navegador - Enumerador indicador de qual navegador será utilizado no teste
	 * @return WebDrive - Objeto responsável para interação com telas web
	 */
	public static WebDriver CriarWebDriver(Navegador navegador) {
		
		try {
			
			PageLoadStrategy pageLoadStrategy = PageLoadStrategy.NORMAL;
			
			switch (navegador) {
			case GoogleChrome:
				WebDriverManager.chromedriver().setup();
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.setPageLoadStrategy(pageLoadStrategy);
				driver = new ChromeDriver(chromeOptions);
				break;
			case MozillaFirefox:
				WebDriverManager.firefoxdriver().setup();
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				firefoxOptions.setPageLoadStrategy(pageLoadStrategy);
				driver = new FirefoxDriver(firefoxOptions);
				break;
			case InternetExplorer:
				WebDriverManager.iedriver().setup();
				InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
				internetExplorerOptions.setPageLoadStrategy(pageLoadStrategy);
				driver = new InternetExplorerDriver(internetExplorerOptions);
				break;
			case MicrosoftEdge:
				WebDriverManager.edgedriver().setup();
				EdgeOptions edgeOptions = new EdgeOptions();
				edgeOptions.setPageLoadStrategy(pageLoadStrategy.toString());
				driver = new EdgeDriver(edgeOptions);
				break;
			case Safari:
				break;
			}
			
		} catch (Exception e) {
//			Assert.fail(e.toString());
		}
		
		return driver;
	}
}
