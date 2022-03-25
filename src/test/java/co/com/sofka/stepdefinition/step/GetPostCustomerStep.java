package co.com.sofka.stepdefinition.step;

import co.com.sofka.models.datosRandomModel.DatosModelRandom;
import co.com.sofka.models.getModel.GetResponse;
import co.com.sofka.models.postModel.PostResponseItem;
import co.com.sofka.questions.GetDataQuestion;
import co.com.sofka.questions.PostDataQuestion;
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

import static co.com.sofka.tasks.DoGet.doGet;
import static co.com.sofka.tasks.DoPost.doPost;
import static co.com.sofka.util.Log4jValues.LOG4J_PROPERTIES_FILE_PATH;
import static co.com.sofka.util.Log4jValues.USER_DIR;
import static co.com.sofka.util.NumbersToAssertion.ONE;
import static co.com.sofka.util.Persona.generarPersonasRandom;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;


public class GetPostCustomerStep {

    private static final String URL_BASE = "https://jsonplaceholder.typicode.com/";
    private static final String RESOURCE = "posts/1";
    private static final String RESOURCECOMMENTS = "comments";
    private final Actor actor = Actor.named("AlvaroD");
    private String bodyRequest;
    private DatosModelRandom random;

    private ReadFileJson fileJson;
    private static final Logger LOGGER = Logger.getLogger(GetPostCustomerStep.class);


    @Given("el usuario esta en la pagina y desea ver su informacion")
    public void elUsuarioEstaEnLaPaginaYDeseaVerSuInformacion() {

        try{

            PropertyConfigurator.configure(USER_DIR.getValue() + LOG4J_PROPERTIES_FILE_PATH.getValue());

            actor.can(CallAnApi.at(URL_BASE));

        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
        }
    }

    @When("el usuario realiza la accion para obtener su informacion")
    public void elUsuarioRealizaLaAccionParaObtenerSuInformacion() {

        try{

            actor.attemptsTo(
                    doGet().usingTheResource(RESOURCE)
            );
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);

        }

    }
    @Then("el usuario lograra visualizar una lista con sus datos")
    public void elUsuarioLograraVisualizarUnaListaConSusDatos() {

        try{
            LastResponse.received().answeredBy(actor).prettyPrint();

            GetResponse Customer = new  GetDataQuestion().answeredBy(actor);

            actor.should(
                    seeThatResponse("codigo de respuesta: "+ HttpStatus.SC_OK,
                            validatableResponse -> validatableResponse.statusCode(HttpStatus.SC_OK) ),

                    seeThat(
                            "El id de usuario es :", x -> Customer.getUserId(), equalTo(ONE.getValue()))

            );
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);

        }

    }

    @Given("el usuario esta en la pagina y desea anexar una serie de datos para registrar un comentario")
    public void elUsuarioEstaEnLaPaginaYDeseaAnexarUnaSerieDeDatosParaRegistrarUnComentario()  {

        try{
            PropertyConfigurator.configure(USER_DIR.getValue() + LOG4J_PROPERTIES_FILE_PATH.getValue());
            actor.can(CallAnApi.at(URL_BASE));


            random = generarPersonasRandom();
            fileJson = new ReadFileJson(random.getPostId(), random.getFullName(), random.getEmail() );
            String SaveNameJob = fileJson.ConvertPost();
            bodyRequest = SaveNameJob;
            LOGGER.info(bodyRequest);

        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);

        }

    }

    @When("el usuario realiza la accion")
    public void elUsuarioRealizaLaAccion() {

        try{

            actor.attemptsTo(
                    doPost()
                            .usingTheResource(RESOURCECOMMENTS)
                            .andBodyRequest(bodyRequest)
            );

        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);

        }

    }

    @Then("el usuario lograra visualizar sus datos registrados y el comentario")
    public void elUsuarioLograraVisualizarSusDatosRegistradosYElComentario() {
        try{

            LastResponse.received().answeredBy(actor).prettyPrint();

            PostResponseItem Customer = new PostDataQuestion().answeredBy(actor);
            actor.should(
                    seeThatResponse(
                            "El código de respuesta debe ser: " + HttpStatus.SC_CREATED,
                            validatableResponse -> validatableResponse.statusCode(HttpStatus.SC_CREATED)),
                    seeThat(
                            "El correo electronico es:", x -> Customer.getEmail(),equalTo(random.getEmail())),
                    seeThat(
                            "El PostId es:", x -> Customer.getPostId(),equalTo(Integer.parseInt(random.getPostId())))
            );

        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);

        }
    }

}
