package com.mu.week01;

import java.io.*;
import java.lang.reflect.Method;
import java.net.MalformedURLException;

public class CustomeClassLoader extends ClassLoader implements Serializable {

    public static void main(String[] args) throws Exception {
        String path = "/Users/***/IdeaProjects/jeek-work/01jvm/src/main/resources/Hello.xlass";
        Class targetClass = new CustomeClassLoader().loadClass("Hello", path).newInstance().getClass();
        Method method = targetClass.getMethod("hello", new Class[]{});
        method.invoke(new CustomeClassLoader(), null);
    }

    protected Class<?> loadClass(String showName, String path) throws ClassNotFoundException {
        try {
//            URL url = new URL(path);
            File file = new File(path);
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            ByteArrayOutputStream bufferedOutputStream = new ByteArrayOutputStream((int)file.length());
            int buff_size = 4096;
            byte[] temp = new byte[buff_size];
            int len = 0;
            while (-1 != (len = bufferedInputStream.read(temp, 0, buff_size)) ) {
                bufferedOutputStream.write(temp, 0, len);
            }
            byte[] bytes = bufferedOutputStream.toByteArray();
            byte[] data = new byte[bytes.length];
            for(int i=0; i<data.length; i++) {
                data[i] = handler(bytes[i]);
            }
            return defineClass(showName, data, 0, bytes.length);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 不会了...
     * @param data
     * @return
     */
    private byte handler(byte data) {
        int temp =  255 - byteToInt(data) ;
        byte result = intToByte(temp);
        return result;
    }

    private byte intToByte(int x) {
        return (byte) x;
    }

    private int byteToInt(byte b) {
        return b & 0xFF;
    }
}
