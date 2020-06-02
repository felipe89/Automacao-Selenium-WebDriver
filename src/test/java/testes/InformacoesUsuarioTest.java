package testes;

import static org.junit.Assert.*;

import jdk.jfr.Timespan;
import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import suporte.Web;

import java.beans.Visibility;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "InformacoesUsuarioTest.csv")

public class InformacoesUsuarioTest {

    private WebDriver navegador;

    @Before
    public void setUp() {
        navegador = Web.createChrome();

        //Clicar no link que possui o texto "Sign in"
        navegador.findElement(By.linkText("Sign in")).click();

        //Identificar Formulario de login
        WebElement formularioSignBox = navegador.findElement(By.id("signinbox"));

        //Digitar no campo nome "login" que esta dentro do formulario
        formularioSignBox.findElement(By.name("login")).sendKeys("julio0001");

        //Digitar no campo nome "password" que esta dentro do formulario
        formularioSignBox.findElement(By.name("password")).sendKeys("123456");

        //Click no link com o texto "SIGN IN"
        navegador.findElement(By.linkText("SIGN IN")).click();

        //Click em um link que possui o nome "me"
        navegador.findElement(By.className("me")).click();

        //Click no link que possui o texto "MORE DATA ABOUT YOU"
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
    }

    @Test
    public void testAdicionarUmaInformacaoAdicionarParaUsuario(@Param(name ="tipo")String tipo, @Param(name = "contato")String contato, @Param(name = "mensagem")String mesagemEsperada) {

        //Click no botão através do xpath //button[@data-target="addmoredata"]
        navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();

        //Identificar a popup onde esta o formulario de id addmoredata
        WebElement popUpAddMoreData = navegador.findElement(By.id("addmoredata"));

        //Na combo de nome "type" escolhe a opção "Phone"
        WebElement campoType = popUpAddMoreData.findElement(By.name("type"));
        new Select(campoType).selectByVisibleText(tipo);

        //No campo de nome "contact" digitar "+55119999999999"
        popUpAddMoreData.findElement(By.name("contact")).sendKeys(contato);

        //Click no botão "SAVE"
        popUpAddMoreData.findElement(By.linkText("SAVE")).click();

        //Na mensagem de id "toast-container" validar que o texto é "You contact has been added!"
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String msn = mensagemPop.getText();
        assertEquals(mesagemEsperada, msn);
    }

//    @Test
    public void removerUmContatoDeUsuario() {

        //Clicar no elemento pelo seu xpath "//span[text()='+551199996666']/following-sibling::a"
        navegador.findElement(By.xpath("//span[text()='+551199996666']/following-sibling::a")).click();

        //Confirmar a janela javaScript
        navegador.switchTo().alert().accept();

        //Validar que a mensagem "Rest in peace, dear phone!", foi apresentada
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String msn = mensagemPop.getText();
        assertEquals("Rest in peace, dear phone!", msn);

        //Aguardar até 10 segundos para que a janela desapareça
        WebDriverWait aguardar = new WebDriverWait(navegador, 10);
        aguardar.until(ExpectedConditions.stalenessOf(mensagemPop));

        //Clicar no link com o texto "Logout"
        navegador.findElement(By.linkText("Logout")).click();
    }

//    @After
    public void tearDown() {
        //Fechar Navegador
            navegador.quit();
    }
}
