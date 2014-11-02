libGDX-with-cucumber
====================

A raw libGDX maven project with cucumber set up

for information, help, documentation and the latest version of libGDX please visit their awesome homepage:
http://www.libgdx.com

You can take everything I created and do what it what you like to.
For further information take a loot at the licenses of the projects this is built on. 
(libGDX is under Apache 2.0)

<h2>Requirements</h2>
The only thing you'll need is maven 3.x

<h2> Steps to Take </h2>

1. Fork / Copy it
2. rename YourGame-Classes (one in each subproject) and "yourgame" packages
3. invoke "mvn clean install" on the base directory
4. ignore the plugin execution not covered by lifecycle configuration error - or tell me how to fix it

<h2> How to execute Tests </h2>

There are two ways of executing the tests
a) mvn test / mvn install / mvn package ... will execute all of your tests
b) run dhbw.karlsruhe.it.yourgame.junit.TestSuite as a JUnit Test in your favourite IDE.

Maven will generate a pretty report in core/target/cucumber

<h2> Notes on the project structure </h2>

99% of your code will be in the core subproject. Your tests will fall into those 99%!

- Unit Tests should be placed in dhbw.karlsruhe.it.yourgame.junit and should be named like "ExampleJUnit"
- Unit Tests have to be added to the TestSuite (dhbw.karlsruhe.it.yourgame.junit). There's also a brief description provided
- Cucumber Steps should be placed in dhbw.karlsruhe.it.yourgame.junit.cucumber and should end with "CucumberSteps"
- Cucumber Feature Files should be placed in src/test/resources
- You must not add cucumber steps to neither the TestSuite nor the CukeRunnerJUnit!

<h2> Additional Help </h2>
To enable more comfortable unit / functional testing I provided a little TestHelper class. Take a look at them

<h2> Contact </h2>
If you have questions / proposals feel free to send me a mail to thetwo@gmx.net
