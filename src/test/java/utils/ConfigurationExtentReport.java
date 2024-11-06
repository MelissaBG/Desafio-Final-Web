package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ConfigurationExtentReport {
    private static ExtentReports extent;
    private static ExtentTest test;

    public static void initializeReport() {
        if (extent == null) { // Inicializa o relatório apenas se ainda não foi criado
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/Reports/extentSparkReport.html");
            sparkReporter.config().setDocumentTitle("Demoblaze Test Report");
            sparkReporter.config().setReportName("Demoblaze Automation Test Execution");
            sparkReporter.config().setTheme(Theme.DARK);
            sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
        }
    }

    public static ExtentTest createTest(String testName) {
        test = extent.createTest(testName);
        return test;
    }

    public static ExtentTest getTest() {
        return test;
    }

    public static String captureScreenshot(WebDriver driver, String screenshotName) throws IOException {
        String extentReportPath = System.getProperty("user.dir") + "/Reports";
        String screenshotPath = extentReportPath + "/screenshots";

        // Cria o diretório de capturas de tela, se não existir
        File screenshotDir = new File(screenshotPath);
        if (!screenshotDir.exists()) {
            screenshotDir.mkdirs();
        }

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String filePath = screenshotPath + "/" + screenshotName + ".png";

        FileUtils.copyFile(src, new File(filePath));
        return filePath;
    }

    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}
