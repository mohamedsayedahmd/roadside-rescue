package com.roadside.backend.repository;

import com.roadside.backend.model.User;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.Query;

public interface UserRepo extends CouchbaseRepository<User, String> {
    @Query("#{#n1ql.selectEntity} WHERE email = $1")
    User findByEmail(String email);

}
