package com.jackpang.subject.infra.basic.service;

import com.jackpang.subject.infra.basic.entity.SubjectRadio;

import java.util.List;

/**
 * 单选题信息表(SubjectRadio)表服务接口
 *
 * @author jackpang
 * @since 2024-03-12 23:16:49
 */
public interface SubjectRadioService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectRadio queryById(Long id);


    /**
     * 新增数据
     *
     * @param subjectRadio 实例对象
     * @return 实例对象
     */
    SubjectRadio insert(SubjectRadio subjectRadio);

    /**
     * 修改数据
     *
     * @param subjectRadio 实例对象
     * @return 实例对象
     */
    SubjectRadio update(SubjectRadio subjectRadio);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    void batchInsert(List<SubjectRadio> subjectRadioList);

    List<SubjectRadio> queryByCondition(SubjectRadio subjectRadio);
}
