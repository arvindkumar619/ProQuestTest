# ProQuestAutomationTest

## Problem statement
Write the automated tests for 
*  Test 1: Navigate to www.google.com and search for ‘ProQuest’. Write the titles of all results on the first page to a text file on the operating system
*  Test 2: Open the ProQuest website from the results, search for ‘QA’ in the top nav, and take a screenshot

## Overview
This is a maven project using Selenium and testNG framework written in Java. This contains 2 tests for the given tasks as per problem statement.

## Prerequisites

*  Java JDK 1.8.0_231
*  Eclipse IDE luna 2(4.4.2)
*  Maven-3.6.3
*  Selenium-java-3.141.59
*  Chromedriver
*  TestNG

## Instructions to setup Automation framework

*  Install Java JDK, JRE and configure JAVA_HOME path under Environment->System Variables,Include 'jdk/bin' directory in 'PATH' variable.

*  Install Eclipse IDE and configure the workspace required, configure Installed JRE's via preferences.

*  Download and Install Maven and add 'MAVEN_HOME' Environment Variable, Include 'maven/bin' directory in 'PATH' variable.

*  Download the Selenium Java Client Driver (3.141.59) extract the contents of this ZIP file which contains all the JAR files that we would later import on Eclipse.

*  Configure Eclipse IDE with Selenium WebDriver and Add externals JAR's for the required project by Right Click->Properties->Java Build Path->Add External Jars and libraries from the extracted contents above including 'lib' folder as well.

*  Add/Install TestNG plugin in eclipse select the project in eclipse project explorer select -> Help / Install New Software, Enter the update site URL in "Work with:" field: http://dl.bintray.com/testng-team/testng-eclipse-release/.
Make sure the check box next to URL is checked and click Next.

* Download and install chromeDriver. Then set the path 'webdriver.chrome.driver' to installed chromedriver.exe directory. Alternatively this path can be set before running the test in java class using setProperty


## Steps followed for AutomationTest project Setup

* Created a new Maven project - AutomationTest

* Created a new class ProQuestCodeTest under package com.ProQuest.selenium.Test.
  This class contains the tests for the tasks given in the problem statement
  
* Created a new class CommonUtils under package com.ProQuest.selenium.Utils.
  This class contains static Utility methods such as taking screenshot and writing to a new file
  
* Created a new class Constants under package com.ProQuest.selenium.Utils.
  This class contains const Strings using in the test methods
  
* Generate testNG.xml file using TestNG eclipse plugin. This plugin automatically generates testng.xml and contains all the Test configuration in an XML file, and is used to run and organize our test. 

* Running testng.xml file, it will start running @test methods from the ProQuestCodeTest class as mentioned in the XML and generate the report.

* This testng.xml needs to be configured in pom.xml under maven-surefire-plugin plugin.
  This helps to run this project from 'mvn' commands
  
### Approach followed in ProQuestCodeTest test Class

* Tests are created using @Test annotation. 
  ProQuestCodeTest contains 2 tests for each task given in the problem statement

* @BeforeTest to contain all setUp need to be done before running tests.
  In ProQuestCodeTest BeforeTest need to set path variable for chrome driver. This is optional If path is set already in Environment variable. Creating an instance for chrome driver with necessary timeouts
  
* @AfterTest to contains all clean up needs to be done after running tests.
  In ProQuestCodeTest AfterTest closing the driver instance created.
  
* @Test - googlesearchProquestTest. 
  Load a new web page with google URL.
  Enter the ProQuest text in search box and then click search button.
  Identify titles in the search results.
  Loop through the searched titles and appending it to string.
  Write Titles to a file.
  
* @Test - topNavSearch.
  Navigate directly to the ProQuest website.
  Wait added to load popup frame and switch to that frame.
  Complete actions for cookies.
  Switch back control to Parent frame.
  Identify & Click on top nav search button.
  Submit form with 'QA' as search text.
  Capture screenshot for QA search results.
  
* CommonUtil class. 
  captureScreenshot - to capture the screenshot. It takes webdriver(for which screenshot needs to be taken) and filePath to which the screenshot image needs to write.
  fileWrite - to write content to a given file. It taken String to be written and filePath where file needs to be created with the content 
   
   
## Test Execution

Tests can be run in below mentioned ways

**Approach 1**
Using Eclipse IDE - Select project ->Run as -> TestNg Test

**Approach 2**
Using Eclipse IDE - Right-click on testNg.xml generated earlier and Run as -> TestNg Suite

**Approach 3**
 Using maven. testNg.xml is generated earlier and the same is configured in pom.xml under maven-surefire-plugin.
 Goto command prompt and run below commands
  `mvn compile` - for compilation
  `mvn test` - It will execute tests and generate surefile-reports.
  
## Test Results

* ProQuestSearchResultsTitles.txt contains all the titles in a text file.
* QA_search_ScreenShot.png contains the screenshot of QA search page

## Known issues

* Ignored 'People also ask' from task1
* ignored 'ProQuest (@ProQuest) · Twitter' from task1s
  
  

