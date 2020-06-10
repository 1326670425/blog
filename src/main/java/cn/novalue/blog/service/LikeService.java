package cn.novalue.blog.service;

import cn.novalue.blog.model.params.LikeParam;
import cn.novalue.blog.model.vo.BaseContentVO;

public interface LikeService {
    Boolean handleRequest(LikeParam likeParam);
    void handleVO(BaseContentVO baseContentVO, Integer type);
}
