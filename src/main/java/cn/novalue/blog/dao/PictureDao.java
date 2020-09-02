package cn.novalue.blog.dao;

import cn.novalue.blog.model.entity.Picture;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 照片管理(Picture)表数据库访问层
 *
 * @author makejava
 * @since 2020-09-02 08:49:18
 */
public interface PictureDao extends BaseMapper<Picture> {

    List<Picture> find(@Param("albumId") Long albumId);

    Picture queryById(Long id);

    List<Picture> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    List<Picture> queryAll(Picture picture);

    int insert(Picture picture);

    int update(Picture picture);

    int deleteById(Long id);

    IPage<Picture> getPictrueByPage(Page<?> page,@Param("albumId") Long albumId);
}