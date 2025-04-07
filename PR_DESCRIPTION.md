# Update Extent Reports Configuration

## Changes Made
1. **Modified Extent Reports Configuration**:
   - Updated the `extent.properties` file to save individual reports for each test run
   - Changed output paths to create timestamped folders for each test execution
   - Reports are now organized by date and time (format: yyyy-MM-dd_HH-mm-ss)

2. **Fixed Report Overwriting Issue**:
   - Previously, each test run would overwrite the previous report
   - Now each test run creates a separate report in its own timestamped folder
   - Historical test reports are preserved

## Technical Details
- Changed report path from `target/SparkReport/Spark.html` to `Spark.html` (relative path)
- Updated screenshot path from `target/screenshots/` to `screenshots/` (relative path)
- Enabled date/time pattern for base folder to organize reports chronologically

## Benefits
- Complete history of test executions is now preserved
- Test reports from different runs can be easily compared
- Better organization of test artifacts
- Simplified test result analysis

## Validation
- Tested the solution by running tests multiple times
- Confirmed that each test run creates a new report in its own timestamped folder
- Verified that the reports contain correct test information 