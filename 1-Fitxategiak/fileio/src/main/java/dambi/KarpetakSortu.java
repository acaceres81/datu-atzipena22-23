package dambi;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class KarpetakSortu 
{
    /**
     * @param args
     * @throws IOException
     */
    public static void main( String[] args ) throws IOException
    {
        Path karpetaBerria = Paths.get(("kareptaBerria"));
        try{
            // Karpeta ez bada existizten, sortu egiten du
            Files.createDirectories(karpetaBerria);
        } catch (Exception e){
            System.out.println("Zerbait gaizki joan da");
        
        }
        Path animaliak = Paths.get(("kareptaBerria/animaliak"));
        try{
            // Karpeta ez bada existizten, sortu egiten du
            Files.createDirectories(animaliak);
        } catch (Exception e){
            System.out.println("Zerbait gaizki joan da");
        
        }
        Path elikagaiak = Paths.get(("kareptaBerria/elikagaiak"));
        try{
            // Karpeta ez bada existizten, sortu egiten du
            Files.createDirectories(elikagaiak);
        } catch (Exception e){
            System.out.println("Zerbait gaizki joan da");
        
        }
        Path barazkiak = Paths.get(("kareptaBerria/elikagaiak/barazkiak"));
        try{
            // Karpeta ez bada existizten, sortu egiten du
            Files.createDirectories(barazkiak);
        } catch (Exception e){
            System.out.println("Zerbait gaizki joan da");
        
        }
        Path esnekiak = Paths.get(("kareptaBerria/elikagaiak/esnekiak"));
        try{
            // Karpeta ez bada existizten, sortu egiten du
            Files.createDirectories(esnekiak);
        } catch (Exception e){
            System.out.println("Zerbait gaizki joan da");
        
        }
        Path arrainak = Paths.get(("kareptaBerria/animaliak/arrainak"));
        try{
            // Karpeta ez bada existizten, sortu egiten du
            Files.createDirectories(arrainak);
        } catch (Exception e){
            System.out.println("Zerbait gaizki joan da");
        
        }
        Path ugaztunak = Paths.get(("kareptaBerria/animaliak/ugaztunak"));
        try{
            // Karpeta ez bada existizten, sortu egiten du
            Files.createDirectories(ugaztunak);
        } catch (Exception e){
            System.out.println("Zerbait gaizki joan da");
        
        }
        
    }
}
