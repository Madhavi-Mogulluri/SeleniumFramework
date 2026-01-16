package com.qa.tests;

import com.qa.base.BaseTest;
import com.qa.openCartPages.FileDownloadPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FileDownloadTest extends BaseTest {

    @Test
    public void verifyFileDownloadInChrome(){
        FileDownloadPage fileDownloadPage = loginpage.gotoanotherURL();
        fileDownloadPage.fileDownload();
        Assert.assertTrue(fileDownloadPage.isFileDownloaded("/Users/madhugannu/eclipse-workspace/SeleniumFramework/downloads","tmpkum2jlxi.txt"));

    }

}
