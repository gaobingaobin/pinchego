package com.bxg.pinchego.model

import javax.persistence.*

/**
 * @author lidu
 * @createDate 2016/11/7
 * @description 菜单
 */
@Entity(name = "core_menu")
class Menu implements Serializable {
    private static final long serialVersionUID = 6996386421824822733L

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    /**
     * 菜单名称
     */
    @Column(nullable = false, unique = true)
    String name;

    /**
     * 上级菜单
     */
    @ManyToOne
    @JoinColumn(name = "parentId")
    Menu parentMenu;

    /**
     * 请求的url
     */
    @Column
    String url;

    /**
     * 菜单图标
     */
    String icon;

    /**
     * 排序
     */
    Integer sort;

    Menu() {}

    Menu(Integer id){
        this.id = id;
    }
}
