package org.example;

import lombok.RequiredArgsConstructor;
import org.example.util.HomeMain;
import java.nio.charset.Charset;
import java.lang.reflect.Field;

@RequiredArgsConstructor
public class Application {
    public static void main(String[] args) {
        encodig();
        HomeMain homeMain = new HomeMain();
        homeMain.displayHome();
    }
    public static void encodig(){
        System.setProperty("file.encoding","UTF-8");
        try{
            Field charset = Charset.class.getDeclaredField("defaultCharset");
            charset.setAccessible(true);
            charset.set(null,null);
        }
        catch(Exception e){
        }
    }

}