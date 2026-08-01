package common.db;

import activity.entity.Activity;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.configuration.ConfigUtils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.ResourceInUseException;

@ApplicationScoped
public class DevTableInitializer {

    private final DynamoDbClient dynamoDbClient;

    @Inject
    public DevTableInitializer(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    void onStart(@Observes StartupEvent ev) {
        if (ConfigUtils.isProfileActive("dev") || ConfigUtils.isProfileActive("test")) {
            DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
                    .dynamoDbClient(dynamoDbClient)
                    .build();

            DynamoDbTable<Activity> table = enhancedClient.table("Activities", TableSchema.fromBean(Activity.class));

            try {
                table.createTable();
            } catch (ResourceInUseException e) {
                // Table already exists, ignore
            } catch (Exception e) {
                System.err.println("Could not create dev table: " + e.getMessage());
            }
        }
    }

}
