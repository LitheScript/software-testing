package com.example.demo.service;

import com.example.demo.entity.Picture;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 图片表 服务类
 * </p>
 *
 * @author ZLZHAO
 * @since 2021-12-20
 */
public interface PictureService extends IService<Picture> {
    /**
     * 上传文件接口
     *
     * @param file           上传的文件
     * @param uploadFilePath 上传到服务器的路径
     * @return 上传并处理后的信息
     * @throws Exception 上传异常
     */
    String upload(MultipartFile file, String uploadFilePath, HttpServletRequest request) throws Exception;
}
