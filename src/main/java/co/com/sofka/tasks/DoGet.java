package co.com.sofka.tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

public class DoGet implements Task {

    private String Resource;

    public DoGet usingTheResource(String resource){
        this.Resource=resource;
        return this;
    }
    

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource(Resource).with(
                        requestSpecification -> requestSpecification.contentType(ContentType.JSON)
                                .relaxedHTTPSValidation().log().all()
                )
        );
    }

    public static DoGet doGet(){
        return new DoGet();
    }
}
