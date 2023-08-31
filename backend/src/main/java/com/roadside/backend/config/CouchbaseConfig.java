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
    // Get configuration values from YAML properties
    @Value("${app.couchbase.connection-string}")
    private String connectionString;
    @Value("${app.couchbase.user-name}")
    private String userName;
    @Value("${app.couchbase.password}")
    private String password;
    @Value("${app.couchbase.bucket-user}")
    private String bucketUser;
    // Override methods to provide Couchbase connection details
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
    // Configure repository operations mapping
    @Override
    public void configureRepositoryOperationsMapping(RepositoryOperationsMapping mapping) {
        try {
            // Map the User entity to the appropriate CouchbaseTemplate
            mapping.mapEntity(User.class, getCouchbaseTemplate(bucketUser));
            // You can map other entities in a similar manner if needed
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    // Create and return a CouchbaseTemplate for the specified bucketName
    @SneakyThrows
    private CouchbaseTemplate getCouchbaseTemplate(String bucketName) throws Exception {
        CouchbaseTemplate template = new CouchbaseTemplate(couchbaseClientFactory(bucketName),
                mappingCouchbaseConverter(couchbaseMappingContext(customConversions()),
                        new CouchbaseCustomConversions(Collections.emptyList())));
        template.setApplicationContext(context);
        return template;
    }
    // Create and return a CouchbaseClientFactory for the specified bucketName
    private CouchbaseClientFactory couchbaseClientFactory(String bucketName) {
        return new SimpleCouchbaseClientFactory(couchbaseCluster(couchbaseClusterEnvironment()), bucketName, this.getScopeName());
    }
}
