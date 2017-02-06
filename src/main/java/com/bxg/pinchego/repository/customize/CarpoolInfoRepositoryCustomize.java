package com.bxg.pinchego.repository.customize;

import com.bxg.pinchego.model.CarpoolInfo;

import java.util.List;
import java.util.Map;

/**
 * @author gaobin
 * @createDate ${Date}
 */
public interface CarpoolInfoRepositoryCustomize {
    List<CarpoolInfo>  findCarpoolInfoByPage(CarpoolInfo carpoolInfo);

}
