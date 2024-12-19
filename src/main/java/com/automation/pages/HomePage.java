package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

    private WebDriver driver;

    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    private By loginButton = By.id("login-button");
    private By categoryMenu = By.id("category-menu");
    private By subcategoryMenu = By.id("subcategory-menu");
    private By firstProduct = By.xpath("//div[@class='product'][1]");
    private By addToCartButton = By.id("add-to-cart");
    private By cartPopup = By.id("cart-popup");
    private By totalPricePopup = By.id("total-price");
    private By proceedToCheckoutButton = By.id("checkout-button");
    private By cartPageTitle = By.xpath("//h1[text()='Your Cart']");
    private By totalPriceCart = By.id("cart-total-price");


    public void navigateToCategoryAndSubcategory(String category, String subcategory) {
        driver.findElement(categoryMenu).click();
        driver.findElement(By.linkText(category)).click();
        driver.findElement(subcategoryMenu).click();
        driver.findElement(By.linkText(subcategory)).click();
    }

    public void addFirstProductToCart() {
        driver.findElement(firstProduct).click();
        driver.findElement(addToCartButton).click();
    }

    // Método para validar el popup de confirmación
    public boolean isProductAddedToCart() {
        return driver.findElement(cartPopup).isDisplayed();
    }

    // Método para obtener el precio total del popup
    public String getTotalPriceFromPopup() {
        return driver.findElement(totalPricePopup).getText();
    }

    public void proceedToCheckout() {
        driver.findElement(proceedToCheckoutButton).click();
    }

    public boolean isCartPageTitleDisplayed() {
        return driver.findElement(cartPageTitle).isDisplayed();
    }

    public String getTotalPriceFromCart() {
        return driver.findElement(totalPriceCart).getText();
    }
}
