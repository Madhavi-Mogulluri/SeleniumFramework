package com.qa.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public final class JavaScriptUtil {

    private WebDriver driver;
    private JavascriptExecutor js;

    public JavaScriptUtil(WebDriver driver) {
        this.driver = driver;
        js = (JavascriptExecutor) this.driver;
    }

    /**
     * fectches title of the page by using javascript
     * @return Title
     */
    public String gettitleUsingjs() {
        return js.executeScript(" return document.title").toString();
    }

    /**
     * fetches Url of the page
     * @return URL
     */
    public String getUrlUsingjs() {

        return js.executeScript(" return document.URL").toString();
    }

    /**
     * navigate back to the previous page using js
     */
    public void gobackUsingjs() {

        js.executeScript("history.go(-1)");
    }

    /**
     * navigate forward using js
     */
    public void goforwardUsingjs() {

        js.executeScript("history.go(1)");
    }

    /**
     * refresh the page using js
     */
    public void refreshUsingjs() {
        js.executeScript("history.go(0)");
    }

    /**
     * generate the alret on the page using js
     * @param message
     */
    public void generateAlret(String message) {
        js.executeScript("alert('" +message + "')");
        try{
            Thread.sleep(4000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        driver.switchTo().alert().dismiss();
    }

    /**
     * get all the text displayed on the page
     * @return text
     */
    public String getPageInnerText(){
        return js.executeScript(" return document.documentElement.innerText").toString();
    }

    /**
     * scroll to bottom of the page using js
     */
    public void scrollPageDown(){
        js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
    }

    /**
     * scroll to top of the page using js
     */
    public void scrollPageUp(){
        js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
    }

    /**
     * scroll to specific height  of the page using js
     * @param height
     */
    public void scrollToHeight(String height){
        js.executeScript("window.scrollTo(0,'"+ height +"')");
    }

    /**
     * Scroll to web element using js
     * @param element
     */
    public void scrollIntoView(WebElement element){
        js.executeScript("arguments[0].scrollIntoView(true);",element);
    }

    public void drawBorder(WebElement element){
        js.executeScript("arguments[0].style.border = '3px solid red'", element);
    }

    /**
     * Highlight the element
     * @param element
     */
    public void flash(WebElement element) {
        String bgcolor = element.getCssValue("backgroundColor");//blue
        for (int i = 0; i < 20; i++) {
            changeColor("rgb(0,200,0)", element);// green
            changeColor(bgcolor, element);// blue
        }
    }

    private void changeColor(String color, WebElement element){
        js.executeScript( "arguments[0].style.border='15px solid " + color + "'", element);
    }

    public void zoomChromeandEdge(String zoomPercentage ){
        js.executeScript("document.body.style.zoom = '"+zoomPercentage+ "%'");
    }


    public void zoomFirefox(String zoomPercentage ){
        js.executeScript("document.body.style.Moztransform = 'scale("+zoomPercentage+")'");
    }

    public void jsClick(WebElement element){
        js.executeScript("arguments[0].click()",element);
    }

    public void sendkeyswithJs(String id, String value){
        js.executeScript("document.findElementById('"+id +"').value = '"+ value + "'");
    }

}
