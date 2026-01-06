package com.qa.utils;

import com.qa.exceptions.BrowserException;
import com.qa.factory.DriverFactory;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;

public final class ElementUtil {


        private WebDriver driver;
        private Actions actions;
        private WebDriverWait wait;
        private JavaScriptUtil jsutil;
    private static final int SHORT_TIME = 5000;
    private static final int MEDIUM_TIME = 10000;
    private static final int LONG_TIME = 15000;
    private static final int LONGER_TIME = 20000;

        public ElementUtil(WebDriver driver) {
            this.driver = driver;
            this.actions = new Actions(driver);
            jsutil = new JavaScriptUtil(driver);
        }

    public static Logger log = LogManager.getLogger(ElementUtil.class);


        //************ BROWSER UTILS ***********
    /**
     * This method check validity of url like null or empty url or protocol and launch the url if its correct or else throw Exception
     * @param url
     */
    public void launchUrl(String url) {

        nullCheck(url);
        protocolCheck(url);
        lengthCheck(url);
        driver.get(url);
    }

    /**
     * when driver is not null, this method closes the browser
     */
    public void quit(){
        if(driver != null){
            driver.quit();
        }
    }

    /**
     * it closes the browser, session id will become expired or invalid.
     */
    public void close(){
        if(driver!= null){
            driver.close();
        }
    }

    /**
     * fectches the curret url from the launched browser
     * @return
     */
    public String  getCurrentUrl(){
        String url =  driver.getCurrentUrl();
        log.info(url);
        return url;
    }

    /**
     * fetch the title of the web page
     * @return
     */
    public String getTitle(){
        String title = driver.getTitle();
        log.info(title);
        return title;
    }

    /**
     * his method check the length of url
     * @param value
     */
    private void lengthCheck(String value){
        if(value.length() ==0){
            log.info("url should not be blank" + value);
            throw new BrowserException("url is blank");
        }
    }
    /**
     * his method check the null
     * @param value
     */

    private void nullCheck(String value){
        if (value == null){
            log.info("url should not be null" + value);
            throw new NullPointerException();
        }
    }
    /**
     * his method check the ptotocol
     * @param value
     */

    private void protocolCheck(String value)  {
        if(value.indexOf("http") != 0){
            throw new BrowserException("url does not have http protocol");

        }


    }


    /**
         * This method gives webelement on ui without checking its visibility
         *
         * @param locator
         * @return WebElement
         */
        public WebElement getElement(By locator) {
            WebElement element =driver.findElement(locator);
            highlightElement(element);
            return element;
        }

        private void highlightElement(WebElement element){
            if(Boolean.parseBoolean(DriverFactory.highlight)){
                jsutil.flash(element);
            }
        }

        /**
         * This is to get web element without using findelement method and checks visibilty of element in UI
         *
         * @param locator
         * @param timeout
         * @return
         */

        public WebElement getElementwithWait(By locator, int timeout) {
           WebElement element= waitforElementVisibility(locator, timeout);
           highlightElement(element);
            return element ;
        }

        /**
         * This method find links in the webpage and print all of them
         *
         * @param locator
         */
        public List<WebElement> getListOfWebElements(By locator) {
            return driver.findElements(locator);

        }

        /**
         * this method gives the count
         *
         * @param locator
         * @return
         */
        public int getelementsCount(By locator) {
            int totallinks = getListOfWebElements(locator).size();
            log.info("total count of links on the page : " + totallinks);
            return totallinks;
        }



        private void nullCheck(CharSequence... value) {
            if (value == null) {
                throw new RuntimeException("value can not be null");
            }

        }


        /**
         * this method helps to send characters in a text field
         *
         * @param locator
         * @param value
         */
        public void sendKeys(By locator, String value) {
            if (value != null) {
                getElement(locator).clear();
                getElement(locator).sendKeys(value);
            }

        }

        /**
         * This method accepts string, string buffer and StringBuilder and array of all the above
         *
         * @param locator
         * @param value
         */
        public void sendKeys(By locator, CharSequence... value) {
            nullCheck(value);
            getElement(locator).clear();
            getElement(locator).sendKeys(value);

        }


        /**
         * this method click on the given WebElement
         *
         * @param locator
         */
        @Step("clicking on the element : {0}")
        public void click(By locator) {
            getElement(locator).click();
        }

