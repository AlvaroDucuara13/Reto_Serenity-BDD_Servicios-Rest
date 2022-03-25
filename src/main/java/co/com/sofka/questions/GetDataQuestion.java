package co.com.sofka.questions;

import co.com.sofka.models.getModel.GetResponse;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class GetDataQuestion implements Question  {

    @Override
    public GetResponse answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(GetResponse.class);
    }
}
