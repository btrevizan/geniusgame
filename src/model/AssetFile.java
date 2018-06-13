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
                    System.err.format("InvocationTarget: %s%n", e);
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

    public void save(List data){
        try{
            PrintWriter file = new PrintWriter(this.path);

            for(Object instance: data){
                file.write(instance.toString());
            }

            file.close();

        } catch(FileNotFoundException e) {
            System.err.format("FileNotFound: %s%n", e);
        }
    }
}
