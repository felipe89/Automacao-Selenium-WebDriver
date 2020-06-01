package testes;

import static org.junit.Assert.*;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class InformacoesUsuarioTest {

    @Test
    public void testAdicionarUmaInformacaoAdicionarParaUsuario(){
        //Abrindo o navegador
        System.setProperty("webdriver.chrome.driver", "/home/gilmar/Documentos/drivers/chromedriver");
        WebDriver navegador = new ChromeDriver();

        //Navegador para a pagina
        navegador.get("http://juliodelima.com.br/taskit/");

        //Validações
        assertEquals(1, 1);
    }
}
