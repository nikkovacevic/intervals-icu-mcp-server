package activity.control;

import activity.entity.Activity;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.Comparator;
import java.util.List;

@ApplicationScoped
@RegisterForReflection
public class ActivityRepository {

    private final DynamoDbEnhancedClient dynamoDbClient;

    @Inject
    public ActivityRepository(DynamoDbEnhancedClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    private DynamoDbTable<Activity> table;

    @PostConstruct
    void init() {
        this.table = dynamoDbClient.table("Activities", TableSchema.fromBean(Activity.class));
    }

    public void create(Activity activity) {
        table.putItem(activity);
    }

    public List<Activity> getLast10Activities() {
        // no problem with full table scan since I don't expect more than 100 rows.
        return table.scan()
                .items()
                .stream()
                .sorted(Comparator.comparing(Activity::getDate, Comparator.nullsLast(Comparator.reverseOrder())))
                .toList();
    }
}
