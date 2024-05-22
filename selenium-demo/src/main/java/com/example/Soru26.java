package com.example;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Soru26 {

    public static void main(String[] args) {
        int bekleme = 3000; // Her adım öncesi bekleme süresi

        // Adım 1: Tarayıcıyı aç 
        String chromedriverPath = System.getProperty("user.dir") + "\\chromedriver-win64\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromedriverPath);
        WebDriver driver = new ChromeDriver();

        try {
            // Adım 2: 'http://automationexercise.com' adresine git
            driver.get("http://automationexercise.com");
            Thread.sleep(bekleme);

            // Adım 3: Sayfanın en altına git
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0, 7650);");
            while (!driver.findElement(By.id("recommended-item-carousel")).isDisplayed()) {
                js.executeScript("window.scrollBy(0, 200);");
            }
            Thread.sleep(bekleme);

            // Adım 4: 'RECOMMENDED ITEMS' eşyalarının göründüğüne emin ol
            WebElement recommendedItemsCarousel = driver.findElement(By.id("recommended-item-carousel"));
            if (!recommendedItemsCarousel.isDisplayed()) {
                throw new Exception("'RECOMMENDED ITEMS' görünür değil!");
            }
            else{
                System.out.println("'RECOMMENDED ITEMS' görünür.");
            }
            Thread.sleep(bekleme);

            // Adım 5: Önerilen bir eşyadaki 'Add To Cart' butonuna tıkla
            List<WebElement> addToCartButtons = driver.findElements(By.cssSelector(".btn.btn-default.add-to-cart"));
            double minYPositionThreshold = 7650; // Adjust this value as needed

            for (WebElement button : addToCartButtons) {
                double yPosition = (Double) ((JavascriptExecutor) driver).executeScript(
            "return arguments[0].getBoundingClientRect().top + window.pageYOffset;",
                     button);
                if (yPosition > minYPositionThreshold) 
                { 
                    button.click();
                    break;
                }
            }
            Thread.sleep(bekleme);

            // Adım 6: 'View Cart' butonuna tıkla
            driver.get("https://automationexercise.com/view_cart");
            Thread.sleep(bekleme);
            System.out.println("'View Cart' sayfasına gidildi.");

            // Adım 7: eşyanın sepette olup olmadığını doğrula
            WebElement cartPage = driver.findElement(By.className("cart_product"));
            if (!cartPage.isDisplayed()) {
                throw new Exception("Eşya sepette değil!");
            }
            else{
                System.out.println("Eşya sepette.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
            System.out.println("Uygulama kapandı.");
            System.exit(0);
        }
    }
}