package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features= "src/test/java/cucumber", glue= "selenium_framework_design.step_definition"
,monochrome=true,plugin= {"html:target/cucumber.html"})
public class TestngRunner extends AbstractTestNGCucumberTests {

}
