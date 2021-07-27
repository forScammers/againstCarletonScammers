import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;


/*
to download selenium driver for whatever chrome you use. Do this link https://sites.google.com/a/chromium.org/chromedriver/downloads

 */

public class KamakazieBitch {
    ChromeDriver webDriver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(webDriver, 40);
    String googleFormLink = "https://forms.gle/pCyHpBXqj3acpiq8A";
    public boolean startUpProtocol(){
        if(openLinks()){
            //at this stage the link has opeend and we can see the submit button.
            return fillOutInfo();

        }
        return false;
    }

    private boolean openLinks(){
        webDriver.get(googleFormLink);
        if(webDriver.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[3]/div[1]/div/div/span")) != null){
            return true;
        }
        return false;
    }

    private boolean fillOutInfo(){
        WebElement firstName = webDriver.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[2]/div/div/div[2]/div/div[1]/div/div[1]/input"));
        WebElement lastName = webDriver.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[3]/div/div/div[2]/div/div[1]/div/div[1]/input"));
        WebElement emailNotSchool = webDriver.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[4]/div/div/div[2]/div/div[1]/div/div[1]/input"));
        WebElement alternateEmailNotSchool = webDriver.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[5]/div/div/div[2]/div/div[1]/div/div[1]/input"));
        WebElement phoneNumber = webDriver.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[6]/div/div/div[2]/div/div[1]/div/div[1]/input"));
        WebElement sexMale = webDriver.findElement(By.xpath("//*[@id=\"i28\"]/div[2]")); //female = //*[@id="i31"]/div[2]
        WebElement sexFemale = webDriver.findElement(By.xpath("//*[@id=\"i31\"]/div[2]"));
        WebElement address = webDriver.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[8]/div/div/div[2]/div/div[1]/div[2]/textarea"));
        WebElement city = webDriver.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[9]/div/div/div[2]/div/div[1]/div/div[1]/input"));
        WebElement state = webDriver.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[10]/div/div/div[2]/div/div[1]/div/div[1]/input"));
        WebElement zipCode = webDriver.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[11]/div/div/div[2]/div/div[1]/div/div[1]/input"));
        WebElement age = webDriver.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[12]/div/div/div[2]/div/div[1]/div/div[1]/input"));
        WebElement availableTimeMorning = webDriver.findElement(By.xpath("//*[@id=\"i59\"]/div[2]"));
        WebElement availableTimeAfternoon = webDriver.findElement(By.xpath("//*[@id=\"i62\"]/div[2]"));
        WebElement availableTimeEvening = webDriver.findElement(By.xpath("//*[@id=\"i65\"]/div[2]"));

        //now time to do some random ass shit.
        firstName.sendKeys(randomWordGenerator());
        lastName.sendKeys(randomWordGenerator());
        emailNotSchool.sendKeys((randomWordGenerator() + "@gmail.com"));
        alternateEmailNotSchool.sendKeys((randomWordGenerator()+"@gmail.com"));
        phoneNumber.sendKeys(randomNumberGenerator());
        if(maleOrFemale() == 1){
            sexMale.click();
        }
        else{
            sexFemale.click();
        }

        address.sendKeys(randomWordGenerator());
        city.sendKeys(randomWordGenerator());
        state.sendKeys(randomWordGenerator());
        zipCode.sendKeys(randomZipCode());
        age.sendKeys(randomAgeGenerator());
        int timing = availableTime();
        if(timing == 0){
            availableTimeMorning.click();
        }
        else if(timing == 1){
            availableTimeAfternoon.click();
        }
        else{
            availableTimeEvening.click();
        }


        //now we do the submit button.

        WebElement submitButton = webDriver.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[3]/div[1]/div/div/span"));
        submitButton.click();
        return true;

    }

    private String randomWordGenerator(){
        //yes this was 100% taken off the internet. More precisely from https://stackoverflow.com/a/45841798/9953736 which they also stole from another link. Comp Sci in 1 sentence.
        String saltChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghrijklmnopqrstuvwxyz";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * saltChars.length());
            salt.append(saltChars.charAt(index));
        }
        return salt.toString();
    }

    private String randomNumberGenerator(){
        //you guessed it. This was also ripped off the internet.
        Random r = new Random();

        int i1 = r.nextInt(8); // returns random number between 0 and 7
        int i2 = r.nextInt(8);
        int i3 = r.nextInt(8);
        int i4 = r.nextInt(742); // returns random number between 0 and 741
        int i5 = r.nextInt(10000); // returns random number between 0 and 9999

        return String.format("%d%d%d-%03d-%04d", i1, i2, i3, i4, i5);
    }

    private String randomZipCode(){
        //this wasn't stolen from the internet.
        Random r = new Random();
        int i5 = r.nextInt(10000); // returns random number between 0 and 9999
        return String.valueOf(i5);
    }

    private String randomAgeGenerator(){
        //this wasn't stolen from the internet.
        Random r = new Random();
        return String.valueOf(r.nextInt(26)); //number between 0 and 25.
    }

    private Integer maleOrFemale(){
        Random r = new Random();

        return r.nextInt(2);
    }

    private Integer availableTime(){
        Random r = new Random();

        return r.nextInt(3); //etiher 0, 1 or 2
    }
}
