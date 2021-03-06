package model;

import java.io.*;
import java.nio.file.*;
import java.nio.charset.*;

import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;


public class AssetFile{

    private String path;

    public AssetFile(String path){
        this.path = path;
    }

    public String getPath(){
        return this.path;
    }

    /**
     * Changes game difficullty to diff.
     * @param cls class
     * @return list of colors related to their sounds.
     */
    public List load(Class cls){
        ArrayList<Object> result = new ArrayList<Object>();
        Path filepath = Paths.get(this.path);

        try{
            BufferedReader file = Files.newBufferedReader(filepath, Charset.defaultCharset());

            String line = file.readLine();

            while(line != null){
                String[] info = line.split(Default.SEPARATOR);

                try{
                    Method createInstance = cls.getMethod("createInstance", info.getClass());
                    Object obj = createInstance.invoke(null, (Object)info);
                    result.add(obj);
                } catch(NoSuchMethodException e){
                    System.err.format("NoSuchMethod: %s%n", e);
                } catch(IllegalAccessException e){
                    System.err.format("IllegalAccess: %s%n", e);
                } catch(InvocationTargetException e){
                    System.err.format("InvocationTarget for createInstance in %s%n", cls);
                }

                line = file.readLine();
            }

            file.close();

            return result;
        } catch(IOException e) {
            System.err.format("I/O error: %s%n", e);
            return null;
        }
    }

    /**
     * Saves data on a file to be used by the game.
     * @param data
     * @return True, if saved the data correctly. False, otherwise.
     */
    public boolean save(List data){
        try{
            PrintWriter file = new PrintWriter(this.path);

            for(Object instance: data){
                file.write(instance.toString());
            }

            file.close();
            return true;
        } catch(FileNotFoundException e) {
            System.err.format("FileNotFound: %s%n", e);
            return false;
        }
    }
}
