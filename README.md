# Selenium sample automation framework
This is a sample test Automation Framework utilizing selenium with java built to automate 6 tests for https://www.demoblaze.com/

Project uses TestNG framework. extent reports has been integrated with the help of testng listners and log4j has been integrated

design pattern followed is Page Object Model. Browsers used are chrome and firefox 

xml file has been created to run tests in 3 parallel threads. Test cases are grouped in to regression cases and sanity cases. The group can be chosen by passing groupname as a parmeter. This was achieved by adding bean scripts to xml

Screen shots will be attached on Test pass and failure

BrowserFactory and DriverFactory classes help achieve thread safety.


# How To Run
in eclipse Run>Run Configurations.

select Suite(testng.xml)

open arguments tab

pass VM aarguments('-DgroupName = sanity' to run sanity test cases and '-DgroupName = regression' to run regression test cases

apply and run

# report and logs

Report can be found in folder /test-output/reports. Time stamp will be appeneded to the name

logs can be found in /logs folder. Time stamp will be appeneded to the name
 
