package co.com.sofka.runner.soap.countryinformation.fullinformation;


import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberSerenityRunner;

import org.junit.runner.RunWith;



@RunWith(CucumberSerenityRunner.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = {"src/test/resources/features/countryinfo/fullcountryinfo.feature"},
        glue = {"co.com.sofka.stepdefinition.soap.countryinformation.fullcountryinfo"}
)

public class FullCountryInformationTest {
}
