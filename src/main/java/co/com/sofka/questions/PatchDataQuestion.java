package co.com.sofka.questions;

import co.com.sofka.models.PatchModel.PatchResponse;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class PatchDataQuestion implements Question {


    @Override
    public PatchResponse answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(PatchResponse.class);
    }
}
