package com.hos.sys.modular.system.service;

import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hos.sys.modular.system.entity.SysRole;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @Author scott
 * @since 2018-12-19
 */
public interface ISysRoleService extends IService<SysRole> {

    /**
     * 导入 excel ，检查 roleCode 的唯一性
     *
     * @param file
     * @param params
     * @return
     * @throws Exception
     */
    ResponseData importExcelCheckRoleCode(MultipartFile file, ImportParams params) throws Exception;

    /**
     * 删除角色
     * @param roleid
     * @return
     */
    public boolean deleteRole(String roleid);

    /**
     * 批量删除角色
     * @param roleids
     * @return
     */
    public boolean deleteBatchRole(String[] roleids);

}
