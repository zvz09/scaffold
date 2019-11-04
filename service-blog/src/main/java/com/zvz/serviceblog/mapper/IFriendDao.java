package com.zvz.serviceblog.mapper;


import com.zvz.serviceblog.common.PageConfig;
import com.zvz.serviceblog.model.Friendlink;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Pan on 2016/12/1.
 */
@Repository("iFriendDao")
public interface IFriendDao {

    Friendlink selectById(@Param("id") Integer fId);

    int deleteById(@Param("id") Integer fId);

    int insert(Friendlink friendlink);

    int update(Friendlink friendlink);

    List<Friendlink> selectBy(@Param("pageConfig") PageConfig pageConfig);

    int selectCountBy();

    int updateHits(Friendlink friendlink);
}
