/**
 * Saldo
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class Saldo {

    User user = new User();

    private String random;
    private String fileName = "database/voucher.bat";
    private File data = new File(fileName);

    public String getData(int userData) {

        String result;
        result = "";

        try {
            FileReader readUser = new FileReader(data);
            BufferedReader bufferRead = new BufferedReader(readUser);
            String listUser;

            while ((listUser = bufferRead.readLine()) != null) {
                String[] split = listUser.split("\\s");
                if (this.random.equals(split[0])) {
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

    public void setVoucher(String random) {
        this.random = random;
    }

    public boolean getVoucher(String username, String password) {
        user.setAuthentication(username, password);

        if (random.equals(getData(0))) {
            int saldo, nominalVoucher, totalSaldo;

            saldo = Integer.parseInt(user.getData(2));            
            nominalVoucher = Integer.parseInt(getData(1));            

            totalSaldo = saldo + nominalVoucher;

            String totalSaldoFinal = Integer.toString(totalSaldo);

            // penambahan saldo ke user
            user.modifyFile(user.getData(2), totalSaldoFinal);

            // voucher jadi hangus
            modifyFile(random, username);

            return true;      
        }
        else {
            return false;
        }

    }
    
}