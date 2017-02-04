package com.bxg.pinchego.repository.customize.impl;

import com.bxg.pinchego.Util.StringUtil;
import com.bxg.pinchego.model.CarpoolInfo;
import com.bxg.pinchego.repository.customize.CarpoolInfoRepositoryCustomize;
import com.sun.deploy.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

/**
 * @author gaobin
 * @createDate ${Date}
 */
public class CarpoolInfoRepositoryCustomizeImpl implements CarpoolInfoRepositoryCustomize {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<CarpoolInfo> getCarpoolInfo(CarpoolInfo carpoolInfo) {
        StringBuffer sql = new StringBuffer();
        sql.append("from CarpoolInfo  c where 1=1");
        if(StringUtil.isNotBlank(carpoolInfo.getStartAddress())){
            sql.append(" and c.startAddress like '%"+carpoolInfo.getStartAddress()+"%'");
        }
        if(StringUtil.isNotBlank(carpoolInfo.getStartAddress())){
            sql.append(" and c.endAddress like '%"+carpoolInfo.getEndAddress()+"%'");
        }
        if(StringUtil.isNotBlank(carpoolInfo.getStartAddress())){
            sql.append(" and c.endAddress like '%"+carpoolInfo.getEndAddress()+"%'");
        }
        Query query = em.createNativeQuery(sql.toString());
        List result = query.getResultList();
        return result;
    }
}
