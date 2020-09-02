package cn.novalue.blog.service;

import cn.novalue.blog.model.entity.Picture;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 照片管理(Picture)表服务接口
 *
 * @author makejava
 * @since 2020-09-02 08:49:18
 */
public interface PictureService extends IService<Picture> {

    Picture queryById(Long id);

    List<Picture> queryAllByLimit(int offset, int limit);

    Picture insert(Picture picture);

    Picture update(Picture picture);

    boolean deleteById(Long id);

    List<Picture> find(Long albumId);

    IPage<Picture> getPictureByPage(Page<?> objectPage, Long albumId);
}