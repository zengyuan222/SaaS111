package com.ihrm.domain.system;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "pe_permission")
//@Getter
//@Setter
//@NoArgsConstructor
@DynamicInsert(true)
@DynamicUpdate(true)
public class Permission implements Serializable {
    private static final long serialVersionUID = -4990810027542971546L;
    /**
     * 主键
     */
    @Id
    private String id;
    /**
     * 权限名称
     */
    private String name;
    /**
     * 权限类型 1为菜单 2为功能 3为API
     */
    private Integer type;

    /**
     * 权限标识/编码
     */
    private String code;

    /**
     * 权限描述
     */
    private String description;

    //哪一个菜单下的按钮  一个权限  菜单 --- 多个按钮(父权限的id是什么)
    private String pid;

    /**
     * 可见状态
     */
    private Integer enVisible;

    public Permission() {
    }

    public Permission(String name, Integer type, String code, String description) {
        this.name = name;
        this.type = type;
        this.code = code;
        this.description = description;
    }

    public Permission(String id, String name, Integer type, String code, String description, String pid, Integer enVisible) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.code = code;
        this.description = description;
        this.pid = pid;
        this.enVisible = enVisible;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Integer getEnVisible() {
        return enVisible;
    }

    public void setEnVisible(Integer enVisible) {
        this.enVisible = enVisible;
    }
}