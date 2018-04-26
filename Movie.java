/**
 * Movie
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;

public class Movie {

    Scanner input = new Scanner(System.in);
    User jeremy = new User();

    // File Management
    private String fileName = "database/list_ticket.bat";
    private File database = new File(fileName);
    // private FileReader fileShow = null;

    public String[][] listMovie = {
        {"Danur", "2018", "150 Menit", "35000"},
        {"Avatar", "2018", "150 Menit", "35000"},
        {"Java", "2018", "150 Menit", "40000"},
        {"Titanic", "2018", "150 Menit", "40000"},
        {"Gladiator", "2018", "150 Menit", "40000"},
        {"Jaws", "2018", "150 Menit", "40000"},
        {"Inception", "2018", "150 Menit", "35000"},
    };

    public void FileManagement() {
        database.getParentFile().mkdirs();

        // jika file tidak tersedia maka membuat database baru
        if(!database.exists())
        {
            try {
                database.createNewFile();
            } catch (Exception e) {
                System.err.println("Failed to create the file");
            }
        }
    }

    public void showMovie() {
        System.out.println();
        System.out.println("==============================");
        System.out.println("List Film Saat Ini: ");
        for (int i = 0; i < listMovie.length; i++) {
            System.out.println(i + ". " + listMovie[i][0]);   
        }
        System.out.println();
    }

    public void addMovie(String judulFilm, String username, String kursi) {
        // write data
        try {
            PrintWriter fileStore = new PrintWriter(new FileWriter(database, true));
            fileStore.write(judulFilm);
            fileStore.write(" ");
            fileStore.write(username);
            fileStore.write(" ");
            fileStore.write(kursi);
            fileStore.println();
            fileStore.close();
        } catch (Exception evt) {
            System.out.println(evt);
        }

    }

    public boolean fetchDataKursi(int userData, String kursi, String movie) {

        boolean result;
        result = false;

        try {
            FileReader readUser = new FileReader(database);
            BufferedReader bufferRead = new BufferedReader(readUser);
            String listUser;

            while ((listUser = bufferRead.readLine()) != null) {
                String[] split = listUser.split("\\s");
                if (movie.equals(split[0]) && kursi.equals(split[2])) {
                    result = true;
                }
                else {
                    result = false;
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return result;

    }

    public String getData(int userData, String username, String password) {

        String result;
        result = "";

        try {
            FileReader readUser = new FileReader(database);
            BufferedReader bufferRead = new BufferedReader(readUser);
            String listUser;

            while ((listUser = bufferRead.readLine()) != null) {
                String[] split = listUser.split("\\s");
                result = split[userData];

                if (username.equals(split[1])) {
                    System.out.println(result);
                    // break;
                }        

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return result;

    }

    public void bookHistory(String username, String password) {
        System.out.println("==============================");
        System.out.println("Pesanan Tiket: ");
        getData(0, username, password);
    }

    public void pickMovie(String username, String password) {
        FileManagement();
        
        int pick, harga, saldo;

        System.out.print("Pilih Film: ");
        pick = input.nextInt();
        harga = Integer.parseInt(listMovie[pick][3]);

        System.out.println();
        System.out.println("Film " + listMovie[pick][0]);
        System.out.println("Tahun: " + listMovie[pick][1]);
        System.out.println("Menit: " + listMovie[pick][2]);
        System.out.println("Harga: " + listMovie[pick][3]);
        
        jeremy.setAuthentication(username, password);
        saldo = Integer.parseInt(jeremy.getData(2));

        System.out.print("Input kursi: ");
        String inputKursi = input.next();

        if (fetchDataKursi(2, inputKursi, listMovie[pick][0])) {
            System.out.println("Kursi sudah dipakai, cari yang lain");
        }
        else {
            System.out.println("Kursi tersedia");
            if (saldo >= harga) {
                int value = saldo - harga; 
                String saldoFinal = Integer.toString(saldo);
                String valueFinal = Integer.toString(value);
    
                jeremy.modifyFile(saldoFinal, valueFinal);            
                
                System.out.println("Pembelian berhasil!");
                addMovie(listMovie[pick][0], username, inputKursi);            
            }
            else {
                System.out.println("Saldo tidak mencukupi!");
            } 
        }


    };
    
}