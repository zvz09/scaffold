package com.zvz.serviceblog.mapper;


import com.zvz.serviceblog.common.PageConfig;
import com.zvz.serviceblog.model.Project;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Pan on 2016/12/27.
 */
@Repository("iProjectDao")
public interface IProjectDao {

    Integer insert(Project project);

    Integer delete(@Param("id") Long projectId);

    Integer update(Project project);

    Integer updateHits(Project project);

    List<Project> selectBy(@Param("project") Project project,
                           @Param("pageConfig") PageConfig pageConfig);

    Integer selectCountBy(@Param("project") Project project);

    Project selectById(@Param("id") Long id, @Param("status") boolean status);

}
