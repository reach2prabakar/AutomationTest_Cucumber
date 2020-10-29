# Automation Assessment


> TO validate the Westpac retirement calculator with the given Acceptance Criteria.

## TOOLS
[JAVA V_1.7 or greater](https://www.java.com/en/download/)<br/>
[Maven Build](https://maven.apache.org/download.cgi)<br/>
[Cucumber BDD Framework](https://mvnrepository.com/artifact/io.cucumber/cucumber-java/6.3.0)<br/>
[Simple JsonParser , Rest Assured](https://mvnrepository.com/artifact/com.googlecode.json-simple/json-simple)

## Project Description
> This project is to navigate to Westpac retirement calculator and validate with the acceptance
<br/>Also to validate the proposed savings.

## Project Highlights
> Project is build on BDD framework with the POM and  part of modular approach. .<br/>
> Java/Selenium for coding<br/>
> log4j2 is used to capture logs<br/>
> Hamcrest assertion for assertion<br/>
>Report - 2 type of report 
	>HTML report
	>JVM (Graphical report) <br/>



## Installation/Prerequisites for the test

Java 1.7 or higher
<br/>Maven 
<br/>Intellij/Eclipse (Intellij preferable) / Command prompt to run the test
<br/>Set Java_Home and Maven_Home in environmental variables

To check if Java and maven are installed in your Machine:

```sh
Java -version
Mvn -version
```

## Test run and data set up  
  ![scenario](/src/test/resources/imageForGit/Feature.JPG)
  
  
  To navigate to different menu , all you need is to add the menu name in <br/>
   | headerMenu | uberMenu  | subMenuItem    | <br/>
   no additional code is required
   
   As mentioned in the fig (feature file) you can keep adding data to the run the test with multiple sets <br/>
     | currentAge | employment    | salary | kiwiBalance | kiwiSaver | volContribution | frequent    | riskProfile  | savingGoal | projection |
     
   
  ## Validations
  
  > The UI data asserted with the business requirement, if any step fail the test scenario will fail.
  
  > Result will be updated with number of test scenario and test step passed
  

## How to run the test

Download the code from GITHUB 

```sh
git pull origin master <br/>
https://github.com/reach2prabakar/RecruitIt_TradeMe_AutomationFramework.git
```

Open the project in any IDE (Intellij preferred)
You can right click on the feature file and select Run (not preferred to run in suite level)

Mention the tag for the scenario to run in the feature file and configure Run with the specific tag to run the test

![configure](/src/test/resources/imageForGit/Configure.JPG)

If you want to run the project as maven Build 

Open the command prompt, Navigate to your project folder
```sh
C:\Users> cd path to your folder
C:\Users\Projectfolder> mvn clean install
```

## Reporting

For greater understanding cucumber JVM is plugged in with the framework.

Once all the test executed,
<br/>Navigate to the project folder ->\target\cucumber-html-reports
>### overview-features.html

![Scenario](/src/test/resources/imageForGit/Report.JPG)

## Contributing

1. Fork it (<https://github.com/yourname/yourproject/fork>)
2. Create your feature branch (`git checkout -b feature/fooBar`)
3. Commit your changes (`git commit -am 'Add some fooBar'`)
4. Push to the branch (`git push origin feature/fooBar`)
5. Create a new Pull Request

