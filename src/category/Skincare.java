package category;

import java.time.LocalDateTime;
import java.util.UUID;

public class Skincare extends Category{

    public Skincare(UUID id, String name) {
        super(id, name);
    }

    @Override
    public LocalDateTime findDeliveryDueDate() {
        return LocalDateTime.now();
    }

}
