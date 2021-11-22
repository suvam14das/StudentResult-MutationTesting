# MutationTesting-Pitest

In this work, we have performed mutation testing using pitest mutation tool. For writing test cases JUnit 5 has been used.

For testing purpose, pre existing code base given at https://github.com/ArturT/Game-of-Life-in-Java has been used.

Follwing steps needs to be followed to setup the mutation testing using pitest

<h5>POM inclusion to setup project for mutation testing</h5>
Important inclusion in POM :

```xml
<!-- Dependency to include the pitest in the code -->
<dependency>
    <groupId>org.pitest</groupId>
    <artifactId>pitest-parent</artifactId>
    <version>1.7.3</version>
    <type>pom</type>
</dependency>
```
```xml
<!-- Dependency to generate reports for pitest with junit5 -->
<dependency>
    <groupId>org.pitest</groupId>
    <artifactId>pitest-junit5-plugin</artifactId>
    <version>0.8</version>
</dependency>
```

Apart from above dependencies, plugin for pitest in maven needs to setup.
This can be done in following way.
```xml
<plugin>
    <groupId>org.pitest</groupId>
    <artifactId>pitest-maven</artifactId>
    <version>1.7.3</version>
    <configuration>
        <targetClasses>
            <param>path.to.target.classes</param>
        </targetClasses>
        <targetTests>
            <param>path.to.target.test.classes</param>
        </targetTests>
    </configuration>
</plugin>
```



<h5>Execution of project to generate execute mutation testing and generate report</h5>

This project needs to be build at first and then mutation specific test can be done on the bulded class files.
The target Junit test folder location and target compiled class location are configured in POM

Execute following commands to perform mutation testing.

<b>
<ol>
<li>mvn install
<li>mvn org.pitest:pitest-maven:mutationCoverage
</ol>
</b>

<i>Note: These commands needs to be executed from the directory which contains the appropriate POM.</i>

After successful execution the mutation reports are present in <b>target/pit-test/YYYYMMDDHHMI</b> directory
Open the .html file in browser to check the report.