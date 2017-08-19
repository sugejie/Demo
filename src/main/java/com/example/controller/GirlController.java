package com.example.controller;

import com.example.aspect.HttpAspect;
import com.example.entity.Girl;
import com.example.entity.Result;
import com.example.repository.GirlRepository;
import com.example.service.GirlService;
import com.example.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class GirlController {

    @Autowired
    private GirlService girlService;

    private static final Logger logger = LoggerFactory.getLogger(GirlRepository.class);

    /**
     * 查询所有女生
     */
    @GetMapping(value = "/girls")
    public List<Girl> girlList() {
        logger.info("girlList");
        return girlService.findAll();
    }

    /**
     * 查询一个女生
     */
    @GetMapping(value = "/girls/{id}")
    public Girl girlFindOne(@PathVariable("id")Integer id) {
        return girlService.findOne(id);
    }

    /**
     * 添加一个女生
     */
    @PostMapping(value = "/girls")
    public Result<Girl> girlAdd(@Valid Girl girl, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(1, bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success( girlService.save(girl) );
    }

    /**
     * 更新
     */
    @PutMapping(value = "/girls")
    public Girl girlUpdate(Girl girl) {
        return girlService.save(girl);
    }

    /**
     * 删除
     */
    @DeleteMapping(value = "/girls/{id}")
    public void firlDelete(@PathVariable("id")Integer id) {
        girlService.delete(id);
    }

    /**
     * 测试nativeQuery和pageable,分页查询所有女生
     */
    @GetMapping(value = "/girls/pages")
    public Page<Girl> findByPages(@PageableDefault(page = 0, size = 5, sort = {"id"}, direction = Sort.Direction.ASC)Pageable pageable) {
        return girlService.findPages(pageable);
    }

    /**
     * 测试nativeQuery,jpa自动生成sql
     */
    @GetMapping(value = "/girls/age/before/{age}")
    public Page<Girl> agrBefore(
            @PathVariable("age")Integer age_max,
            @PageableDefault(page = 0, size = 5, sort = {"id"}, direction = Sort.Direction.ASC)Pageable pageable) {
        return girlService.findByAgeBefore(age_max, pageable);
    }

    /**
     * 统一异常处理例子：根据id获取女生年龄，
     *      若年龄小于10，返回“你还在上小学吧”
     *      若年龄小于16，返回“你可能还在上初中”
     *      否则执行业务逻辑，如：返回年龄等
     * @param id
     * @throws Exception
     */
    @GetMapping(value = "/girls/age/{id}")
    public Integer getAge(@PathVariable("id")Integer id) throws Exception {
        return girlService.getAge(id);
    }



}
