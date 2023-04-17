package com.hodoleg.clonecoding.respository;

import com.hodoleg.clonecoding.domain.Post;
import com.hodoleg.clonecoding.request.PostSearch;

import java.util.List;

public interface PostRepositoryCustom {

    List<Post> getList(PostSearch postSearch);
}
