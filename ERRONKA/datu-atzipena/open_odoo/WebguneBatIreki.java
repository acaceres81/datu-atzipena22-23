public class WebguneBatIreki {
    public static void main(String[] args) {
        try {
            // Define the URL you want to open
            String url = "http://192.168.65.120:8069";
            
            // Get the operating system name
            String os = System.getProperty("os.name").toLowerCase();

            // Create a ProcessBuilder for running platform-specific commands
            ProcessBuilder processBuilder = new ProcessBuilder();
            
            if (os.contains("win")) {
                // On Windows, use the "start" command to open the URL
                processBuilder.command("cmd.exe", "/c", "start", url);
            } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
                // On Unix-like and Mac OS, use "xdg-open" to open the URL
                processBuilder.command("xdg-open", url);
            } else {
                // Platform not supported
                System.out.println("Unable to open Odoo, make sure you are connected to the net or contact the IT department.");
                return;
            }
            
            // Start the process to open the URL in the default web browser
            processBuilder.start();
        } catch (Exception e) {
            // Handle any exceptions that may occur
            e.printStackTrace();
        }
    }
}