        /**
         * this method retuns the text associated with the web element.
         *
         * @param locator
         * @return text of the given WebElement
         */
        @Step("getting on the element : {0}")
        public String getText(By locator) {
            String text = getElement(locator).getText();
            log.info("the text is " + text);
            return text;
        }

        /**
         * this method will be used to find the attribute value of a given webelement
         *
         * @param locator
         * @param attributeName
         * @return
         */
        public String getAttribute(By locator, String attributeName) {
            nullCheck(attributeName);
            String attributeValue = driver.findElement(locator).getDomAttribute(attributeName);
            log.info(attributeValue);
            return attributeValue;
        }

        /**
         * this method will be used to find the attribute Property of a given webelement
         *
         * @param locator
         * @param attributeProperty
         * @return
         */
        @Step("finding the element using : {0}")
        public String getAttributeProperty(By locator, String attributeProperty) {
            nullCheck(attributeProperty);
            String attributepropValue = driver.findElement(locator).getDomProperty(attributeProperty);
            log.info(attributepropValue);
            return attributepropValue;
        }

        /**
         * This method used to check visibility of the element
         */
        public boolean elementIsDisplayed(By locator) {
            try {
                return getElement(locator).isDisplayed();
            } catch (NoSuchElementException e) {
                log.info("elemenet is not displyaed on page" + locator);
                return false;
            }
        }

        /**
         * This method checks webelement is enabled mainly for textbox or button
         *
         * @param locator
         * @return boolean
         */
        public boolean isEnabled(By locator) {
            try {
                return getElement(locator).isEnabled();
            } catch (NoSuchElementException e) {
                log.info("elemenet is not displyaed on page" + locator);
                return false;
            }
        }

        /**
         * This method check the web element type checkbox or radio or dropdown is selected
         *
         * @param locator
         * @return boolean
         */
        public boolean isSelected(By locator) {
            return getElement(locator).isSelected();
        }
        // ***********Dropdown Handling*****************

        /**
         * Select the dropdown value by Value Attribute
         *
         * @param locator
         * @param value
         * @return
         */
        public boolean selectDropdownByValue(By locator, String value) {
            Select select = new Select(getElement(locator));

            try {
                select.selectByValue(value);
                return true;
            } catch (NullPointerException e) {
                log.info("Element is not present in the DOM " + value);
                return false;
            }

        }

        /**
         * Select the dropdown value by index
         *
         * @param locator
         * @param index
         * @return
         */

        public boolean selectDropdownByIndex(By locator, int index) {
            Select select = new Select(getElement(locator));

            try {
                select.selectByIndex(index);
                return true;
            } catch (NullPointerException e) {
                log.info("Element is not present in the DOM " + index);
                return false;
            }

        }

        /**
         * Select the dropdown value by visible text
         *
         * @param locator
         * @param text
         * @return
         */
        @Step("finding the element using : {0}")
        public boolean selectDropdownByVisibleText(By locator, String text) {
            Select select = new Select(getElement(locator));

            try {
                select.selectByVisibleText(text);
                return true;
            } catch (NullPointerException e) {
                log.info("Element is not present in the DOM " + text);
                return false;
            }

        }

        /**
         * This method extracts the Text present in the dropdown values
         *
         * @param locator
         * @return
         */
        public List<String> getDropdownValueList(By locator) {

            Select select = new Select(getElement(locator));
            List<String> optionsText = new ArrayList<>();
            List<WebElement> optionsList = select.getOptions();
            for (WebElement e : optionsList) {
                String text = e.getText();
                optionsText.add(text);
            }
            return optionsText;
        }

        /**
         * This method extracts the Text present in the dropdown values and verify with the given list
         *
         * @param locator
         * @param expectedList
         * @return
         */
        public boolean getDropdownValueList(By locator, List<String> expectedList) {

            Select select = new Select(getElement(locator));
            List<String> optionsText = new ArrayList<>();
            List<WebElement> optionsList = select.getOptions();
            for (WebElement e : optionsList) {
                String text = e.getText();
                optionsText.add(text);
            }
            if (optionsText.containsAll(expectedList)) {
                return true;
            } else {
                return false;
            }
        }

        //*****non select Dropdown values Selection **********

