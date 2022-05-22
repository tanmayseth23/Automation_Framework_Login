# Automation_Framework_Login

How to import this Project :

Method I:

Download Zip from (https://github.com/tanmayseth23/Automation_Framework_Login) and extract the same into one folder
In intellij >>  Open Project from File System
click Directory to select Import Source Path and select the folder 
Next Finish

Click on Trust Project if the prompt pops up.
It may also ask to download all the maven dependencies so we have to select that option as well.

How to Run the Project :

Run Testng.xml => This will run all the tests present

Improvements Which can be done in the framework:-

1. Currently I have used print statements (Sysouts) but instead we should be using Loggers & could have augmented ExtentReports or Allure Report which can be used to generate a nice Consolidated report.
2. We can Implements the necessary Listeners in TestNg for various features like running failed Test cases or logging in the case of Failed tests,etc.
3. Write the java doc for each Class & methods.
4. Separation of some common code written for each method into a common code.
5. Augment some tests related to status code =429 (too many requests) , this occurs when an API is hit mutiple times more than the rate limiting time or requests set wrt algorithm & even retrying after certain duration.
6.  Some End to end Tests which can be like Register => Login => reseting => Login . However , I have used the registered data only to login so partially which is covered.
7.  We can also use Apache POI & built ExcelUtility to provide the data in the data Provider class.
8.  We can also add schema Validator tests.
9.  We need to do the performance testing of the provided APIs as well as part of our Testing & do the bemchmarking.
10. Certain more design patterns can be used like SOLID Priciples,etc.
11. Like I have implemented a generic response(IRestResponse), a similar approach for APIs which provide common response if it is possible.

Uncovered Some Minor Issues:-

1. For Password field minimum no of characters are required are 6. (>=6 & not >6)
2. For gender field - it is taking only "o" & not "O". 
3. For email field - some validations are missing.

