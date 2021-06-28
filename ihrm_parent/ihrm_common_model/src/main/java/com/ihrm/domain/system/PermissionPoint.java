package com.ihrm.domain.system;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created with IDEA
 * Author:xzengsf
 * Date:2018/3/22 10:24
 * Description: 菜单权限实体类
 */
@Entity
@Table(name = "pe_permission_point")
@Getter
@Setter
public class PermissionPoint implements Serializable {
    private static final long serialVersionUID = -1002411490113957485L;

    /**
     * 主键
     */
    @Id
    private String id;

    /**
     * 权限代码
     */
    private String pointClass;

    private String pointIcon;

    private String pointStatus;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPointClass() {
        return pointClass;
    }

    public void setPointClass(String pointClass) {
        this.pointClass = pointClass;
    }

    public String getPointIcon() {
        return pointIcon;
    }

    public void setPointIcon(String pointIcon) {
        this.pointIcon = pointIcon;
    }

    public String getPointStatus() {
        return pointStatus;
    }

    public void setPointStatus(String pointStatus) {
        this.pointStatus = pointStatus;
    }
}