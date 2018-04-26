
/**
 * User
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class User {

    private String username, password;
    private String fileName = "database/user.bat";
    private File data = new File(fileName);

    public void setAuthentication(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean getAuthentication() {
        boolean result;
        result = false;

        try {
            FileReader readUser = new FileReader(data);
            BufferedReader bufferRead = new BufferedReader(readUser);

            String listUser;

            while ((listUser = bufferRead.readLine()) != null) {
                String[] split = listUser.split("\\s");
                if (this.username.equals(split[0]) && this.password.equals(split[1])) {
                    result = true;
                    break;
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return result;
    }

    public void setRegister(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean getRegister() {
        if (getData(0).equals(this.username) || getData(1).equals(this.password)) {
            return false;
        }
        else {
            try {
                PrintWriter fileStore = new PrintWriter(new FileWriter(data, true));
                fileStore.write(this.username);
                fileStore.write(" ");
                fileStore.write(this.password);
                fileStore.write(" ");
                fileStore.write("0");
                fileStore.println();
                fileStore.close();
            } catch (Exception evt) {
                System.out.println(evt);
            }

            return true;
        }

    }

    public String getData(int userData) {

        String result;
        result = "";

        try {
            FileReader readUser = new FileReader(data);
            BufferedReader bufferRead = new BufferedReader(readUser);
            String listUser;

            while ((listUser = bufferRead.readLine()) != null) {
                String[] split = listUser.split("\\s");
                if (this.username.equals(split[0]) && this.password.equals(split[1])) {
                    result = split[userData];
                    break;
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return result;

    }

    public void modifyFile(String oldString, String newString) {
        String oldContent = "";
        BufferedReader reader = null;
        FileWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(data));

            //Reading all the lines of input text file into oldContent
            String line = reader.readLine();

            while (line != null) {
                oldContent = oldContent + line + System.lineSeparator();

                line = reader.readLine();
            }

            //Replacing oldString with newString in the oldContent

            String newContent = oldContent.replaceAll(oldString, newString);

            //Rewriting the input text file with newContent

            writer = new FileWriter(data);

            writer.write(newContent);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //Closing the resources

                reader.close();

                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}