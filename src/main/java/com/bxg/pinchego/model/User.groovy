package com.bxg.pinchego.model

import com.fasterxml.jackson.annotation.JsonIgnore

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.Table
import javax.persistence.Temporal
import javax.persistence.TemporalType

/**
 * @author gaobin
 * @createDate 2017/1/9
 * @description 用户信息类
*/
@Entity
@Table(name = "core_user")
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id
    /**
     * 用户名
    */
    @Column(length = 64,nullable = false)
    String username
    /**
     * 密码
    */
    @Column(length = 128, nullable = false)
    String password
    /**
     * 邮箱
     */
    @Column(length = 64, nullable = false)
    String email
    /**
     * 最近登陆时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    Date lastLoginDate
    /**
     * 注册时间
     */
    @Temporal(TemporalType.DATE)
    Date registerDate

    /**
     * 访问次数
     */
    @Column(length = 32)
    Integer loginNumber
    /**
     * 员工所拥有的权限
     */
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name = "core_user_permission", joinColumns = @JoinColumn(name = "userId"), inverseJoinColumns = @JoinColumn(name = "permissionId"))
    @JsonIgnore
    Set<Permission> permissions

}
