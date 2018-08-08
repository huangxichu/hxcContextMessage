package com.hxc.cms.controller.common;

import com.hxc.cms.annotation.CheckLogin;
import com.hxc.cms.dto.Result;
import com.hxc.cms.enums.ResultEnum;
import com.hxc.cms.exception.ApplicationException;
import com.hxc.cms.service.user.UserService;
import com.hxc.cms.utils.ImageUtil;
import com.hxc.cms.utils.ObjectUtil;
import com.hxc.cms.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author 黄细初
 * @createDate 2018/08/08
 */
@RestController
public class CommonController {
    
    private final static Logger logger = LoggerFactory.getLogger(CommonController.class);
    
    @Autowired
    private UserService userService;
    
    /**
     * 在配置文件中配置的文件保存路径
     */
    @Value("${img.location}")
    private String imgLocation;
    @Value("${img.path}")
    private String imgPath;
    @Value("${img.url}")
    private String imgUrl;
    
    /**
     *  上传图片接口（绝对路径）
     * @date  2018/8/8
     * @author  黄细初
     */
    @CheckLogin
    @PutMapping("/img/upload")
    public Result uploadImg(@RequestParam("editormd-image-file") MultipartFile multipartFile)  {
        if (multipartFile.isEmpty() || !ObjectUtil.isNotBlank(multipartFile.getOriginalFilename())) {
            throw new ApplicationException(ResultEnum.IMG_NOT_EMPTY);
        }
        String contentType = multipartFile.getContentType();
        if (!contentType.contains("")) {
            throw new ApplicationException(ResultEnum.IMG_FORMAT_ERROR);
        }
        String root_fileName = multipartFile.getOriginalFilename();
        logger.info("上传图片:name={},type={}", root_fileName, contentType);

        //获取路径
//        String return_path = ImageUtil.getFilePath(currentUser);
        String filePath = imgLocation + imgPath;
        logger.info("图片保存路径={}", filePath);
        String file_name = null;
        try {
            file_name = ImageUtil.saveImg(multipartFile, filePath);
            if(ObjectUtil.isNotBlank(file_name)){
                return ResultUtil.success(imgUrl+imgPath+ "/"+file_name);
            }
            return ResultUtil.error(ResultEnum.SAVE_IMG_ERROE);
        } catch (IOException e) {
            throw new ApplicationException(ResultEnum.SAVE_IMG_ERROE);
        }
    }
    
}
