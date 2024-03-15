package com.jackpang.subject.domain.entity;

import com.jackpang.subject.common.entity.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * SubjectOptionBO
 *
 * @author jackpang
 * @since 2024-03-12 23:16:22
 */
@Data
public class SubjectOptionBO extends PageInfo implements Serializable {
    /**
     * 题目答案
     */
    private String subjectAnswer;
    /**
     * 答案选项
     */
    private List<SubjectAnswerBO> optionList;

}

