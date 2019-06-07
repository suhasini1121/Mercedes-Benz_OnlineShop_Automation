<<<<<<< HEAD
# Mercedes-Benz_Online_Shop_Automation
Automation of Online Shop Application of Mercedes-Benz
=======
# Mercedes-Benz_OnlineShop_Automation
Automation of online shop of Mercedes-Benz

Framework and Tools Used:
Programming Language: Java 1.7
IDE: Eclipse Oxygen.2
Build Tool: Maven
Test Automation Tool: Selenium WebDriver - 3.141.59
Automation Framework: Page Object Model with TestNg
Reporting Tool: Extent Reports

About the testcases that has automated:
1.	Automated one end to end test script. You can find the automated test script under src/test/java
EndtoEnd_TC1 – is an end to end test case which covers the positive flow of the application
Test Case	Status
EndtoEnd_TC1	Pass

2.	The test can be run by testng.xml under the path src/test/resources
There are 2 testng.xml files.

EndtoEndtestng.xml – runs on Chrome browser and is a single test run

Parallel_Testng.xml – runs on Chrome and Firefox browsers parallelly

To execute the automation test case:

1.	Download the project from the GitHub repository

2.	Import the project into eclipse as an existing maven project. 
3.	Open command prompt and go to the project location and give the command below:

mvn install

4.	In eclipse, go to project and Clean the project.

5.	To execute the test script on Chrome browser:
Right click on EndtoEndtestng.xml file under src/test/resources in the Project Explorer  Run As  TestNG Suite

6.	To view the extent reports for the test run: (Once the test cases have been run)
Extent Reports are placed under the Reports folder under the Project.

Open the report by selecting the web browser.

7.	To run the test script parallelly on both Chrome and Firefox browsers:

Right click on Parallel_Testng.xml file under src/test/resources in the Project Explorer  Run As  TestNG Suite

Report will be same in the Reports folder with updation.

Open the report by selecting the web browser.
>>>>>>> b83ea773b0b8d133c665aa5070561663159fd687
