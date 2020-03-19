import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class Weather {

    public static String getWeather(String message, Api api) throws IOException {
        URL url = new URL(
                "http://api.openweathermap.org/data/2.5/weather?q=" + message + "&units=metric&appid=6fff53a641b9b9a799cfd6b079f5cd4e");

        Scanner in = new Scanner((InputStream) url.getContent());
        String result = "";
        while (in.hasNext()) {
            result += in.nextLine();
        }

        JSONObject object = new JSONObject(result);
        api.setName(object.getString("name"));

        JSONObject main = object.getJSONObject("main");
        api.setTemp(main.getDouble("temp"));
        api.setHumidity(main.getDouble("humidity"));

        JSONArray getArray = object.getJSONArray("weather");
        for (int i = 0; i < getArray.length(); i++) {
            JSONObject obj = getArray.getJSONObject(i);
            api.setIcon((String) obj.get("icon"));
            api.setMain((String) obj.get("main"));
        }

        return "City: " + api.getName() + "\n" +
                "Temperature: " + api.getTemp() + "C" + "\n" +
                "Humidity:" + api.getHumidity() + "%" + "\n" +
                "Main: " + api.getMain() + "\n" +
                "http://openweathermap.org/img/w/" + api.getIcon() + ".png";
    }
}