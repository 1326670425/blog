package cn.novalue.blog.controller;

import cn.novalue.blog.model.entity.Album;
import cn.novalue.blog.model.entity.Picture;
import cn.novalue.blog.model.support.Response;
import cn.novalue.blog.service.AlbumService;
import cn.novalue.blog.service.PictureService;
import cn.novalue.blog.utils.SecurityUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 相册表(Album)表控制层
 *
 * @author makejava
 * @since 2020-09-01 22:51:55
 */
@RestController
@RequestMapping("album")
public class AlbumController {
    /**
     * 服务对象
     */
    @Resource
    private AlbumService albumService;
    @Autowired
    private PictureService pictureService;

    /**
     * 增加相册
     * @param album
     * @return
     */
    @PostMapping("add")
    public Response add(@RequestBody Album album){
        if(album.getUserId()==null){
            album.setUserId(SecurityUtils.getUser().getId());
        }else if(!album.getUserId().equals(SecurityUtils.getUser().getId()))
            return Response.failure(400,"这不是您的相册");
        List<Album> albums = albumService.getMine(SecurityUtils.getUser().getId());
        for(Album a:albums){
            if(a.getBname().equals(album.getBname())){
                return Response.failure(400,"你已经有该相册");
            }
        }
        if(album.getAuth()==null){
            album.setAuth(0);//0--谁都可以查看
        }
        if(album.getTotal()==null) album.setTotal(0);
        albumService.saveOrUpdate(album);
        return Response.success("保存成功");
    }

    /**
     * 删除相册
     * @param album
     * @return
     */
    @PostMapping("delete")
    public Response delete(@RequestBody Album album){
        if(!album.getUserId().equals(SecurityUtils.getUser().getId())){
            return  Response.failure(400, "不是您的相册");
        }
        albumService.deleteById(album.getId());
        return Response.success("删除成功");
    }

    /**
     * 查看相册的照片
     * @param albumId
     * @param page
     * @param size
     * @return
     */
    @GetMapping("getPicture")
    public Response getPicture(@RequestParam("albumId") Long albumId,@RequestParam(value = "page",required = false,defaultValue = "1")Integer page,
                               @RequestParam(value = "size",required = false,defaultValue = "10")Integer size){
        if(albumService.getById(albumId).getAuth()!=0&&!albumService.getById(albumId).getUserId().equals(SecurityUtils.getUser().getId())){
            Response.failure(400,"您无权限查看此相册");
        }
        IPage<Picture>  pictureIPage = pictureService.getPictureByPage(new Page<>(page,size),albumId);
        return Response.success(pictureIPage);
    }

    /**
     * 获取某用户的所有相册
     * @param userId
     * @return
     */
    @GetMapping("getAll")
    public List<Album> getAll(@RequestParam("userId") Long userId){
        List<Album> albums = albumService.getAll(userId);
        return albums;
    }

    /**
     * 查看自己的相册
     * @return
     */
    @GetMapping("getMine")
    public List<Album> getMine(){
        return albumService.getMine(SecurityUtils.getUser().getId());
    }
    @GetMapping("selectOne")
    public Album selectOne(Long id) {
        return this.albumService.queryById(id);
    }

}