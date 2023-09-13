package dambi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CopyBytesFNEKontrolatuz {
    public static void main(String[] args) throws IOException {

        FileInputStream in = null;
        FileOutputStream out = null;

        String errepikatu = "bai";
        String file;

        Scanner sc = new Scanner(System.in);
        
        while (errepikatu == "bai") {
       
        System.out.print("Sartu kopiatu nahi duzun fitxategia: ");
        file = sc.nextLine();
        

        
            errepikatu = "ez";
            try {

                in = new FileInputStream(file);
                out = new FileOutputStream("outagain.txt");
                int c;

                while ((c = in.read()) != -1) {
                    out.write(c);
                }

            } catch (FileNotFoundException e) {
                System.out.println("fitxategi hori ez da existitzen.");
                errepikatu = "bai";
            } finally {

                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            }
        }
        System.out.println("Testua ondo kopiatu da!");
        sc.close();
    }
   
}
