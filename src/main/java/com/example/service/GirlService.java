package com.example.service;

import com.example.entity.Girl;
import com.example.enums.ResultEnum;
import com.example.exception.GirlException;
import com.example.repository.GirlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GirlService {

    @Autowired
    private GirlRepository girlRepository;

    /** 查询所有女生 */
    public List<Girl> findAll() {
        return girlRepository.findAll();
    }

    /** 通过id查询一个女生 */
    public Girl findOne(Integer id) {
        return girlRepository.findOne(id);
    }

    /** 保存 */
    public Girl save(Girl girl) {
        return girlRepository.save(girl);
    }

    /** 删除 */
    public void delete(Integer id) {
        girlRepository.delete(id);
    }

    /** nativeQuery分页查询 */
    public Page<Girl> findPages(Pageable pageable) {
        return girlRepository.findPages(pageable);
    }

    /** nativeuery分页查询小于指定年龄的女生 */
    public Page<Girl> findByAgeBefore(Integer age_max, Pageable pageable) {
        return girlRepository.findByAgeBefore(age_max, pageable);
    }

    /** 根据id获取年龄 */
    public Integer getAge(Integer id) throws Exception {
        Girl girl = girlRepository.findOne(id);
        Integer age = girl.getAge();
        // 验证
        if (age < 10) {
            throw new GirlException(ResultEnum.PRIMARY_SCHOOL);
        } else if (age > 10 && age < 16) {
            throw new GirlException(ResultEnum.MIDDLE_SCHOOLE);
        }
        // 其他逻辑
        return age;
    }

}
