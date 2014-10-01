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
import java.nio.file.NoSuchFileException;
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
            copyFileOutOfJar("simlib_test.exe");
            copyFileOutOfJar("mtbank.in");
            System.out.println(runFile("simlib_test.exe"));
            //System.in.read();
            deleteFile("simlib_test.exe");
            deleteFile("mtbank.in");
            
        } catch (IOException ex) {
            Logger.getLogger(TestEmbedExe.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(TestEmbedExe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static int runFile(String filename) throws IOException, InterruptedException {
        return Runtime.getRuntime().exec(filename).waitFor();
    }
    
    private static void deleteFile(String filename) throws NoSuchFileException, IOException {
        Files.deleteIfExists(Paths.get(filename));
    }
    
    private static void copyFileOutOfJar(String filename) throws FileNotFoundException, IOException {  
        InputStream stream = TestEmbedExe.class.getResourceAsStream("/testembedexe/" + filename);
        
        if (stream == null) {
            // exception for empty stream
        }
        
        OutputStream resStreamOut;
        int readBytes;
        byte[] buffer = new byte[4096];
        
        resStreamOut = new FileOutputStream(new File(filename));
        while ((readBytes = stream.read(buffer)) > 0) {
            resStreamOut.write(buffer, 0, readBytes);
        }
        
        stream.close();
        resStreamOut.close();
    }
    
}
