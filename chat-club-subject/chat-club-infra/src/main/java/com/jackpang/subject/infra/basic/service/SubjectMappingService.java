package com.jackpang.subject.infra.basic.service;

import com.jackpang.subject.infra.basic.entity.SubjectMapping;

import java.util.List;

/**
 * 题目分类关系表(SubjectMapping)表服务接口
 *
 * @author jackpang
 * @since 2024-03-12 19:57:39
 */
public interface SubjectMappingService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectMapping queryById(Long id);


    /**
     * 新增数据
     *
     * @param subjectMapping 实例对象
     * @return 实例对象
     */
    SubjectMapping insert(SubjectMapping subjectMapping);

    /**
     * 修改数据
     *
     * @param subjectMapping 实例对象
     * @return 实例对象
     */
    int update(SubjectMapping subjectMapping);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param subjectMapping 实例对象
     * @return 对象列表
     */
    List<SubjectMapping> queryLabelId(SubjectMapping subjectMapping);

    void insertBatch(List<SubjectMapping> subjectMappings);
}
