package com.bxg.pinchego.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.persistence.Temporal
import javax.persistence.TemporalType

/**
 * @author gaobin
 * @createDate ${Date}  
 */
@Entity
@Table(name = "car_pool_info")
class CarpoolInfo {
    private static final long serialVersionUID = 8197409369264035839L
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id
    /**
     * 拼车信息所有人
     */
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    User user
    /**
     * 出发地
     */
    @Column(length = 128, nullable = false)
    String startAddress
    /**
     * 目的地
     */
    @Column(length = 128, nullable = false)
    String endAddress
    /**
     * 途径地A
     */
    @Column(length = 64, nullable = true)
    String middleAddress_a
    /**
     * 途径地B
     */
    @Column(length = 64, nullable = true)
    String middleAddress_b
    /**
     * 途径地a
     */
    @Column(length = 64, nullable = true)
    String middleAddress_c
    /**
     * 途径地a
     */
    @Column(length = 64, nullable = true)
    String middleAddress_d
    /**
     * 出发时间
    */

    @Column(length = 32,nullable = false)
    String startDate
    /**
     * 返回时间
     */
    @Column(length = 32,nullable = true)
     String returnDate
    /**
     * 车型
     */
    @Column(length = 12, nullable = false)
    String carType
    /**
     * 容纳人数
     */
    @Column(length = 12, nullable = false)
    Integer personNum
    /**
    * 目的地费用
    */
    @Column(length = 24,nullable = false)
    Double amount
    /**
     * 途径A地费用
     */
    @Column(length = 24)
    Double amount_A
    /**
     * 途径B地费用
     */
    @Column(length = 24)
    Double amount_B
    /**
     * 途径C地费用
     */
    @Column(length = 24)
    Double amount_C
    /**
     * 途径D地费用
     */
    @Column(length = 24)
    Double amount_D
    /**
     * 备注
     */
    @Column(length = 256,nullable = true)
    String remark


}