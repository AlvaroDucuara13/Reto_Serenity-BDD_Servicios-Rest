package co.com.sofka.stepdefinition.step;

import co.com.sofka.models.PatchModel.PatchResponse;
import co.com.sofka.models.datosRandomModel.DatosModelRandom;
import co.com.sofka.questions.PatchDataQuestion;
import co.com.sofka.util.ReadFileJson;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import static co.com.sofka.tasks.DoDelete.doDelete;
import static co.com.sofka.tasks.DoPatch.doPatch;
import static co.com.sofka.util.Log4jValues.LOG4J_PROPERTIES_FILE_PATH;
import static co.com.sofka.util.Log4jValues.USER_DIR;
import static co.com.sofka.util.Persona.generarPersonasRandom;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;

public class PatchDeleteCustomerStep {

    private static final String URLBASE = "https://reqres.in/";
    private static final String RESOURCE = "api/users/2";
    private final Actor actor = Actor.named("AlvaroD");
    private String bodyRequest;
    private DatosModelRandom random;

    private ReadFileJson fileJson;
    private static final Logger LOGGER = Logger.getLogger(PatchDeleteCustomerStep.class);

    @Given("el usuario esta en la plataforma y desea actualizar datos")
    public void elUsuarioEstaEnLaPlataformaYDeseaActualizarDatos() {

        try{
            PropertyConfigurator.configure(USER_DIR.getValue() + LOG4J_PROPERTIES_FILE_PATH.getValue());
            actor.can(CallAnApi.at(URLBASE));


            random = generarPersonasRandom();
            fileJson = new ReadFileJson(random.getJob(), random.getFullName() );
            String SaveNameJob = fileJson.ConvertPatch();
            bodyRequest = SaveNameJob;
            LOGGER.info(bodyRequest);

        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);

        }

    }

    @When("el usuario logra actualizar los datos")
    public void elUsuarioLograActualizarLosDatos() {
        try{

            actor.attemptsTo(
                    doPatch()
                            .usingTheResource(RESOURCE)
                            .andBodyRequest(bodyRequest)
            );

        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);

        }


    }
    @Then("el usuario obtendra un codigo de respuesta exitosa y podra ver sus datos actualizados")
    public void elUsuarioObtendraUnCodigoDeRespuestaExitosaYPodraVerSusDatosActualizados() {
        try{

            LastResponse.received().answeredBy(actor).prettyPrint();

            PatchResponse Customer = new PatchDataQuestion().answeredBy(actor);
            actor.should(
                    seeThatResponse(
                            "El código de respuesta debe ser: " + HttpStatus.SC_OK,
                            validatableResponse -> validatableResponse.statusCode(HttpStatus.SC_OK)),
                    seeThat(
                            "El nombre del usuario es:", x -> Customer.getName(), equalTo(random.getFullName())),
                    seeThat(
                            "El trabajo del usuario es:", x -> Customer.getJob(), equalTo(random.getJob()))
            );

        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);

        }

    }

    @Given("el usuario esta en la plataforma y desea eliminar datos")
    public void elUsuarioEstaEnLaPlataformaYDeseaEliminarDatos() {

        try{
            PropertyConfigurator.configure(USER_DIR.getValue() + LOG4J_PROPERTIES_FILE_PATH.getValue());
            actor.can(CallAnApi.at(URLBASE));

        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
        }

    }

    @When("el usuario logra eliminar los datos")
    public void elUsuarioLograEliminarLosDatos() {
        try{
            actor.attemptsTo(
                    doDelete()
                            .usingTheResource(RESOURCE)
            );
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);

        }
    }
    @Then("el usuario obtendra un codigo de respuesta")
    public void elUsuarioObtendraUnCodigoDeRespuesta() {
        try{
            LastResponse.received().answeredBy(actor).prettyPrint();
            actor.should(
                    seeThatResponse(
                            "El código de respuesta debe ser: " + HttpStatus.SC_NO_CONTENT,
                            validatableResponse -> validatableResponse.statusCode(HttpStatus.SC_NO_CONTENT))
            );

        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);

        }

    }
}
