import java.util.UUID;

public class Product {
    private final String name;
    private final UUID id;

    public Product(String prdName) {
        name = prdName;
        id = UUID.randomUUID();
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }
}
