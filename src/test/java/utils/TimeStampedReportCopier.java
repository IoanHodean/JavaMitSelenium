package utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

/**
 * TestNG Listener that copies the Extent Report file to a timestamped version after test execution.
 * This ensures that each test run produces a unique report file.
 */
public class TimeStampedReportCopier implements ITestListener {
    private static final Logger LOGGER = Logger.getLogger(TimeStampedReportCopier.class.getName());
    private static final String REPORT_FILE = "target/SparkReport/TestReport.html";
    private static final String TIMESTAMP_FORMAT = "yyyy-MM-dd_HH-mm-ss";

    /**
     * After all tests have completed, copies the standard report file to a timestamped version.
     * 
     * @param context The TestNG test context
     */
    @Override
    public void onFinish(ITestContext context) {
        try {
            String timestamp = new SimpleDateFormat(TIMESTAMP_FORMAT).format(new Date());
            String newFileName = REPORT_FILE.replace(".html", "_" + timestamp + ".html");
            
            Path source = Paths.get(REPORT_FILE);
            Path destination = Paths.get(newFileName);
            
            // Wait a bit to ensure the report file is completely written
            Thread.sleep(1000);
            
            // Only copy if the original file exists
            if (Files.exists(source)) {
                Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
                LOGGER.info("Successfully copied report to timestamped file: " + newFileName);
            } else {
                LOGGER.warning("Could not find report file to copy: " + REPORT_FILE);
            }
        } catch (IOException | InterruptedException e) {
            LOGGER.severe("Error copying report file: " + e.getMessage());
        }
    }

    // Other ITestListener methods that we don't need to implement
    @Override public void onTestStart(ITestResult result) {}
    @Override public void onTestSuccess(ITestResult result) {}
    @Override public void onTestFailure(ITestResult result) {}
    @Override public void onTestSkipped(ITestResult result) {}
    @Override public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
    @Override public void onStart(ITestContext context) {}
} 