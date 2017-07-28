package com.bxg.pinchego.model

import javax.persistence.*

/**
 * @author lidu
 * @createDate 2016/10/25
 * @description 权限表
 */
@Entity(name = "core_permission")
class Permission implements Serializable{
    private static final long serialVersionUID = 8240842977182267875L

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id

    /**
     * 权限
     */
    @Column(unique = true,nullable = false,length = 50)
    String name

    /**
     * 权限名称
     */
    @Column(unique = false,nullable = false,length = 50)
    String label

    /**
     * 所属菜单
     */
    @ManyToOne
    @JoinColumn(name = "menuId")
    Menu menu

    /**
     * 标识是属于页面权限还是菜单权限
     */
    Boolean pagePermissionFlag

    Permission() {
    }
    Permission(Integer id) {
        this.id = id
    }
}
