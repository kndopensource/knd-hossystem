package com.hos.base.i18n.service;

import com.hos.base.pojo.page.LayuiPageInfo;
import com.hos.base.i18n.entity.Translation;
import com.hos.base.i18n.model.params.TranslationParam;
import com.hos.base.i18n.model.result.TranslationResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 多语言表 服务类
 * </p>
 *
 * @author dev
 * @since 2019-10-17
 */
public interface TranslationService extends IService<Translation> {

    /**
     * 新增
     *
     * @author dev
     * @Date 2019-10-17
     */
    void add(TranslationParam param);

    /**
     * 删除
     *
     * @author dev
     * @Date 2019-10-17
     */
    void delete(TranslationParam param);

    /**
     * 更新
     *
     * @author dev
     * @Date 2019-10-17
     */
    void update(TranslationParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author dev
     * @Date 2019-10-17
     */
    TranslationResult findBySpec(TranslationParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author dev
     * @Date 2019-10-17
     */
    List<TranslationResult> findListBySpec(TranslationParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author dev
     * @Date 2019-10-17
     */
     LayuiPageInfo findPageBySpec(TranslationParam param);

}
