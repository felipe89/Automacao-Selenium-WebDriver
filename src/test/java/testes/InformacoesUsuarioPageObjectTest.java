package testes;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import suporte.Web;

import static org.junit.Assert.*;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "InformacoesUsuario.csv")
public class InformacoesUsuarioPageObjectTest {
    private WebDriver navegador;

    @Before
    public void setUp(){
        navegador = Web.createChrome();
    }
    @Test
    public void testAdicionarUmaInformacaoAdicionarParaUsuario (
            @Param(name = "login")String login,
            @Param(name = "password")String password,
            @Param(name = "type")String type,
            @Param(name = "contact")String contact,
            @Param(name = "mensagem")String messagemEsperada ){
        String textToast = new LoginPage(navegador)
                .clickSignIn()
                .fazerLogin("julio0001", "123456")
                .clickMe()
                .clickAbaMoreDateAboutYou()
                .clickButtonAddMoreDataAboutYou()
                .addContact("Phone", "+5511888888888")
                .capturarTextToast();

        assertEquals(messagemEsperada, textToast);
    }

    @After
    public void tearDown(){
//        navegador.quit();
    }
}
