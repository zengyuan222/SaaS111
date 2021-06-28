package com.ihrm.system.service;

import com.ihrm.common.entity.PageResult;
import com.ihrm.common.service.BaseService;
import com.ihrm.common.utils.IdWorker;
import com.ihrm.domain.system.Role;
import com.ihrm.system.dao.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * 角色操作业务逻辑层
 */
@Service
public class RoleService extends BaseService {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private RoleDao roleDao;

    /**
     * 添加角色
     */
    public void save(Role role){
        //填充其他参数
        role.setId(idWorker.nextId() + "");
        roleDao.save(role);
    }

    /**
     * 更新角色
     */
    public void  update(Role role){
        Role targer = roleDao.getOne(role.getId());
        targer.setDescription(role.getDescription());
        targer.setName(role.getName());
        roleDao.save(targer);
    }

    /**
     *  根据ID查询角色
     */
    public Role findById(String id){
        return roleDao.findById(id).get();
    }

    /**
     *删除角色
     */
    public void delete(String id){
        roleDao.deleteById(id);
    }

    /**
     *多条件查询
     */
    public Page<Role> findSearch(String companyId, int page, int size){
        Specification<Role> specification = new Specification<Role>() {
            @Override
            public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("companyId").as(String.class),companyId);
            }
        };
        return roleDao.findAll(specification, PageRequest.of(page-1,size));
    }

    /**
     * 查询所有
     */
    public List<Role> findAll(String companyId){
        return roleDao.findAll(getSpec(companyId));
    }

    /**
     * 分页查询
     */
    public Page<Role> findByPage(String companyId, int page, int size){
        return roleDao.findAll(getSpec(companyId), PageRequest.of(page-1, size));
    }
}
