package com.bxg.pinchego.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.Temporal
import javax.persistence.TemporalType

/**
 * @author gaobin
 * @createDate 2017/1/9
 * @description 用户信息类
*/
@Entity
@Table(name = "pinche_user")
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id
    /**
     * 用户名
    */
    @Column(length = 64,nullable = false)
    String username
    /**
     * 密码
    */
    @Column(length = 32, nullable = false)
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

}
