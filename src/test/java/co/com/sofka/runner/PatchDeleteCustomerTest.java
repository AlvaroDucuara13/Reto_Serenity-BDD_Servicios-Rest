package co.com.sofka.runner;



import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = {"src/test/resources/feature/AgregarParcheEliminar.feature"},
        glue = {"co.com.sofka.stepdefinition.step"}
)
public class PatchDeleteCustomerTest {
}
