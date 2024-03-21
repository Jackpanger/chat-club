package com.jackpang.subject.infra.basic.service.impl;

import com.jackpang.subject.infra.basic.entity.SubjectBrief;
import com.jackpang.subject.infra.basic.mapper.SubjectBriefDao;
import com.jackpang.subject.infra.basic.service.SubjectBriefService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 简答题(SubjectBrief)表服务实现类
 *
 * @author jackpang
 * @since 2024-03-12 23:17:54
 */
@Service("subjectBriefService")
public class SubjectBriefServiceImpl implements SubjectBriefService {
    @Resource
    private SubjectBriefDao subjectBriefDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SubjectBrief queryById(Long id) {
        return this.subjectBriefDao.queryById(id);
    }


    /**
     * 新增数据
     *
     * @param subjectBrief 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectBrief insert(SubjectBrief subjectBrief) {
        this.subjectBriefDao.insert(subjectBrief);
        return subjectBrief;
    }

    /**
     * 修改数据
     *
     * @param subjectBrief 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectBrief update(SubjectBrief subjectBrief) {
        this.subjectBriefDao.update(subjectBrief);
        return this.queryById(subjectBrief.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.subjectBriefDao.deleteById(id) > 0;
    }

    @Override
    public List<SubjectBrief> queryByCondition(SubjectBrief subjectBrief) {
        return this.subjectBriefDao.queryAllByLimit(subjectBrief);
    }
}
