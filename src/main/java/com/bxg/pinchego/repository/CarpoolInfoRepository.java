package com.bxg.pinchego.repository;

import com.bxg.pinchego.model.CarpoolInfo;
import com.bxg.pinchego.repository.customize.CarpoolInfoRepositoryCustomize;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author gaobin
 * @createDate ${Date}
 */
public interface CarpoolInfoRepository extends JpaRepository<CarpoolInfo, Integer> ,CarpoolInfoRepositoryCustomize{

}
