package cn.novalue.blog.service.impl;

import cn.novalue.blog.dao.PictureDao;
import cn.novalue.blog.model.entity.Picture;
import cn.novalue.blog.service.PictureService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 照片管理(Picture)表服务实现类
 *
 * @author makejava
 * @since 2020-09-02 08:49:18
 */
@Service("pictureService")
public class PictureServiceImpl extends ServiceImpl<PictureDao,Picture> implements PictureService {
    @Resource
    private PictureDao pictureDao;

    @Override
    public Picture queryById(Long id) {
        return this.pictureDao.queryById(id);
    }

    @Override
    public List<Picture> queryAllByLimit(int offset, int limit) {
        return this.pictureDao.queryAllByLimit(offset, limit);
    }

    @Override
    public Picture insert(Picture picture) {
        this.pictureDao.insert(picture);
        return picture;
    }

    @Override
    public Picture update(Picture picture) {
        this.pictureDao.update(picture);
        return this.queryById(picture.getId());
    }

    @Override
    public boolean deleteById(Long id) {
        return this.pictureDao.deleteById(id) > 0;
    }

    @Override
    public List<Picture> find(Long albumId) {
        return this.pictureDao.find(albumId);
    }

    @Override
    public IPage<Picture> getPictureByPage(Page<?> page, Long albumId) {
        IPage<Picture> pictureIPage;
        pictureIPage = pictureDao.getPictrueByPage(page,albumId);
        List<Picture> pictures = pictureIPage.getRecords();
        pictureIPage.setRecords(pictures);
        return pictureIPage;
    }
}