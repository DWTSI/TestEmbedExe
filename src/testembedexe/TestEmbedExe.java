/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testembedexe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Daniel
 */
public class TestEmbedExe {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TestEmbedExe testEmbedExe = new TestEmbedExe();
        try {
            copyFileOutOfJar();
            runFile("simlib_test.exe");
        } catch (IOException ex) {
            Logger.getLogger(TestEmbedExe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public TestEmbedExe() {
        URL url = getClass().getResource("simlib_test.exe");
        File dest = new File("../temp/");
        //FileUtils.copyURLtoFile(url, dest);
    }
    
    private static void runFile(String filename) throws IOException {
        Process p = Runtime.getRuntime().exec(filename);
    }
    
    private static void copyFileOutOfJar() throws FileNotFoundException, IOException {
        //URL inputUrl = getClass().getResource("/testembedexe/simlib_test.exe");
        //File dest = new File("simlib_test.exe");
        //FileUtils 

        InputStream stream = TestEmbedExe.class.getResourceAsStream("/testembedexe/simlib_test.exe");
        
        if (stream == null) {
            // exception for empty stream
        }
        
        OutputStream resStreamOut;
        int readBytes;
        byte[] buffer = new byte[4096];
        
        resStreamOut = new FileOutputStream(new File("simlib_test.exe"));
        while ((readBytes = stream.read(buffer)) > 0) {
            resStreamOut.write(buffer, 0, readBytes);
        }
        
        stream.close();
        resStreamOut.close();
        
        
        
        
        //InputStream is = getClass().getResourceAsStream(filename);
        //Path inPath = Paths.get(filename);
        //Path outPath = Paths.get("temp/" + filename);
        //byte[] data = Files.readAllBytes(inPath);
        
        //OutputStream os = new FileOutputStream(new File("temp/" + filename));
        
        //Files.copy(inPath, outPath);
        
        //os.write(data);
    }
    
}
