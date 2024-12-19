package com.automation.steps;

import com.automation.pages.HomePage;
import com.automation.pages.LoginPage;
import com.automation.utils.BaseTest;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class ProductStoreSteps extends BaseTest {

    private HomePage homePage;
    private LoginPage loginPage;

    @Before
    public void setUpTest() {
        setUp();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
    }

    @After
    public void tearDownTest() {
        tearDown();
    }

    @Given("estoy en la página de la tienda")
    public void estoyEnLaPaginaDeLaTienda() {
        driver.get("https://qalab.bensg.com/store");
    }

    @And("me logueo con mi usuario {string} y clave {string}")
    public void meLogueoConMiUsuarioYClave(String usuario, String clave) {
        homePage = loginPage.login(usuario, clave);
    }

    @When("navego a la categoría {string} y subcategoría {string}")
    public void navegoALaCategoriaYSubcategoria(String categoria, String subcategoria) {
        homePage.navigateToCategoryAndSubcategory(categoria, subcategoria);
    }

    @And("agrego {int} unidades del primer producto al carrito")
    public void agregoUnidadesDelPrimerProductoAlCarrito(int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            homePage.addFirstProductToCart();
        }
    }

    @Then("valido en el popup la confirmación del producto agregado")
    public void validoEnElPopupLaConfirmacionDelProductoAgregado() {
        Assert.assertTrue("El producto no fue agregado correctamente al carrito.", homePage.isProductAddedToCart());
    }

    @And("valido en el popup que el monto total sea calculado correctamente")
    public void validoEnElPopupQueElMontoTotalSeaCalculadoCorrectamente() {
        String totalPopup = homePage.getTotalPriceFromPopup();
        Assert.assertNotNull("El precio total en el popup no es válido.", totalPopup);
    }

    @When("finalizo la compra")
    public void finalizoLaCompra() {
        homePage.proceedToCheckout();
    }

    @Then("valido el título de la página del carrito")
    public void validoElTituloDeLaPaginaDelCarrito() {
        Assert.assertTrue("El título de la página del carrito no es correcto.", homePage.isCartPageTitleDisplayed());
    }

    @And("vuelvo a validar el cálculo de precios en el carrito")
    public void vuelvoAValidarElCalculoDePreciosEnElCarrito() {
        String totalCart = homePage.getTotalPriceFromCart();
        Assert.assertNotNull("El precio total en el carrito no es válido.", totalCart);
    }
}
