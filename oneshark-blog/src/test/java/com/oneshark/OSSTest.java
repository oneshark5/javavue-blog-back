// package com.oneshark;
//
// import com.google.gson.Gson;
// import com.qiniu.common.QiniuException;
// import com.qiniu.http.Response;
// import com.qiniu.storage.Configuration;
// import com.qiniu.storage.Region;
// import com.qiniu.storage.UploadManager;
// import com.qiniu.storage.model.DefaultPutRet;
// import com.qiniu.util.Auth;
// import org.junit.jupiter.api.Test;
// import org.springframework.boot.context.properties.ConfigurationProperties;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.stereotype.Component;
//
// import java.io.ByteArrayInputStream;
// import java.io.FileInputStream;
// import java.io.InputStream;
//
// @SpringBootTest // 这个地方如果测试类和启动类包名不一致，需要手动设置 @SpringBootTest(classes = OneSharkBlogApplication.class) ---springboot扫包的基础知识
// @Component
// @ConfigurationProperties(prefix = "oss")
// // @EnableConfigurationProperties(OSSTest.class)
// public class OSSTest {
//
//     // 添加配置信息
//     private String accessKey;
//     private String secretKey;
//     private String bucket;
//
//     public void setAccessKey(String accessKey) {
//         this.accessKey = accessKey;
//     }
//
//     public void setSecretKey(String secretKey) {
//         this.secretKey = secretKey;
//     }
//
//     public void setBucket(String bucket) {
//         this.bucket = bucket;
//     }
//
//     @Test
//     public void testOSS() {
//         //构造一个带指定 Region 对象的配置类
//         Configuration cfg = new Configuration(Region.autoRegion());
//         cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
//         //...其他参数参考类注释
//
//         UploadManager uploadManager = new UploadManager(cfg);
//         //...生成上传凭证，然后准备上传 --- 前两个是密钥 第三个是存储空间名字
//         // String accessKey = "dMPIEKRSxcgq45w_gJUjb-Na6oc18pX06lnj1WMo";//ak
//         // String secretKey = "WfQtPAaCohQO7a6WmGPBBy9sE7DKJL-DxlhyMs-j";//sk
//         // String bucket = "shark-blog";// 存储空间名字
//
//         //默认不指定key的情况下，以文件内容的hash值作为文件名
//         String key = "2023/11/25/bb.jpg";//指定文件在OSS存储式时的文件名---这里其实是一个路径
//         try {
//             byte[] uploadBytes = "hello qiniu cloud".getBytes("utf-8");
//             ByteArrayInputStream byteInputStream = new ByteArrayInputStream(uploadBytes);
//
//             // 测试文件上传
//             InputStream inputStream = new FileInputStream("C:\\Users\\Iron\\Pictures\\Saved Pictures\\bob.jpg");
//
//             Auth auth = Auth.create(accessKey, secretKey);// 上传一个凭证
//             String upToken = auth.uploadToken(bucket);// 上传凭证的方法
//
//             try {
//                 Response response = uploadManager.put(inputStream, key, upToken, null, null);
//                 //解析上传成功的结果 --- 上传成功后返回的结果
//                 DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
//                 System.out.println(putRet.key);
//                 System.out.println(putRet.hash);
//             } catch (QiniuException ex) {
//                 ex.printStackTrace();
//                 if (ex.response != null) {
//                     System.err.println(ex.response);
//
//                     try {
//                         String body = ex.response.toString();
//                         System.err.println(body);
//                     } catch (Exception ignored) {
//                     }
//                 }
//             }
//         } catch (Exception ex) {
//             //ignore
//         }
//
//     }
// }
