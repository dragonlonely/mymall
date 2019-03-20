package com.dragon.xiaomi.utils;


import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;

import java.io.IOException;

/**
 * @author Administrator
 * @Date 2018/11/4 16:42
 * @Classname FastDfsUtils
 */
public class FastDfsUtils {
    private static TrackerClient trackerClient;
    private static TrackerServer trackerServer;
    private static StorageServer storageServer;

    public FastDfsUtils(String configlocation) throws IOException, MyException {
        if (configlocation.startsWith("classpath")) {
            configlocation=configlocation.replace("classpath:",getClass().getResource("/").getPath());
        }
        ClientGlobal.init(configlocation);
        trackerClient=new TrackerClient();
        trackerServer=trackerClient.getConnection();
    }
    public String fileUpload(byte[] bs,String ext_name) throws IOException, MyException {
        return fileUpload(bs,ext_name,null);
    }

    public String fileUpload(byte[] bs, String ext_name, NameValuePair[] valuePair) throws IOException, MyException {
        StorageClient1 storageClient1=new StorageClient1(trackerServer,storageServer);
        String[] resultString = storageClient1.upload_file(bs, ext_name, valuePair);
        StringBuffer stringBuffer=new StringBuffer();
        for (int i = 0; i < resultString.length; i++) {
            stringBuffer.append(resultString[i]);
            if (i==0) {
                stringBuffer.append("/");
            }
        }
        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        String config="classpath:conf.properties";
        config=config.replace("classpath:",FastDfsUtils.class.getResource("/").getPath());
        System.out.println(config);
    }

}
