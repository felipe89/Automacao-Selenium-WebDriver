package suporte;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.MalformedInputException;
import java.util.concurrent.TimeUnit;

public class Web {
    public static final String USERNAME = "felipetrindade1";
    public static final String AUTOMATE_KEY = "bqxwzBqgk1vrMAMuTDqN";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    public static WebDriver createChrome(){
        //Abrindo o navegador
        System.setProperty("webdriver.chrome.driver", "/home/gilmar/Documentos/drivers/chromedriver");
        WebDriver navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //Navegador para a pagina
        navegador.get("http://www.juliodelima.com.br/taskit/");

        return navegador;
    }

    public static WebDriver createBrowserStack(){

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browser", "Chrome");
        caps.setCapability("browser_version", "60.0");
        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "10");
        caps.setCapability("resolution", "1280x800");
        caps.setCapability("browserstack.debug", "true");

        WebDriver navegador = null;

        try {
            navegador = new RemoteWebDriver(new URL (URL), caps);
            navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

            //Navegador para a pagina
            navegador.get("http://www.juliodelima.com.br/taskit/");

        } catch (MalformedURLException e) {
            System.out.println("Houveram problemas com a url" + e.getMessage());
        }

        return navegador;
    }
}
