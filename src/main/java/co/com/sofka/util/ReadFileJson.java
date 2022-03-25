package co.com.sofka.util;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

import static co.com.sofka.util.CommentsCode.*;


public class ReadFileJson {
    private static String IdPost;
    private static String FullName;
    private static String EMail;

    private static String Employ;

    private static String FullJson;
    private static Object JsonObjc;
    private static JSONObject JsonObjc2;

    private static String SavePostid;
    private static String SaveFullname;
    private static String SaveEmail;
    private static String SaveJob;



    //Contructor
    public ReadFileJson(String IdPost, String FullName, String Email) {
        this.IdPost = IdPost;
        this.FullName = FullName;
        this.EMail = Email;
    }

    public ReadFileJson(String employ, String fullName) {
        this.Employ = employ;
        this.FullName = fullName;
    }



    //lectura del archvio Json

    public static String ConvertPost() throws IOException, ParseException {
        JsonObjc = new JSONParser().parse(new FileReader("src/test/resources/filesJson/PostCustomer.json"));
        JsonObjc2 = (JSONObject) JsonObjc;
        FullJson = JSONObject.toJSONString(JsonObjc2);
        SavePostid = FullJson.replace(PostId.getValue(), IdPost);
        SaveFullname =SavePostid.replace(CompleteName.getValue(), FullName);
        SaveEmail = SaveFullname.replace(Email.getValue(), EMail);
        return SaveEmail;
    }
    public static String ConvertPatch() throws IOException, ParseException {
        JsonObjc = new JSONParser().parse(new FileReader("src/test/resources/filesJson/PatchCustomer.json"));
        JsonObjc2 = (JSONObject) JsonObjc;
        FullJson = JSONObject.toJSONString(JsonObjc2);
        SaveFullname = FullJson.replace(CompleteName.getValue(), FullName);
        SaveJob =SaveFullname.replace(Job.getValue(), Employ);
        return SaveJob;
    }

}
