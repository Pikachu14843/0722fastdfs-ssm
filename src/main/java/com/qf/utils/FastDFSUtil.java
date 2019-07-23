package com.qf.utils;

import org.csource.common.MyException;
import org.csource.fastdfs.*;

import java.io.IOException;

public class FastDFSUtil {
    //声明 tracker storage
    TrackerClient trackerClient=null;
    TrackerServer trackerServer=null;
    StorageClient storageClient=null;
    StorageServer storageServer=null;

    public FastDFSUtil() {
        try {
            //1.加载配置文件
            ClientGlobal.init("client.conf");
            System.out.println(ClientGlobal.configInfo());
            //2.创建TrackerClient 对象
            trackerClient=new TrackerClient();
            //3.得到TrackerServer 对象
            trackerServer=trackerClient.getConnection();
            //4.构建StorageClient 对象
            storageClient=new StorageClient(trackerServer,storageServer);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    public String[] upload(String local_name,String suffix) {
        try {
            String[] s = storageClient.upload_file(local_name, suffix, null);
            return s;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String[] upload(byte[] b,String suffix){
        try {
            String[] s = storageClient.upload_file(b, suffix, null);
            return s;//返回文件上传的路径
        } catch (IOException e) {

        } catch (MyException e) {
            e.printStackTrace();
        }
        return null;
    }

}
