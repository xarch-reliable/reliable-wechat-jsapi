package org.xarch.reliable.service.impl;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xarch.reliable.service.MediaUpload;
import org.xarch.reliable.service.feign.FeignCentralManager;
import org.xarch.reliable.utils.BaseResultTools;

@Service
public class MediaUploadImpl implements MediaUpload{
	
	private static final Logger logger = LoggerFactory.getLogger(MediaUploadImpl.class);
	
	private static final String mediaUploadUrl = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
	
	@Autowired
	private FeignCentralManager feignCentralManager;
	
	/**
	 * 文件上传的方法
	 * @param filePath 绝对路径
	 * @param fileType 文件类型
	 * @return
	 */
	@Override
    public Map<String, Object> UploadMeida(String filePath, String fileType) throws Exception{
		logger.info("filePath="+filePath+"  fileType="+fileType);
		
        //返回结果
        String result=null;
        File file=new File(filePath);
        if(!file.exists()||!file.isFile()){
        	logger.info("文件不存在");
            throw new IOException("文件不存在");
        }
        String token= (String)feignCentralManager.getAccessToken().get("access_token");
        String urlString="https://api.weixin.qq.com/cgi-bin/media/upload?access_token="+token+"&type="+fileType;
        URL url=new URL(urlString);
        HttpsURLConnection conn=(HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("POST");//以POST方式提交表单
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setUseCaches(false);//POST方式不能使用缓存
        //设置请求头信息
        conn.setRequestProperty("Connection", "Keep-Alive");
        conn.setRequestProperty("Charset", "UTF-8");
        //设置边界
        String BOUNDARY="----------"+System.currentTimeMillis();
        conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
        //请求正文信息
        //第一部分
        StringBuilder sb=new StringBuilder();
        sb.append("--");//必须多两条道
        sb.append(BOUNDARY);
        sb.append("\r\n");
        sb.append("Content-Disposition: form-data;name=\"media\"; filename=\"" + file.getName()+"\"\r\n");
        sb.append("Content-Type:application/octet-stream\r\n\r\n");

        //获得输出流
        OutputStream out=new DataOutputStream(conn.getOutputStream());
        //输出表头
        out.write(sb.toString().getBytes("UTF-8"));
        //文件正文部分
        //把文件以流的方式 推送道URL中
        DataInputStream din=new DataInputStream(new FileInputStream(file));
        int bytes=0;
        byte[] buffer=new byte[1024];
        while((bytes=din.read(buffer))!=-1){
            out.write(buffer,0,bytes);
        }
        din.close();
        //结尾部分
        byte[] foot=("\r\n--" + BOUNDARY + "--\r\n").getBytes("UTF-8");//定义数据最后分割线
        out.write(foot);
        out.flush();
        out.close();
        if(HttpsURLConnection.HTTP_OK==conn.getResponseCode()){

            StringBuffer strbuffer=null;
            BufferedReader reader=null;
            try {
                strbuffer=new StringBuffer();
                reader=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String lineString=null;
                while((lineString=reader.readLine())!=null){
                    strbuffer.append(lineString);

                }
                if(result==null){
                    result=strbuffer.toString();
                }
            } catch (IOException e) {
                logger.info("发送POST请求出现异常！"+e);
                e.printStackTrace();
            }finally{
                if(reader!=null){
                    reader.close();
                }
            }
        }
        return BaseResultTools.ObjectToMap(result);
    }
}
