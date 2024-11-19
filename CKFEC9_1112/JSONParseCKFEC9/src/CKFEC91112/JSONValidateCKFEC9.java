package CKFEC91112;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;


public class JSONValidateCKFEC9 {
    public static void main(String[] args) {
        try {
            String content = Files.readString(Paths.get("./CKFEC9_1112/JSONParseCKFEC9/orarendCKFEC9.json"));
            String rawSchemaString = Files.readString(Paths.get("./CKFEC9_1112/JSONParseCKFEC9/orarendCKFEC9Schema.json"));
            JSONObject jsonObject = new JSONObject(content);
            JSONObject rawSchema = new JSONObject(rawSchemaString);
            Schema schema = SchemaLoader.load(rawSchema);
            schema.validate(jsonObject);
            System.out.println("JSON is valid");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}