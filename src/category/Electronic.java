package category;

import java.time.LocalDate;
import java.util.UUID;

public class Electronic extends Category{

    public Electronic(UUID id, String name) {
        super(id, name);
    }

    @Override
    public LocalDate findDeliveryDueDate() {
        return null;
    }

    @Override
    public String generateCategoryCode() {
        return "EL-" + getId().toString().substring(0,8);
    }

}
