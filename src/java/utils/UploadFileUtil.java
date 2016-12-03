/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.SecureRandom;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Gustavo
 */
public class UploadFileUtil {
    
    
    

    public static Path upload(Part file, String userId)
    {
        Path path;
        SecureRandom random = new SecureRandom();
        
       try(InputStream input = file.getInputStream())
       {
           path = new File(userId, file.getName().substring(0, 32) + new BigInteger(130, random).toString(32)).toPath();
           Files.copy(input, path);
       }
       catch(IOException e)
       {
           path = null;
       }
       return path;
    }
}
