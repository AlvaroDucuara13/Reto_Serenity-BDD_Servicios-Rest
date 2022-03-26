package co.com.sofka.tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Patch;

public class DoPatch implements Task {
    private String resource;
    private String bodyRequest;

    public DoPatch usingTheResource(String resource) {
        this.resource = resource;
        return this;
    }

    public DoPatch andBodyRequest(String bodyRequest) {
        this.bodyRequest = bodyRequest;
        return this;
    }


    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Patch.to(resource)
                        .with(
                                requestSpecification ->requestSpecification.relaxedHTTPSValidation().log().all()
                                        .contentType(ContentType.JSON)
                                        .body(bodyRequest)
                        )
        );

    }
    public static DoPatch doPatch(){
        return new DoPatch();
    }
}
