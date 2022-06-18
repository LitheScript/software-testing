package com.example.demo.service.impl;

import com.example.demo.entity.Picture;
import com.example.demo.mapper.PictureMapper;
import com.example.demo.service.PictureService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * <p>
 * 图片表 服务实现类
 * </p>
 *
 * @author ZLZHAO
 * @since 2021-12-20
 */
@Service
public class PictureServiceImpl extends ServiceImpl<PictureMapper, Picture> implements PictureService {


    @Override
    public String upload(MultipartFile file, String uploadFilePath, HttpServletRequest request) throws Exception {
        if (file.isEmpty()) {
            return null;
        }
        String uploadInfo = "";
        String fileName;
        // 获得当前时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/");
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        // 转换为字符串
        String formatDate = format.format(new Date());
        // 随机生成文件编号
        int random = new Random().nextInt(10000);
        /**
         *  2.文件保存目录  E:/images/2020/03/15/
         *  如果目录不存在，则创建
         */
        String directory = simpleDateFormat.format(new Date());
        File dir = new File(uploadFilePath + directory);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        //3.给文件重新设置一个名字
        fileName = (new StringBuffer().append(formatDate).append(
                random)) + file.getOriginalFilename();
        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
        long fileSize = file.getSize();
        File packageFile = new File(uploadFilePath);
        if (!packageFile.exists()) {
            packageFile.mkdir();
        }
        File targetFile = new File(uploadFilePath + directory + fileName);
        file.transferTo(targetFile);
        uploadInfo = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/upload/" + directory + fileName;

        //是否使用一个接口还是两个接口仍需讨论
        //尚未决定表的存储方法
//        PictureMapper pictureMapper;
//        Picture picture =new Picture();
//        picture.setPictureUrl(uploadInfo);


        return uploadInfo;
    }

}
