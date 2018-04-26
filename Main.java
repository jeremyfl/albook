/**
 * Main
 */
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        User jeremy = new User();
        Movie movie = new Movie();
        Saldo saldo = new Saldo();

        System.out.print("Sudah punya akun? ");
        String loginOrRegister = input.nextLine();

        switch (loginOrRegister) {
            case "n":
                // login
                System.out.print("Username: ");
                String registerUsername = input.nextLine();

                System.out.print("Password: ");
                String registerPassword = input.nextLine();

                jeremy.setRegister(registerUsername, registerPassword);
                
                if (jeremy.getRegister()) {
                    System.out.println("Pendaftaran sukses, silahkan login!");
                }
                else {
                    System.out.println("Username & Password sudah terdaftar!");
                    break;
                }

            case "y":
                // login
                System.out.print("Username: ");
                String username = input.nextLine();
        
                System.out.print("Password: ");
                String password = input.nextLine();
        
                jeremy.setAuthentication(username, password);
            
                if (jeremy.getAuthentication()) {
                    String menu;
                    do {
                        System.out.println();
                        System.out.println("M for Movie");
                        System.out.println("H for Ticket Histroy");
                        System.out.println("S for Isi saldo");
                        System.out.println("Q for Quit");
                        System.out.println();
        
                        // menu
                        // System.out.print("Saldo: ");
                        System.out.println(jeremy.getData(2));
                        
                        System.out.print("Insert a menu: ");
                        menu = input.nextLine();
        
                        switch (menu) {
                            case "m":
                                movie.showMovie();
                                movie.pickMovie(username, password);
                                break;
                            case "h":
                                movie.bookHistory(username, password);
                                break;
                            case "s":
                                String random;
                                System.out.print("Masukan voucher: ");
                                random = input.nextLine();

                                saldo.setVoucher(random);
                                if (saldo.getVoucher(username, password)) {
                                    System.out.println("Voucher benar!, anda mendapatkan penambahan!");
                                }
                                else if(random.equals(username)) {
                                    System.out.println("Paan si");
                                }
                                else {
                                    System.out.println("Voucher tidak ditemukan atau sudah terpakai");
                                }
                                break;
                            default:
                                break;
                        }
        
                    } while (!menu.equals("q"));
                }
                else {
                    System.out.println("Username or Password is not valid");
                }
                break;
            default:
                break;
        }


    }
}