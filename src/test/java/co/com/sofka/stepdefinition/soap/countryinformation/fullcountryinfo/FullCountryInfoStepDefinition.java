package co.com.sofka.stepdefinition.soap.countryinformation.fullcountryinfo;

import co.com.sofka.stepdefinition.soap.countryinformation.SetUpCountry;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static co.com.sofka.questions.ReturnStringValue.systemValue;
import static co.com.sofka.tasks.countryinfo.DoPost.doPost;
import static co.com.sofka.util.FileUtilities.readFile;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.containsString;

public class FullCountryInfoStepDefinition extends SetUpCountry {

    private static final String FULL_COUNTRY_INFO_XML = System.getProperty("user.dir") + "\\src\\test\\resources\\countryinformation\\fullcountryinfo.xml";
    private static final String CODIGO_ISO_PAIS = "[stringCodigoIso]";



    @Given("^que el usuario necesita consultar la información completa de un pais, con el código ISO (.+)$")
    public void writeCodeIso(String iso) {
        setUpCountry();
        bodyRequest = defineBodyRequest(iso);
    }
    @Given("^que el usuario ingresa un código ISO no valido (.+)$")
    public void writeCodeIsoInvalid(String iso) {
        setUpCountry();
        bodyRequest = defineBodyRequest(iso);
    }

    @When("el usuario de ejecuta una consulta")
    public void search() {
        actor.attemptsTo(
                doPost().
                        usingThe(RESOURCE).
                        with(headers()).
                        and(bodyRequest)
        );
    }

    @Then("el ususario debería obtener toda la informacion del pais")
    public void resul() {
        actor.should(
                seeThatResponse(
                        "El código de respuesta HTTP debe ser: " + SC_OK,
                        response -> response
                                .statusCode(SC_OK)
                ),
                seeThat(
                        "Mensaje: ",
                        systemValue(fromLastResponseBy(actor)),
                        containsString(" <m:sName>Colombia</m:sName>")

                )
        );

        System.out.println( LastResponse.received().answeredBy(actor)
                .asString());
    }

    @Then("^el ususario debería obtener como respuesta: (.+)$")
    public void result(String message) {
        actor.should(
                seeThatResponse(
                        "El código de respuesta HTTP debe ser: " + SC_OK,
                        response -> response
                                .statusCode(SC_OK)
                ),
                seeThat(
                        "Mensaje: ",
                        systemValue(fromLastResponseBy(actor)),
                        containsString("<m:sName>Country not found in the database</m:sName>")

                )
        );

        System.out.println( LastResponse.received().answeredBy(actor)
                .asString());
    }




    private String defineBodyRequest(String iso){
        return readFile(FULL_COUNTRY_INFO_XML)
                .replace(CODIGO_ISO_PAIS, iso.toString());

    }


}
