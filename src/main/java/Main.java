import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {

    /**
     * Method to fetch the activities from the user.
     * @param username user who we want to know the activities log
     * @return A string with all the activities.
     */
    public static String fetchApi(String username){
        try {
            URL url = new URL("https://api.github.com/users/"+ username +"/events") ;
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int status = connection.getResponseCode();
            System.out.println("Connection status response: "+status);
            if(status == 200){
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream())
                );
                String inputLine;
                StringBuilder content = new StringBuilder();
                while((inputLine = bufferedReader.readLine()) != null){
                    content.append(inputLine);
                }
                bufferedReader.close();
                connection.disconnect();
                return content.toString();
            } else if (status == 404) {
                System.out.println("User <"+username+"> not found.");
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "";
    }

    public static void displayActivities (String events){
        Pattern pattern = Pattern.compile("\"type\":\\s*\"(.*?)\".*?\"repo\":\\s*\\{.*?\"name\":\\s*\"(.*?)\"", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(events);
        List <Activity> activities = new ArrayList<>();

        while (matcher.find()) {
            String type = matcher.group(1);
            String repoName = matcher.group(2);
            Activity newActivity = new Activity(type,repoName);

            boolean found = false;

            for (Activity activity : activities) {
                if (activity.getRepo().equals(repoName) && activity.getType().equals(type)) {
                    activity.incrementCount();
                    found = true;
                    break;
                }
            }
            if (!found) {
                activities.add(newActivity);
            }
        }
        // Mostrar resultados
        for (Activity activity : activities) {
            System.out.println(activity.createMessage());
        }
    }

    public static void main(String[] args) {
        if(args.length == 0){
            System.out.println("ERROR: There arent any arguments!");
        }else{
            String response = fetchApi(args[0]);
            displayActivities(response);

        }
    }
}