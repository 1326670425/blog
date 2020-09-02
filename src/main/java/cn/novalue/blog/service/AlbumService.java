package cn.novalue.blog.service;

import cn.novalue.blog.model.entity.Album;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 相册表(Album)表服务接口
 *
 * @author makejava
 * @since 2020-09-01 22:51:55
 */
public interface AlbumService extends IService<Album> {
    Album queryById(Long id);

    List<Album> queryAllByLimit(int offset, int limit);

    Album insert(Album album);

    Album update(Album album);

    boolean deleteById(Long id);

    List<Album> getAll(Long userId);

    List<Album> getMine(Long userId);
}