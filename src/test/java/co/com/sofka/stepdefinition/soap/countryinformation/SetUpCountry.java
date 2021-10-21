package co.com.sofka.stepdefinition.soap.countryinformation;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class SetUpCountry {
    protected static final String URL_BASE = "http://webservices.oorsprong.org";
    protected static final String RESOURCE = "/websamples.countryinfo/CountryInfoService.wso";
    protected String bodyRequest;
    protected Actor actor;

    private void setUpCountryActor(){
        actor = Actor.named("Yeison");
    }

    private void setUpCountryActorAndApi(){
        actor.can(CallAnApi.at(URL_BASE));
    }

    protected void setUpCountry(){
        setUpCountryActor();
        setUpCountryActorAndApi();
    }

    protected HashMap<String, Object> headers(){
        HashMap<String, Object> headersCollection = new HashMap<>();
        headersCollection.put("Content-Type", "text/xml;charset=UTF-8");
        headersCollection.put("SOAPAction", "");
        return headersCollection;
    }

    protected String fromLastResponseBy(Actor actor){
        return  new String(
                LastResponse.received().answeredBy(actor).asByteArray(),
                StandardCharsets.UTF_8
        );
    }
}
