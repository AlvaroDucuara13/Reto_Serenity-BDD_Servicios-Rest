package co.com.sofka.tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Delete;

public class DoDelete implements Task {
    private String resource;
    private String bodyRequest;

    public DoDelete usingTheResource(String resource) {
        this.resource = resource;
        return this;
    }

    public DoDelete andBodyRequest(String bodyRequest) {
        this.bodyRequest = bodyRequest;
        return this;
    }
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Delete.from(resource)
                        .with(
                                requestSpecification -> requestSpecification.contentType(ContentType.JSON)
                                        .relaxedHTTPSValidation().log().all()
                        )
        );

    }
    public static DoDelete doDelete(){
        return new DoDelete();
    }
}
