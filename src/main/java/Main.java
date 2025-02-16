import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        if(args.length == 0){
            System.out.println("ERROR: There arent any arguments!");
        }else{
            try {

                URL url = new URL("https://api.github.com/users/"+ args[0] +"/events") ;
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                int status = connection.getResponseCode();
                System.out.println(status);

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (ProtocolException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}