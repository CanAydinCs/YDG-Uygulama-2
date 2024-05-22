package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Soru4
{
    public static void main(String[] args) throws Exception {
        int bekleme = 1000; // Her adım öncesi bekleme süresi

        // Adım 1 : Tarayıcıyı aç
        String chromedriverPath = System.getProperty("user.dir") + "\\chromedriver-win64\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromedriverPath);
        WebDriver driver = new ChromeDriver();

        try {
            // Adım 2 : Belirtilen url'ye git
            driver.get("http://automationexercise.com");
            Thread.sleep(bekleme); 

            // Adım 3 : Ana ekranın başarıyla görüntülenip görüntülenmediğini kontrol et
            String title = driver.getTitle();
            if (title.contains("Automation Exercise")) {
                System.out.println("Ana ekran başarıyla görüntülendi.");
            } else {
                throw new Exception("Ana ekranı yüklerken hata oluştu!");
            }
            Thread.sleep(bekleme); 

            // Adım 4 : 'Signup / Login' butonuna tıkla
            WebElement signupButton = driver.findElement(By.linkText("Signup / Login"));
            signupButton.click();
            Thread.sleep(bekleme); 

            // Adım 5 : 'Login to your account' yazısının görünür olup olmadığını kontrol et
            WebElement loginHeader = driver.findElement(By.xpath("//h2[text()='Login to your account']"));
            if (loginHeader.isDisplayed()) {
                System.out.println("'Login to your account' yazısı görünür.");
            } else {
                throw new Exception("'Login to your account' yazısı görünür değil!");
            }
            Thread.sleep(bekleme); 

            // Adım 6 : doğru e-posta ve şifreyi girin
            WebElement emailField = driver.findElement(By.name("email"));
            emailField.sendKeys("wfafawf@gmail.com"); 
            Thread.sleep(bekleme); 

            WebElement passwordField = driver.findElement(By.name("password"));
            passwordField.sendKeys("12345"); 
            Thread.sleep(bekleme); 

            // Adım 7 : 'login' butonuna tıkla 
            WebElement loginButton = driver.findElement(By.cssSelector("[data-qa='login-button']"));
            loginButton.click();
            Thread.sleep(bekleme); 

            // Adım 8 : 'Logged in as username' yazısı görünür olup olmadığını kontrol et
            WebElement loggedInUser = driver.findElement(By.cssSelector("a > b"));
            if (loggedInUser.isDisplayed()) {
                System.out.println("'Logged in as username' yazısı görünür.");
            } else {
                throw new Exception("'Logged in as username' yazısı görünür değil!");
            }
            Thread.sleep(bekleme); 

            // Adım 9 : 'Logout' butonuna tıkla
            WebElement logoutButton = driver.findElement(By.linkText("Logout"));
            logoutButton.click();
            Thread.sleep(bekleme); 

            // Adım 10 : Kullanıcının giriş sayfasına girip girmediğini doğrula
            if (driver.getCurrentUrl().contains("/login")) {
                System.out.println("Kullanıcı giriş sayfasına yönlendirildi.");
            } else {
                throw new Exception("Kullanıcı giriş sayfasına yönlendirilemedi!");
            }
        } finally {
            driver.quit();
            System.out.println("Uygulama kapandı.");
            System.exit(0);
        }
    }
}