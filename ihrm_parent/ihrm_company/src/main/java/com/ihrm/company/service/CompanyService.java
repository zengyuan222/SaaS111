package com.ihrm.company.service;

import com.ihrm.common.utils.IdWorker;
import com.ihrm.company.dao.CompanyDao;
import com.ihrm.domain.company.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 保存企业
     *  1.配置idwork到工程(配置到当前工程,在启动类中配置)
     *  2.在service中注入idwork
     *  3.通过idwork生成id
     *  4.保存企业
     */
    public void add(Company company){
        //基本属性的设置
        String id = idWorker.nextId() + "";
        company.setId(id);
        // 默认的状态
        company.setAuditState("0");//0：未审核，1:已审核
        company.setState(1); //0:未激活,1:已激活
        companyDao.save(company);
    };
    /**
     * 更新企业
     * 1.参数:Company
     * 2.(springdatajps内部执行流程，更新先查再更新,如果调用save方法前先查询可以查出来就更新，查不出就保存)根据id查询企业对象
     * 3.设置修改的属性
     * 4.调用dao完成更新
     */
    public void update(Company company){
        Company temp = companyDao.findById(company.getId()).get();//datajpa内部执行流程先查查不出存，查出来再更新
        temp.setName(company.getName());
        temp.setCompanyPhone(company.getCompanyPhone());
        companyDao.save(temp);
    }

    /**
     * 删除企业
     */
    public void deleteById(String id){
        companyDao.deleteById(id);
    }

    /**
     * 根据id查询企业   {findById(id).get()}:这个jpa特有语法特征
     */
    public Company findById(String id){
        return companyDao.findById(id).get();
    }

    /**
     * 查询企业列表
     */
    public List<Company> findAll(){
        return companyDao.findAll();
    }

}
