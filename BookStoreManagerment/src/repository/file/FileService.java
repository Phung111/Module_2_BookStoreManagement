package repository.file;

import repository.IModel;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class FileService {
    public <T> List<T> readData(String filePath, Class<T> cls){
        List<T> list = new ArrayList<>();
        try {
            Reader reader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                //line = "1,Iphone 11,23000.0";
                IModel<T> iModel = (IModel<T>) cls.getDeclaredConstructor().newInstance();
                T temp = iModel.parseData(line);
                list.add(temp);
            }
            bufferedReader.close();
            reader.close();
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static <T> void writeDate(List<T> list, String filePath) {
        try {
            File file = new File(filePath);
            PrintWriter printWriter = new PrintWriter(file);
            for (int i = 0; i < list.size(); i++) {
                printWriter.write(list.get(i).toString());
                if (i != list.size() - 1) {
                    printWriter.write("\n");
                }
            }
            printWriter.close();

        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
