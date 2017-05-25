package com.bxg.pinchego.model

import javax.persistence.*

/**
 * Created by it_xyl on 2016/10/14.
 * 权限角色
 */
@Entity(name = "core_role")
class Role implements Serializable{
    private static final long serialVersionUID = 6325070380333385241L

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Column(length = 30, unique = true)
    String name;

    @ManyToMany
    @JoinTable(name = "core_role_permission",joinColumns = @JoinColumn(name = "roleId"), inverseJoinColumns = @JoinColumn(name = "permissionId"))
    Set<Permission> permissions;
}
