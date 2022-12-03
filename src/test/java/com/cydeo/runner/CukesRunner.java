package com.cydeo.runner;

import com.cydeo.utility.DB_Util;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(Cucumber.class)
@CucumberOptions(

        plugin = {
                "html:target/cucumber-report.html",
                "rerun:target/rerun.txt" ,
                "json:target/cucumber-report.json",
                "me.jvt.cucumber.report.PrettyReports:target/cucumber"
        },
        features = "src/test/resources/features" ,
        glue = "com/cydeo/steps",
        dryRun = false,
        tags = "@smoke"
)
public class CukesRunner {

}
// loh





