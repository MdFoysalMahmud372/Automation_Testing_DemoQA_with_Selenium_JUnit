import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class TestAutomationJUnit {

    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void browserSetup() {
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(ops);
//        ChromeOptions ops = new ChromeOptions();
//        ops.addArguments("--headless=*");
//        driver = new ChromeDriver(ops);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void targetSite() {
        driver.get("https://demoqa.com");
    }

    @Test
    public void getTitle() {
        driver.get("https://demoqa.com");
        String titleActual = driver.getTitle();
        String titleExpected = "DEMOQA";

        Assert.assertEquals(titleExpected, titleActual);

    }

    @Test
    public void getTitleImg() {
        driver.get("https://demoqa.com");
        driver.findElement(By.cssSelector("img[src='/images/Toolsqa.jpg']"));
    }

    @Test
    public void writeforms(){
        driver.get("https://demoqa.com/automation-practice-form");

        driver.findElement(By.id("firstName")).sendKeys("Arifin");
        driver.findElement(By.id("lastName")).sendKeys("Mahmud");
        driver.findElement(By.id("userEmail")).sendKeys("xyz@gmail.com");

        Actions actions = new Actions(driver);
        WebElement button = driver.findElement(By.xpath("//label[normalize-space()='Male']"));
        actions.click(button).perform();

        driver.findElement(By.id("userNumber")).sendKeys("4564351285");

//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(627, 494)", "");


        driver.findElement(By.id("dateOfBirthInput")).click();
        Select month = new Select(driver.findElement(By.className("react-datepicker__month-select")));
        month.selectByValue("8");
        Select year = new Select(driver.findElement(By.className("react-datepicker__year-select")));
        year.selectByValue("1998");
        driver.findElement(By.xpath("//div[@aria-label='Choose Tuesday, September 1st, 1998']")).click();

//        WebElement subjectsInput = driver.findElement(By.id("subjectsInput")); // Fixed locator
//        subjectsInput.sendKeys("CSE");
//        subjectsInput.sendKeys(Keys.ENTER);

//        WebElement checkbox = driver.findElement(By.xpath("//label[normalize-space()='Sports']"));
//        if (!checkbox.isSelected()) {
//            checkbox.click();

        WebElement uploadElement = driver.findElement(By.id("uploadPicture"));
        uploadElement.sendKeys("D:\\GitHub\\DemoQA-Automation-Testing-with-Selenium-JUnit\\3040-selenium(1).pngL.jpg");

        driver.findElement(By.id("currentAddress")).sendKeys("BD");

        WebElement stateDropdown = driver.findElement(By.xpath("//div[@id='state']//div[contains(@class,'indicatorContainer')]"));
        actions.click(stateDropdown).perform();
        driver.findElement(By.xpath("//div[text()='NCR']")).click();

        WebElement cityDropdown = driver.findElement(By.xpath("//div[@id='city']//div[contains(@class,'indicatorContainer')]"));
        actions.click(cityDropdown).perform();
        driver.findElement(By.xpath("//div[text()='Delhi']")).click();
    }

    @Test
    public void write() {
        driver.get("https://demoqa.com/text-box");
        driver.findElement(By.id("userName")).sendKeys("Arifin");
        driver.findElement(By.id("userEmail")).sendKeys("xyz@gmail.com");
        driver.findElement(By.id("currentAddress")).sendKeys("BD");
        driver.findElement(By.id("permanentAddress")).sendKeys("BD");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,350)", "");
        driver.findElement(By.id("submit")).click();
        String text = driver.findElement(By.id("name")).getText();
        Assert.assertTrue(text.contains("Arifin"));
    }


    public void writeOnTextBox() {
        driver.get("https://demoqa.com/elements");
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Text Box')]"))).click();
        driver.findElement(By.cssSelector("#userName")).sendKeys("Mahmud");
        driver.findElement(By.cssSelector("#submit")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("#name")).getText().contains("Mahmud"));

    }

    @Test
    public void doubleClickMe() {
        driver.get("https://demoqa.com/buttons");
        Actions actions = new Actions(driver);
        WebElement button = driver.findElement(By.xpath("//button[@id='doubleClickBtn']"));
        actions.doubleClick(button).perform();
    }

    @Test
    public void clickme() {
        driver.get("https://demoqa.com/buttons");
        Actions actions = new Actions(driver);
        WebElement button = driver.findElements(By.xpath("//button[contains(text(),'Click Me')]")).get(2);
        actions.click(button).perform();

    }

    @Test
    public void clickMultipleButtons() {
        driver.get("https://demoqa.com/buttons");
        Actions action = new Actions(driver);

        List<WebElement> list = driver.findElements(By.cssSelector("button"));

        action.doubleClick(list.get(1)).perform();
        String text = driver.findElement(By.id("doubleClickMessage")).getText();
        Assert.assertTrue(text.contains("You have done a double click"));

        action.contextClick(list.get(2)).perform();
        String text2 = driver.findElement(By.id("rightClickMessage")).getText();
        Assert.assertTrue(text2.contains("You have done a right click"));


//        action.click(list.get(3)).perform();
//        list.get(3).click();
//        String text3 = driver.findElement(By.id("dynamicClickMessage")).getText();
//        Assert.assertTrue(text3.contains("You have done a dynamic click"));
    }

    //
    @Test
    public void handleAlerts() throws InterruptedException {
        driver.get("https://demoqa.com/alerts");

        // Set an implicit wait (use Duration instead of int)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(732,542)", "");

        driver.findElement(By.id("alertButton")).click();
        driver.switchTo().alert().accept();

        WebElement promtButton = driver.findElement(By.id("promtButton"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", promtButton);

//    WebElement iframe = driver.findElement(By.cssSelector("iframe[id^='google_ads_iframe']"));
//    ((JavascriptExecutor) driver).executeScript("arguments[0].style.display='none';", iframe);


        promtButton.click();
        driver.switchTo().alert().sendKeys("Arifin");
        driver.switchTo().alert().accept();

        String text = driver.findElement(By.id("promptResult")).getText();
        Assert.assertTrue(text.contains("Arifin"));
    }

    @Test
    public void datepicker() {
        driver.get("https://demoqa.com/date-picker");
        driver.findElement(By.id("datePickerMonthYearInput")).click();
        driver.findElement(By.id("datePickerMonthYearInput")).sendKeys(Keys.CONTROL + "A" + Keys.BACK_SPACE);
        driver.findElement(By.id("datePickerMonthYearInput")).sendKeys("01/09/1998", Keys.ENTER);
//        driver.findElement(By.id("datePickerMonthYearInput")).sendKeys(Keys.ENTER);
    }

    @Test
    public void gettingCurrentDate() {
        driver.get("https://demoqa.com/date-picker");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        WebElement iframe = driver.findElement(By.cssSelector("iframe[id^='google_ads_iframe']"));
//        ((JavascriptExecutor) driver).executeScript("arguments[0].style.display='none';", iframe);
        driver.findElement(By.id("datePickerMonthYearInput")).click();
        driver.findElement(By.id("datePickerMonthYearInput")).sendKeys(Keys.CONTROL + "A" + Keys.BACK_SPACE);
        // Create object of SimpleDateFormat class and decide the format
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        //get current date time with Date()
        Date date = new Date();
        // Now format the date
        String currentDate = dateFormat.format(date);
        driver.findElement(By.id("datePickerMonthYearInput")).sendKeys(currentDate, Keys.ENTER);
    }

    public void waitForThePresenceOfTheElement(By webElement) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(webElement));

    }

    @Test
    public void iframe_handling() {
        driver.get("https://demoqa.com/frames");
        driver.switchTo().frame("frame2");
        String text = driver.findElement(By.id("sampleHeading")).getText();
        Assert.assertTrue(text.contains("This is a sample page"));
        driver.switchTo().defaultContent();
    }


    @Test
    public void selectingDateFromDropdown() {
        driver.get("https://demoqa.com/date-picker");

        driver.findElement(By.id("datePickerMonthYearInput")).click();
//        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("react-datepicker__month-select")));
//        WebElement iframe = driver.findElement(By.cssSelector("iframe[id^='google_ads_iframe']"));
//        ((JavascriptExecutor) driver).executeScript("arguments[0].style.display='none';", iframe);
        waitForThePresenceOfTheElement(By.className("react-datepicker__month-select"));
        Select month = new Select(driver.findElement(By.className("react-datepicker__month-select")));
        month.selectByValue("1");
        Select year = new Select(driver.findElement(By.className("react-datepicker__year-select")));
        year.selectByValue("2025");
        driver.findElement(By.xpath("//div[@aria-label='Choose Sunday, February 9th, 2025']")).click();
    }

    @Test
    public void selectDropdown() {
        driver.get("https://demoqa.com/select-menu");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement iframe = driver.findElement(By.cssSelector("iframe[id^='google_ads_iframe']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.display='none';", iframe);
        Select color = new Select(driver.findElement(By.id("oldSelectMenu")));
        color.selectByValue("1");
        Select cars = new Select(driver.findElement(By.id("cars")));
        if (cars.isMultiple()) {
            cars.selectByValue("saab");
            cars.selectByValue("audi");
//            we could have also utilized the method of sendKeys(Keys.CONTROL + "" + Keys.);
        }
    }

    @Test
    public void mouseHover() throws InterruptedException {
        driver.get("https://dribbble.com/tags/mouse-over");
        WebElement menuWrite = driver.findElement(By.xpath("//a[normalize-space()='Explore'][normalize-space()='Explore']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(menuWrite).perform();
        Thread.sleep(2000);
    }

    @Test
    public void keyboardEvents() throws InterruptedException {
        driver.get("https://www.google.com/");
        WebElement searchElement = driver.findElement(By.name("q"));
        Actions action = new Actions(driver);
        action.moveToElement(searchElement);
        action.keyDown(Keys.SHIFT);
        action.sendKeys("Selenium Webdriver").keyUp(Keys.SHIFT).doubleClick().contextClick().perform();
        Thread.sleep(2000);
    }

    @Test
    public void modalDialog() {
        driver.get("https://demoqa.com/modal-dialogs");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(415,559)", "");
        driver.findElement(By.id("showSmallModal")).click();
        String text = driver.findElement(By.className("modal-body")).getText();
        System.out.println(text);
        driver.findElement(By.id("closeSmallModal")).click();
    }

    @Test
    public void uploadImage() {
        driver.get("https://demoqa.com/upload-download");
        WebElement uploadElement = driver.findElement(By.id("uploadFile"));
        uploadElement.sendKeys("D:\\GitHub\\DemoQA-Automation-Testing-with-Selenium-JUnit\\3040-selenium(1).pngL.jpg");
        String text = driver.findElement(By.id("uploadedFilePath")).getText();
        Assert.assertTrue(text.contains("3040-selenium(1).pngL.jpg"));
    }

    @Test
    public void downloadFile() {
        driver.get("https://demoqa.com/upload-download");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(408,542)", "");
        driver.findElement(By.id("downloadButton")).click();
    }

    @Test
    public void handleMultipleTabs() throws InterruptedException {
        driver.get("https://demoqa.com/browser-windows");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(402,542)", "");
        driver.findElement(By.id("tabButton")).click();
        Thread.sleep(2000);
        ArrayList<String> tabList = new ArrayList(driver.getWindowHandles());
        //switch to opentab
        driver.switchTo().window(tabList.get(1));
        System.out.println("New tab title: " + driver.getTitle());
        String text = driver.findElement(By.id("sampleHeading")).getText();
        Assert.assertEquals(text, "This is a sample page");
        driver.close();
        driver.switchTo().window(tabList.get(0));
    }

    @Test
    public void handleMultipleWindows() throws InterruptedException {
        driver.get("https://demoqa.com/browser-windows");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(419,545)", "");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("windowButton")));
        driver.findElement(By.id(("windowButton"))).click();
        String mainWindowHandle = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();
        Iterator<String> iterator = allWindowHandles.iterator();
        while (iterator.hasNext()) {
            String ChildWindow = iterator.next();
            if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
                driver.switchTo().window(ChildWindow);
                String text = driver.findElement(By.id("sampleHeading")).getText();
                Assert.assertTrue(text.contains("This is a sample page"));
            }
        }



    }

    @Test
    public void screenShot() throws IOException {
        driver.get("https://demoqa.com");
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String time = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss-aa").format(new Date());
        String fileWithPath = "./src/test/resources/screenshots/" + time + ".png";
        File DestFile = new File(fileWithPath);
        FileUtils.copyFile(screenshotFile, DestFile);
    }

    @Test
    public void dataScrapping() {
        driver.get("https://demoqa.com/webtables");
        WebElement table = driver.findElement(By.className("rt-tbody"));
        List<WebElement> allRows = table.findElements(By.className("rt-tr"));
        int i = 0;
        for (WebElement row : allRows) {
            List<WebElement> cells = row.findElements(By.className("rt-td"));
            for (WebElement cell : cells) {
                i++;
                System.out.println("num[" + i + "] " + cell.getText());
            }
        }
    }

    @Test
    public void webTables() {
        driver.get("https://demoqa.com/webtables");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(925,552)", "");
        driver.findElement(By.xpath("//span[@id='edit-record-1']//*[@stroke='currentColor']")).click();
        driver.findElement(By.id("submit")).click();
    }






}

