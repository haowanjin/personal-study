package com.ddup.cloud.user.impl;

import com.ddup.cloud.entity.EsUser;
import com.ddup.cloud.repository.EsUserRepository;
import com.ddup.cloud.service.EsUserService;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: hwj
 * @Description TODO
 * @create: 2021/10/25 18:07
 */
@Service
public class EsUserServiceImpl implements EsUserService {
    @Autowired
    private EsUserRepository userRepository;

    @Override
    public long count() {
        return userRepository.count();
    }

    @Override
    public EsUser save(EsUser esUser) {
        return userRepository.save(esUser);
    }

    @Override
    public void delete(EsUser esUser) {
        userRepository.delete(esUser);
    }

    @Override
    public Iterable<EsUser> getAll() {
        return userRepository.findAll();
    }

    @Override
    public List<EsUser> getByName(String name) {
        List<EsUser> list = new ArrayList<>();
        MatchQueryBuilder builder = new MatchQueryBuilder("name", name);
        Iterable<EsUser> search = userRepository.search(builder);
        search.forEach(e -> list.add(e));
        return list;
    }

    @Override
    public Page<EsUser> pageQuery(Integer pageNo, Integer pageSize, String kw) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchPhraseQuery("name", kw))
                .withPageable(PageRequest.of(pageNo, pageSize))
                .build();
        return userRepository.search(searchQuery);
    }
}