        /**
         * This method works for single value, multiple values and all values selection from the element with out having select tag
         * 1.single eslection nonSelectDropdown(By dropdown, By dropdownvalues,"choice1")
         * 2. Multiselections nonSelectDropdown(By dropdown, By dropdownvalues,"choice1","choice 6","choice 8")
         * 3. all values selection nonSelectDropdown(By dropdown, By dropdownvalues,"all")
         *
         * @param choice
         * @param choiceList
         * @param choiceValues
         */
        public boolean selectDropdownnonselectType(By choice, By choiceList, String... choiceValues) {
            getElement(choice).click();

            List<WebElement> webElementList = getListOfWebElements(choiceList);
            Set<String> allFoundValues = new HashSet<>();
            Set<String> expectedValuesToselect = new HashSet<>(Set.of(choiceValues));
            if (choiceValues[0].equalsIgnoreCase("all")) {
                for (WebElement e : webElementList) {
                    e.click();
                }
                return true;
            } else {
                for (WebElement e : webElementList) {
                    String text = e.getText();
                    for (String value : choiceValues) {
                        if (text.trim().equals(value)) {
                            e.click();
                            allFoundValues.add(text);
                        }
                    }

                }
            }
            boolean allfound = allFoundValues.containsAll(expectedValuesToselect);
            if (allfound == false) {
                expectedValuesToselect.removeAll(allFoundValues);
                log.info("values missing " + expectedValuesToselect);
            }


            return allfound;
        }

        //   *************Action Class Uitls********************

        public void doMoveToElement(By locator) throws InterruptedException {
            actions.moveToElement(getElement(locator)).perform();
            Thread.sleep(2000);
        }

        public void handlePaentandSubMenu(By parentMenu, By subMenu) throws InterruptedException {
            doMoveToElement(parentMenu);
            click(subMenu);
        }

        public void handle4LevelElements(By level1, By level2, By level3, By level4) throws InterruptedException {
            click(level1);
            Thread.sleep(2000);
            doMoveToElement(level2);
            Thread.sleep(2000);
            doMoveToElement(level3);
            Thread.sleep(2000);
            click(level4);

        }

        public void perfrormDargAndDrop(By source, By target) {
            actions.clickAndHold(getElement(source)).moveToElement(getElement(target)).release().perform();
        }


        public void righclick(By locator) {
            actions.contextClick(getElement(locator)).perform();
        }


        public void scrolltoTheElementandEnterText(By locator, CharSequence... value) {
            actions.scrollToElement(getElement(locator))
                    .pause(200)
                    .sendKeys(getElement(locator), value)
                    .perform();
        }


        public void scrolltoTheElementandClick(By locator) {
            actions.scrollToElement(getElement(locator))
                    .pause(200)
                    .click(getElement(locator))
                    .perform();
        }

        /**
         * it will click on the element and enter the text
         *
         * @param locator
         * @param value
         */
        public void sendKeysByActionsClass(By locator, CharSequence... value) {
            actions.sendKeys(getElement(locator)).pause(200).sendKeys(value).perform();
        }

        /**
         * It will move to middle of the element and perform Click
         *
         * @param locator
         */
        public void clickUsingActionsClass(By locator) {
            actions.click(getElement(locator)).perform();
        }

        //******wait Utils ******************

