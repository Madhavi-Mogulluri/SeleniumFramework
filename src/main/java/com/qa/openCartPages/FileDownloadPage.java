package com.qa.openCartPages;

import com.qa.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class FileDownloadPage{

private  WebDriver driver;
private ElementUtil elementUtil;

private final By fileLink = By.cssSelector("a[href=\"download/tmpkum2jlxi.txt\"]");

    public FileDownloadPage(WebDriver driver){
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }


    public  void fileDownload(){
        elementUtil.click(fileLink);

    }


        public  boolean isFileDownloaded(String dir, String fileName) {
            File file = new File(dir + "/" + fileName);
            return file.exists();
        }




}
