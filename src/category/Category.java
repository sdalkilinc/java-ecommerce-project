package category;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public abstract class Category {

    private UUID id;
    private String name;

    public Category(UUID id, String name) {
        this.id = id;
        this.name = name;
    }


    public abstract LocalDate findDeliveryDueDate();

    public String generateCategoryCode(){
        return id.toString().substring(0,8).concat("-").concat(name.substring(0,2));
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
