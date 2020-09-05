package cn.novalue.blog.controller;

import cn.novalue.blog.model.entity.Album;
import cn.novalue.blog.model.entity.Picture;
import cn.novalue.blog.model.support.Response;
import cn.novalue.blog.service.AlbumService;
import cn.novalue.blog.service.FileService;
import cn.novalue.blog.service.PictureService;
import cn.novalue.blog.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 照片管理(Picture)表控制层
 *
 * @author makejava
 * @since 2020-09-02 08:49:19
 */
@RestController
@RequestMapping("picture")
public class PictureController {
    /**
     * 服务对象
     */
    @Resource
    private PictureService pictureService;
    @Autowired
    private FileService fileService;
    @Autowired
    private AlbumService albumService;

    /**
     * 上传照片
     * @param request
     * @param file
     * @param albumId
     * @return
     */
    @PostMapping("upload")
    public Response upload(HttpServletRequest request, @RequestParam("file") MultipartFile file,@RequestParam("albumId") Long albumId){
        if(albumService.queryById(albumId).getUserId()!=SecurityUtils.getUser().getId()){
            return Response.failure(400,"这不是您的相册");
        }
        String path = fileService.upload(file,request);
        Picture picture = new Picture();
        picture.setAlbumId(albumId);
        picture.setUrl(path);
        pictureService.insert(picture);
        Album album = albumService.getById(albumId);
        album.setTotal(album.getTotal()+1);
        albumService.saveOrUpdate(album);
//        try{
//            String uploadDir = request.getSession().getServletContext().getRealPath("/")+"upload/";
//            File dir = new File(uploadDir);
//            if(!dir.exists()) dir.mkdir();
//            executeUpload(uploadDir,file,albumId);
//        }catch (Exception e){
//            e.printStackTrace();
//            return Response.failure(500,"上传失败");
//        }
        return Response.success("上传成功",path);
    }
//    private void executeUpload(String uploadDir,MultipartFile file,long albumId) throws IOException{
//        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
//        String filename = UUID.randomUUID()+suffix;
//        String url = uploadDir+filename;
//        File serverFile = new File(url);
//        file.transferTo(serverFile);
//        Picture picture = new Picture();
//        picture.setAlbumId(albumId);
//        picture.setUrl(url);
//        pictureService.insert(picture);
//    }

    /**
     * 删除照片
     * @param picture
     * @return
     */
    @PostMapping("delete")
    public Response delete(@RequestBody Picture picture){
        Album album = albumService.queryById(picture.getAlbumId());
        if(!album.getUserId().equals(SecurityUtils.getUser().getId())){
            return Response.failure(400,"这不是您的相册");
        }
        pictureService.deleteById(picture.getId());
        album.setTotal(album.getTotal()-1);
        albumService.update(album);
        return Response.success("删除照片成功");
    }

    /**
     * 查看照片
     * @param id
     * @return
     */
    @GetMapping("view")
    public Picture selectOne(@RequestParam Long id) {
        Picture picture = pictureService.queryById(id);
        picture.setViewNum(picture.getViewNum()+1);
        pictureService.update(picture);
        return picture;
    }

   /* //点赞之后还能再点？ 如何保存
    @PostMapping("like")
    public Response like(@RequestBody Picture picture){
        picture.setLikeNum(picture.getLikeNum()+1);
        return Response.success("点赞成功");
    }*/

}