        /**
         * This method checks web element present on DOM
         *
         * @param locator
         * @param timeout
         * @return
         */
        public WebElement waitforElementPresence(By locator, int timeout) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        }

        /**
         * this method checks the visibily of element in UI
         * Visibility means height and width of element is greater than 0
         *
         * @param locator
         * @param timeout
         * @return
         */
        @Step("finding the element using : {0}")
        public WebElement waitforElementVisibility(By locator, int timeout) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            try{
               WebElement element =wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
               highlightElement(element);
                return element;
            } catch (Exception e) {
                throw new RuntimeException("Element not visible: " + locator, e);
            }


        }

        /**
         * this method checks the visibily of elements in UI
         * Visibility means height and width of element is greater than 0
         *
         * @param locator
         * @param timeout
         * @return
         */
        public List<WebElement> getElementsPresenceWithWait(By locator, int timeout) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            try{
                return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
            }catch (Exception e) {
                log.info(locator +"is not availble");
                return Collections.EMPTY_LIST;
            }

        }

        /**
         * This method checks atleast one web element present on DOM and return list, Element height and width is greater than zero
         *
         * @param locator
         * @param timeout
         * @return
         */
        public List<WebElement> getElementsVisibleWithWait(By locator, int timeout) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            try{
                return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
            } catch (Exception e) {
                log.info(locator +"is not availble ");
                return Collections.EMPTY_LIST;
            }
        }

        /**
         * this method checks whether element is visible, enabled and clickable
         *
         * @param locator
         * @param timeout
         */
        @Step("finding the element using : {0}")
        public void clickWhenReady(By locator, int timeout) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        }

        /**
         * wait for elemt visibility and click
         *
         * @param locator
         * @param timeout
         */
        @Step("finding the element using : {0}")
        public void clickwithWait(By locator, int timeout) {
            waitforElementVisibility(locator, timeout).click();
            log.info("clicked on the element" +locator);
        }

        /**
         * wait for element visiblity on UI and then enter text
         *
         * @param locator
         * @param timeout
         * @param value
         */
        @Step("Entering the {2} in to the element: {0}")
        public void sendkeysWithWait(By locator, int timeout, CharSequence... value) {
            waitforElementVisibility(locator, timeout).clear();
            log.info("found the element with the given locator" + locator);
            waitforElementVisibility(locator, timeout).sendKeys(value);
            log.info("entered the given value in the " + locator);
        }

        public @Nullable Alert waitForAlert(int timeout) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            return wait.until(ExpectedConditions.alertIsPresent());
        }


        /**
         * wait for the partial title to appear on page
         *
         * @param partialTitle
         * @param timeout
         * @return
         */
        public String waitForTitlecontains(String partialTitle, int timeout) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            try {
                wait.until(ExpectedConditions.titleContains(partialTitle));
                return driver.getTitle();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /**
         * wait for the title to load on page
         *
         * @param title
         * @param timeout
         * @return
         */
        public String waitForTitle(String title, int timeout) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            try {
                wait.until(ExpectedConditions.titleContains(title));
                return driver.getTitle();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /**
         * fetches  url of the page by
         *
         * @param partialURL
         * @param timeout
         * @return
         */
        public String waitForURLcontains(String partialURL, int timeout) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            try {
                wait.until(ExpectedConditions.urlContains(partialURL));
                return driver.getCurrentUrl();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /**
         * Fetches the URL of the page
         *
         * @param URL
         * @param timeout
         * @return
         */
        public String waitForURL(String URL, int timeout) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            try {
                wait.until(ExpectedConditions.urlToBe(URL));
                return driver.getCurrentUrl();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /**
         * it will wait for the Frame to e attached to DOM and Switch to it
         *
         * @param frameLocator
         * @param timeout
         */
        public void waitForFrame(By frameLocator, int timeout) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));

        }

        public void waitForFrame(String nameOrID, int timeout) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameOrID));

        }

        public void waitForFrame(int frameindex, int timeout) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameindex));
        }

        public boolean waitforwindows(int noOfWindows, int timeout) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            try {
                return wait.until(ExpectedConditions.numberOfWindowsToBe(noOfWindows));
            } catch (Exception e) {
                log.info("Expected windows to be " + noOfWindows);
                return false;
            }
        }

        /**
         * This method checks for the webelemnt presence in Ui for a period of given pollng time
         *
         * @param locator
         * @param timeout
         * @param pollingTime
         * @return
         */
        public WebElement isVisibleEcho(By locator, int timeout, int pollingTime) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofSeconds(2));
            wait.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class)
                    .withMessage("Element is not found");
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        }

        /**
         * This method checks for the webelemnt presence in DOM for a period of given pollng time
         *
         * @param locator
         * @param timeout
         * @param pollingTime
         * @return
         */
        public WebElement presenceOfElementwithFluentWait(By locator, int timeout, int pollingTime) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofSeconds(2));
            wait.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class)
                    .withMessage("Element is not found");
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

        }

        /**
         * This method checks the page loaded with in the given time
         *
         * @param timeout
         * @return
         */
        public boolean isPageLoaded(int timeout) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            String flag = wait.until(ExpectedConditions.jsReturnsValue("return document.readyState ==='complete'")).toString();
            return Boolean.parseBoolean(flag);

        }

        //************Time UTils **************

    public void waitForShortTime() {
        try {
            Thread.sleep(SHORT_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitForMediumTime() {
        try {
            Thread.sleep(MEDIUM_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitForLongTime() {
        try {
            Thread.sleep(LONG_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitForlongerTime() {
        try {
            Thread.sleep(LONGER_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    }



