package com.bxg.pinchego.repository;

import com.bxg.pinchego.model.CarpoolInfo;
import com.bxg.pinchego.repository.customize.CarpoolInfoRepositoryCustomize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author gaobin
 * @createDate ${Date}
 */

public interface CarpoolInfoRepository extends JpaRepository<CarpoolInfo, Integer> ,CarpoolInfoRepositoryCustomize{

}
