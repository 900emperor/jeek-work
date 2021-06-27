package com.mu.week01;

import java.net.MalformedURLException;
import java.net.URL;

public class CustomeClassLoader extends ClassLoader {

    public static void main(String[] args) throws Exception {
        String path = "file:///src/main/resources/Hello.xlass";
        new CustomeClassLoader().loadClass("Hello", path).newInstance();
    }

    protected Class<?> loadClass(String showName, String path) throws ClassNotFoundException {
        try {
            URL url = new URL(path);
            byte[] bytes = url.getFile().getBytes();
            byte[] data = new byte[bytes.length];
            for(int i=0; i<data.length; i++) {
                data[i] = handler(bytes[i]);
            }
            return defineClass(showName, data, 0, bytes.length);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
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
