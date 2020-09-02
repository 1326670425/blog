package cn.novalue.blog.service.impl;

import cn.novalue.blog.dao.AlbumDao;
import cn.novalue.blog.model.entity.Album;
import cn.novalue.blog.service.AlbumService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 相册表(Album)表服务实现类
 *
 * @author makejava
 * @since 2020-09-01 22:51:55
 */
@Service("albumService")
public class AlbumServiceImpl extends ServiceImpl<AlbumDao,Album> implements AlbumService {
    @Resource
    private AlbumDao albumDao;

    @Override
    public Album queryById(Long id) {
        return this.albumDao.queryById(id);
    }

    @Override
    public List<Album> queryAllByLimit(int offset, int limit) {
        return this.albumDao.queryAllByLimit(offset, limit);
    }

    @Override
    public Album insert(Album album) {
        this.albumDao.insert(album);
        return album;
    }

    @Override
    public Album update(Album album) {
        this.albumDao.update(album);
        return this.queryById(album.getId());
    }

    @Override
    public boolean deleteById(Long id) {
        return this.albumDao.deleteById(id) > 0;
    }

    @Override
    public List<Album> getAll(Long userId) {
        return albumDao.getAll(userId);
    }

    @Override
    public List<Album> getMine(Long userId) {
        return albumDao.getMine(userId);
    }
}