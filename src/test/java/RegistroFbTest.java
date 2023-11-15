import java.util.concurrent.TimeUnit;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.testng.Assert.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

/****************************************/
/*
Historia de Usuario: Como usuario nuevo quiero registrar mis datos

Prueba de Aceptacion 1: Verificar que se muestren alertas para los campos obligatorios

1. Ingresar a la pagina de Facebook
2. Hacer click en el link Crear cuenta nueva
3. Presionar el boton Crear cuenta nueva

Resultado Esperado: Se deben mostrar mensajes de alerta para los campos obligatorios que no fueron llenados
 */
/****************************************/
public class RegistroFbTest {

    private WebDriver driver;

    @BeforeTest
    public void setDriver() throws Exception{

        String path = "C:/Users/User/Desktop/GRECO/MATERIAS 2023/Gestion calidad/chromedriver-win64/chromedriver.exe";

        System.setProperty("webdriver.chrome.driver", path);

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

    }

    @Test
    public void verificarMensajeErrorAlRegistrar(){

        /********** Preparacion de la prueba **********/

        //1. Ingresar a la pagina de Facebook
        String fbUrl = "https://www.facebook.com/";
        driver.get(fbUrl);

        /*********** Logica de la prueba***********/

        //2. Hacer click en el link Crear cuenta nueva

        WebElement joinLink = driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']"));

        joinLink.click();

        //Esperamos 3 segundos
        try{
            TimeUnit.SECONDS.sleep(3);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }

        //3. Presionar el boton Crear cuenta nueva

        WebElement joinButton = driver.findElement(By.name("websubmit"));

        joinButton.click();

        //Esperamos 3 segundos
        try{
            TimeUnit.SECONDS.sleep(3);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }

        /************Verificacion de la situacion esperada - Assert***************/

        WebElement errorMessage = driver.findElement(By.xpath("//div[contains(text(),'¿Cómo te llamas?')]"));

        String expectedMessage = "¿Cómo te llamas?";

        String actualMessage = errorMessage.getText();

        Assert.assertEquals(expectedMessage, actualMessage);

    }

/*
Prueba de acetacion 2: Verificar que se muestre mensaje de error al ingresar una contraseña debil

1. Ingresar a la pagina de Facebook
2. Hacer click en el link Crear cuenta nueva
3. Ingresar todos los campos obligatorios, con una contraseña sencilla
4. Presionar el boton Crear cuenta nueva

Resultado Esperado: Se debe mostrar un mensaje de error indicando que la contraseña es debil

 */

    @Test
    public void verificarMensajeErrorContrasenaDebil() {

        /********** Preparacion de la prueba **********/

        //1. Ingresar a la pagina de Facebook
        String fbUrl = "https://www.facebook.com/";
        driver.get(fbUrl);

        /*********** Logica de la prueba***********/

        //2. Hacer click en el link Crear cuenta nueva

        WebElement joinLink = driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']"));

        joinLink.click();

        //Esperamos 3 segundos
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //3. Ingresar todos los campos obligatorios, con una contraseña sencilla

        WebElement firstName = driver.findElement(By.name("firstname"));
        firstName.sendKeys("Juan");

        WebElement lastName = driver.findElement(By.name("lastname"));
        lastName.sendKeys("Perez");

        WebElement number = driver.findElement(By.name("reg_email__"));
        number.sendKeys("70172153");

        WebElement password = driver.findElement(By.name("reg_passwd__"));
        password.sendKeys("123456789");

        WebElement day = driver.findElement(By.name("birthday_day"));
        day.sendKeys("1");

        WebElement month = driver.findElement(By.name("birthday_month"));
        month.sendKeys("1");

        WebElement year = driver.findElement(By.name("birthday_year"));
        year.sendKeys("2000");

        WebElement genero = driver.findElement(By.xpath("//label[contains(text(),'Hombre')]"));
        genero.click();

        WebElement joinButton = driver.findElement(By.name("websubmit"));
        joinButton.click();

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /************Verificacion de la situacion esperada - Assert***************/


        WebElement errorMessage = driver.findElement(By.xpath("//div[contains(text(),'Elige una contraseña más segura. Debe tener más de 6 caracteres, solo tú debes conocerla y debe ser difícil de adivinar.')]"));
        String actualMessage = errorMessage.getText();

        Assert.assertEquals("Elige una contraseña más segura. Debe tener más de 6 caracteres, solo tú debes conocerla y debe ser difícil de adivinar.", actualMessage);

    }


    @AfterTest
    public void closeDriver() throws Exception{
        driver.quit();
    }

}
