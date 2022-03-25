package co.com.sofka.tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

public class DoPost implements Task {
    private String resource;
    private String bodyRequest;

    public DoPost usingTheResource(String resource) {
        this.resource = resource;
        return this;
    }

    public DoPost andBodyRequest(String bodyRequest) {
        this.bodyRequest = bodyRequest;
        return this;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to(resource)
                        .with(
                                requestSpecification ->requestSpecification.relaxedHTTPSValidation().log().all()
                                        .contentType(ContentType.JSON)
                                        .body(bodyRequest)
                        )
        );

    }

    public static DoPost doPost(){
        return new DoPost();
    }

}
