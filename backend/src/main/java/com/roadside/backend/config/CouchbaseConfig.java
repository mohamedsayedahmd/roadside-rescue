package com.roadside.backend.config;

import com.roadside.backend.model.User;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.CouchbaseClientFactory;
import org.springframework.data.couchbase.SimpleCouchbaseClientFactory;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.data.couchbase.core.convert.CouchbaseCustomConversions;
import org.springframework.data.couchbase.repository.config.RepositoryOperationsMapping;
import java.util.Collections;

@Configuration
public class CouchbaseConfig extends AbstractCouchbaseConfiguration {
    @Autowired
    private ApplicationContext context;
    // Get all of these fields from the YAML
    @Value("${app.couchbase.connection-string}")
    private String connectionString;
    @Value("${app.couchbase.user-name}")
    private String userName;
    @Value("${app.couchbase.password}")
    private String password;
    @Value("${app.couchbase.bucket-user}")
    private String bucketUser;

    @Override
    public String getConnectionString() {
        return connectionString;
    }
    @Override
    public String getUserName() {
        return userName;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getBucketName() {
        return bucketUser;
    }

    @Override
    public void configureRepositoryOperationsMapping(RepositoryOperationsMapping mapping) {
        try {
            mapping.mapEntity(User.class, getCouchbaseTemplate(bucketUser));
//            mapping.mapEntity(User.class,getCouchbaseTemplate("NewBUCKET"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    private CouchbaseTemplate getCouchbaseTemplate(String bucketName) throws Exception {
        CouchbaseTemplate template = new CouchbaseTemplate(couchbaseClientFactory(bucketName),
                mappingCouchbaseConverter(couchbaseMappingContext(customConversions()),
                        new CouchbaseCustomConversions(Collections.emptyList())));
        template.setApplicationContext(context);
        return template;
    }

    private CouchbaseClientFactory couchbaseClientFactory(String bucketName) {
        return new SimpleCouchbaseClientFactory(couchbaseCluster(couchbaseClusterEnvironment()), bucketName, this.getScopeName());
    }
}
