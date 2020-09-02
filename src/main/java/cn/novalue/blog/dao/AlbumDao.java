package cn.novalue.blog.dao;

import cn.novalue.blog.model.entity.Album;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 相册表(Album)表数据库访问层
 *
 * @author makejava
 * @since 2020-09-01 22:51:55
 */
public interface AlbumDao extends BaseMapper<Album> {

    Album queryById(Long id);

    List<Album> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    List<Album> queryAll(Album album);

    int insert(Album album);

    int update(Album album);

    int deleteById(Long id);

    List<Album> getAll(Long userId);

    List<Album> getMine(Long userId);
}