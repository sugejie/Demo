package com.example.repository;

import com.example.entity.Girl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GirlRepository extends JpaRepository<Girl, Integer> {

    /**
     * 测试nativeQuery和pageable，分页查询所有女生
     * 注意：
     *      1：使用navticeQuery必须在@Entity(name="girl")用name明确对应关系
     *      2：nativeQuery不支持nativeQuery=true，只能用hql
     * @param pageable
     * @return
     */
    @Query(value = "SELECT g FROM girl g")
    public Page<Girl> findPages(Pageable pageable);

    /**
     * 测试jpa根据类名自动生成sql，分页查询小于指定年龄的女生
     * @param age
     * @param pageable
     * @return
     */
    public Page<Girl> findByAgeBefore(Integer age, Pageable pageable);

}
