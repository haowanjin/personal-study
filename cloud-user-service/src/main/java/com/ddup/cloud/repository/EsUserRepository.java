package com.ddup.cloud.repository;

import com.ddup.cloud.entity.EsUser;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: hwj
 * @Description TODO
 * @create: 2021/10/25 18:04
 */
@Repository
public interface EsUserRepository extends ElasticsearchRepository<EsUser, String> {
}
