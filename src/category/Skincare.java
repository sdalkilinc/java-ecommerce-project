package category;

import java.time.LocalDate;
import java.util.UUID;

public class Skincare extends Category{

    public Skincare(UUID id, String name) {
        super(id, name);
    }

    @Override
    public LocalDate findDeliveryDueDate() {
        return null;
    }

}
