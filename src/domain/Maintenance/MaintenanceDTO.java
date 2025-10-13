package domain.Maintenance;

import java.time.OffsetDateTime;

public class MaintenanceDTO {
    private int id;
    private int objectId;
    private String description;
    private Integer performedBy;
    private OffsetDateTime lastModification;
    private boolean isDeleted;

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
    public boolean isDeleted() { return isDeleted; }
}
