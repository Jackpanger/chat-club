package com.jackpang.subject.infra.basic.service.impl;

import com.jackpang.subject.infra.basic.entity.SubjectRadio;
import com.jackpang.subject.infra.basic.mapper.SubjectRadioDao;
import com.jackpang.subject.infra.basic.service.SubjectRadioService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 单选题信息表(SubjectRadio)表服务实现类
 *
 * @author jackpang
 * @since 2024-03-12 23:16:49
 */
@Service("subjectRadioService")
public class SubjectRadioServiceImpl implements SubjectRadioService {
    @Resource
    private SubjectRadioDao subjectRadioDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SubjectRadio queryById(Long id) {
        return this.subjectRadioDao.queryById(id);
    }



    /**
     * 新增数据
     *
     * @param subjectRadio 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectRadio insert(SubjectRadio subjectRadio) {
        this.subjectRadioDao.insert(subjectRadio);
        return subjectRadio;
    }

    /**
     * 修改数据
     *
     * @param subjectRadio 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectRadio update(SubjectRadio subjectRadio) {
        this.subjectRadioDao.update(subjectRadio);
        return this.queryById(subjectRadio.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.subjectRadioDao.deleteById(id) > 0;
    }

    @Override
    public void batchInsert(List<SubjectRadio> subjectRadioList) {
        subjectRadioDao.insertBatch(subjectRadioList);
    }

    @Override
    public List<SubjectRadio> queryByCondition(SubjectRadio subjectRadio) {
        return this.subjectRadioDao.queryAllByLimit(subjectRadio);
    }
}
