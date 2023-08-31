package com.roadside.backend.repository;

import com.roadside.backend.model.User;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.Query;

public interface UserRepo extends CouchbaseRepository<User, String> {
    // Custom query method to find a user by their email
    @Query("#{#n1ql.selectEntity} WHERE email = $1")
    User findByEmail(String email);
    @Query("#{#n1ql.selectEntity} WHERE username = $1")
    User findByUsername(String username);
}
