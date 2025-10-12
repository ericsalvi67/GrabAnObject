package domain.Maintenance;

import java.time.OffsetDateTime;

public class MaintenanceDTO {
    private final int id;
    private final int objectId;
    private final String description;
    private final Integer performedBy;
    private final OffsetDateTime lastModification;

    public MaintenanceDTO(int id, int objectId, String description, Integer performedBy, OffsetDateTime lastModification) {
        this.id = id;
        this.objectId = objectId;
        this.description = description;
        this.performedBy = performedBy;
        this.lastModification = lastModification;
    }

    public int getId() { return id; }
    public int getObjectId() { return objectId; }
    public String getDescription() { return description; }
    public Integer getPerformedBy() { return performedBy; }
    public OffsetDateTime getLastModification() { return lastModification; }
}
