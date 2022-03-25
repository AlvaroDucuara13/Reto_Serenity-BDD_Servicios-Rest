package co.com.sofka.util;

import co.com.sofka.models.datosRandomModel.DatosModelRandom;
import com.github.javafaker.Faker;

public class Persona {

    private static DatosModelRandom random;
    private static Faker DatosRandom;
    private static String PostId;
    private static String FirstName;
    private static String LastName;
    private static String FullName;
    private static String Email;
    private static String Employ;



    public static DatosModelRandom generarPersonasRandom(){
        //Datos Random
        DatosRandom = new Faker();
        PostId = String.valueOf(DatosRandom.number().numberBetween(00,99));
        FirstName = DatosRandom.name().firstName();
        LastName = DatosRandom.name().lastName();
        FullName = FirstName +" "+ LastName;
        Email =  FirstName + "@outlook.com";
        Employ = DatosRandom.job().title();


        random = new DatosModelRandom();
        random.setPostId(PostId);
        random.setEmail(Email);
        random.setFullName(FullName);
        random.setJob(Employ);

        return random;
    }
}
