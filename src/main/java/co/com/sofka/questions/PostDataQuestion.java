package co.com.sofka.questions;

import co.com.sofka.models.postModel.PostResponseItem;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class PostDataQuestion implements Question {
    @Override
    public PostResponseItem answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(PostResponseItem.class);
    }
}
