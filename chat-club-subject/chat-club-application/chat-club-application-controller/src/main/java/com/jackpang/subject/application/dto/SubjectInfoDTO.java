package com.jackpang.subject.application.dto;

import com.jackpang.subject.common.entity.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 题目信息表(SubjectInfo)实体类
 *
 * @author jackpang
 * @since 2024-03-12 23:16:22
 */
@Data
public class SubjectInfoDTO extends PageInfo implements Serializable {
    private static final long serialVersionUID = -28487292663270843L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 题目名称
     */
    private String subjectName;
    /**
     * 题目难度
     */
    private Integer subjectDifficult;
    /**
     * 出题人名
     */
    private String settleName;
    /**
     * 题目类型 1单选 2多选 3判断 4简答
     */
    private Integer subjectType;
    /**
     * 题目分数
     */
    private Integer subjectScore;
    /**
     * 题目解析
     */
    private String subjectParse;
    /**
     * 题目答案
     */
    private String subjectAnswer;
    /**
     * 分类ID
     */
    private List<Long> categoryIds;
    /**
     * 标签ID
     */
    private List<Long> labelIds;
    /**
     * 答案选项
     */
    private List<SubjectAnswerDTO> optionList;
    /**
     * 标签名称
     */
    private List<String> labelName;

    private Long categoryId;
    private Long labelId;

}

