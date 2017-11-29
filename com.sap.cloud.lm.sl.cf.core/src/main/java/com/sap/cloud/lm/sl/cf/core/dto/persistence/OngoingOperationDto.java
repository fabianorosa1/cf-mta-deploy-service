package com.sap.cloud.lm.sl.cf.core.dto.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "ongoing_operation")
@NamedQueries({
    @NamedQuery(name = "find_mta_lock", query = "SELECT oo FROM OngoingOperationDto oo WHERE oo.mtaId = :mtaId AND oo.spaceId = :spaceId AND oo.acquiredLock = true"),
    @NamedQuery(name = "find_all", query = "SELECT oo FROM OngoingOperationDto oo"),
    @NamedQuery(name = "find_all_in_space", query = "SELECT oo FROM OngoingOperationDto oo WHERE oo.spaceId = :spaceId"),
    @NamedQuery(name = "find_all_in_space_desc", query = "SELECT oo FROM OngoingOperationDto oo WHERE oo.spaceId = :spaceId order by oo.startedAt DESC"),
    @NamedQuery(name = "find_all_active_in_space", query = "SELECT oo FROM OngoingOperationDto oo WHERE oo.spaceId = :spaceId AND oo.finalState is NULL"),
    @NamedQuery(name = "find_all_finished_in_space", query = "SELECT oo FROM OngoingOperationDto oo WHERE oo.spaceId = :spaceId AND oo.finalState is NOT NULL") })
public class OngoingOperationDto {

    @Id
    @Column(name = "process_id")
    private String processId;

    @Column(name = "process_type")
    private String processType;

    @Column(name = "started_at")
    private String startedAt;

    @Column(name = "space_id")
    private String spaceId;

    @Column(name = "mta_id")
    private String mtaId;

    @Column(name = "userx")
    private String user;

    @Column(name = "acquired_lock")
    private boolean acquiredLock;

    @Column(name = "final_state")
    private String finalState;

    protected OngoingOperationDto() {
        // Required by JPA
    }

    public OngoingOperationDto(String processId, String processType, String startedAt, String spaceId, String mtaId, String user,
        boolean acquiredLock, String finalState) {
        this.processId = processId;
        this.processType = processType;
        this.startedAt = startedAt;
        this.spaceId = spaceId;
        this.mtaId = mtaId;
        this.user = user;
        this.acquiredLock = acquiredLock;
        this.finalState = finalState;
    }

    public String getProcessId() {
        return processId;
    }

    public String getProcessType() {
        return processType;
    }

    public String getStartedAt() {
        return startedAt;
    }

    public String getSpaceId() {
        return spaceId;
    }

    public String getMtaId() {
        return mtaId;
    }

    public String getUser() {
        return user;
    }

    public boolean hasAcquiredLock() {
        return acquiredLock;
    }

    public String getFinalState() {
        return finalState;
    }

}