package cn.novalue.blog.controller;



import cn.novalue.blog.model.entity.Letter;
import cn.novalue.blog.model.support.Response;
import cn.novalue.blog.model.vo.LetterVO;
import cn.novalue.blog.service.LetterService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 留言表(Letter)表控制层
 *
 * @author Wu yangjie
 * @date 2020-06-19
 */
@RestController
@RequestMapping("letter")
public class LetterController {
    /**
     * 服务对象
     */
    @Autowired
    private LetterService letterService;


    @PostMapping("add")
    public Response add(@RequestBody Letter letter) {
        letterService.add(letter);
        return Response.success();
    }
    @GetMapping("get")
    public IPage<LetterVO> get(
            @RequestParam(value = "userId") Long userId,
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "20") Integer size) {
        return letterService.getLetterByPage(new Page<>(page, size), userId);
    }
}