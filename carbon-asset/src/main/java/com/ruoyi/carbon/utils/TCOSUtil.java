package com.ruoyi.carbon.utils;

/**
 * @author zyh
 * @date 2023/4/15 21:34
 */


import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.util.Random;


/**
 * 腾讯云COS云存储工具类
 */

@Component
public class TCOSUtil {

    /**
     * 存储桶名称
      */
    private static final String BUCKET_NAME = "blog-1304715799";
    /**
     * secretId 秘钥id
     */
    private static final String SECRET_ID = "AKIDk6z64pcAk9JCH8p4GJ9k5yeBZCKFCiGY";
    /**
     * SecretKey 秘钥
     */
    private static final String SECRET_KEY = "CXdZIYfYKuEIZJf9U26vVorgHh8PSGn9";
    /**
     * 腾讯云 自定义文件夹名称
     */
    private static final String PREFIX = "/imgs/";
    /**
     * 访问域名
     */
    public static final String URL = "https://blog-1304715799.cos.ap-nanjing.myqcloud.com";
    /**
     * 创建COS 凭证
      */
    private static final COSCredentials credentials = new BasicCOSCredentials(SECRET_ID,SECRET_KEY);
    /**
     * 配置 COS 区域 就购买时选择的区域 我这里是 南京（nanjing）
     */
    private static final ClientConfig clientConfig = new ClientConfig(new Region("ap-nanjing"));


    /**
     * 上传文件
     * @param file 文件参数
     * @return 返回URL路径
     */
    public String uploadFile(MultipartFile file){
        // 创建 COS 客户端连接
        COSClient cosClient = new COSClient(credentials,clientConfig);
        String fileName = file.getOriginalFilename();
        try {
            assert fileName != null;
            String substring = fileName.substring(fileName.lastIndexOf("."));
            File localFile = File.createTempFile(String.valueOf(System.currentTimeMillis()),substring);
            file.transferTo(localFile);
            Random random = new Random();
            fileName =PREFIX+random.nextInt(10000)+System.currentTimeMillis()+substring;
            // 将 文件上传至 COS
            PutObjectRequest objectRequest = new PutObjectRequest(BUCKET_NAME,fileName,localFile);
            cosClient.putObject(objectRequest);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            cosClient.shutdown();
        }
        return URL+fileName;
    }
}
