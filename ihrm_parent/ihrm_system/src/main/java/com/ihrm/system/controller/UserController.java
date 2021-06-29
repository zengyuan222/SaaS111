package com.ihrm.system.controller;

import com.ihrm.common.controller.BaseController;
import com.ihrm.common.entity.PageResult;
import com.ihrm.common.entity.Result;
import com.ihrm.common.entity.ResultCode;
import com.ihrm.domain.system.User;
import com.ihrm.domain.system.response.UserResult;
import com.ihrm.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//1.解决跨域
@CrossOrigin
//2.声明restController
@RestController
//3.设置父路径
@RequestMapping(value = "/sys")   //company/deparment
public class UserController  extends BaseController {

    @Autowired
    private UserService userService;


    /**
     * 保存
     * @return
     */
    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public Result save(@RequestBody User user){
        //1.设置保存的企业id
        /**
         * 企业id:目前使用固定值1，以后会解决
         */
        //String companyId = "1";
        user.setCompanyId(companyId);
        user.setCompanyName(companyName);
        //2.调用service完成保存企业
        userService.save(user);
        //3.构造返回结果
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 查询企业的部门列表
     * 指定企业id
     * @return
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Result findAll(int page, int size, @RequestParam Map map) {
        //1.获取当前的企业id
        map.put("companyId",companyId);
        //2.完成查询
        Page<User> pageUser = userService.findAll(map,page,size);
        //3.构造返回结果
        PageResult pageResult = new PageResult(pageUser.getTotalElements(),pageUser.getContent());
        return new Result(ResultCode.SUCCESS, pageResult);
    }

    /**
     * 根据ID查询user
     */
    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    public Result findById(@PathVariable(value = "id")String id){
        // 添加 roleIds(用户已经具有的角色id数组)
        User user = userService.findById(id);
        UserResult userResult = new UserResult(user);
        return new Result(ResultCode.SUCCESS,userResult);
    }

    /**
     * 修改Department
     */
    @RequestMapping(value = "/user/{id}",method = RequestMethod.PUT)
    public Result update(@PathVariable(value = "id") String id,@RequestBody User user){
        //1.设置修改的部门id
        user.setId(id);
        //2.调用service更新
        userService.update(user);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 根据id删除
     */
    @RequestMapping(value = "/user/{id}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable(value = "id") String id){
        userService.deleteById(id);
        return new Result(ResultCode.SUCCESS);
    }


    /**
     * 分配角色
     */
    @RequestMapping(value = "/user/assignRoles", method = RequestMethod.PUT)
    public Result save(@RequestBody Map<String,Object> map) {
        // 1.获取被分配的用户id
        String userId = (String) map.get("id");
        // 2.获取角色的id列表
        List<String> roleIds = (List<String>) map.get("roleIds");
        // 3.完成角色分配
        userService.assignRoles(userId,roleIds);
        return new Result(ResultCode.SUCCESS);
    }



}
