import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.

    ArrayList<String> queryList = new ArrayList<>();

    public String handleRequest(URI url)
    {
        String output = "";

        // Check whether the user is attempting to add a string to the list.
        if (url.getPath().contains("/add"))
        {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s"))
            {
                // Add the new string to the list
                queryList.add(parameters[1]);
                output += "Adding " + parameters[1];
            }// End of if parameter = s
        }// End of if url getpath contains add
        else if (url.getPath().contains("search"))
        {
            // Add all of the strings in Querylist that have the query parameters
            // as a substring
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s"))
            {
                for (int i = 0; i < queryList.size(); i++)
                {
                    if (queryList.get(i).contains(parameters[1]))
                    {
                        output += queryList.get(i) + " ";
                    }   // end of if querylist contains parameter
                }   // End of for loop
            }
        }
        else
        {
            output += "404 Not Found";
        }

        return output;
    }
/*
    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("Alan's Number: %d\n", num);
        } else if (url.getPath().equals("/increment")) {
            num += 1;
            return String.format("Alan's Number incremented!\n");
        } else {
            if (url.getPath().contains("/add")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("count")) {
                    num += Integer.parseInt(parameters[1]);
                    return String.format("Alan's Number increased by %s! It's now %d\n", parameters[1], num);
                }
            }
            return "404 Not Found!";
        }
        */
    
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
