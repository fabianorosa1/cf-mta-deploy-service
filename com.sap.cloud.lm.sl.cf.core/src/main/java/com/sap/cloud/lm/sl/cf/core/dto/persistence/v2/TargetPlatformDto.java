package com.sap.cloud.lm.sl.cf.core.dto.persistence.v2;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.sap.cloud.lm.sl.common.util.XmlUtil;
import com.sap.cloud.lm.sl.mta.model.v2_0.TargetPlatform;

@Entity(name = "TargetPlatform2")
@Access(AccessType.FIELD)
@NamedQuery(name = "find_all_v2", query = "SELECT tp FROM TargetPlatform2 tp")
@Table(name = "target_platform_v2")
public class TargetPlatformDto extends com.sap.cloud.lm.sl.cf.core.dto.serialization.TargetPlatformDto {

    @Id
    private String name;
    @Column(nullable = false)
    private String type;
    @Lob
    @Column(name = "xml_content", nullable = false)
    protected String xmlContent;

    protected TargetPlatformDto() {
        // Required by JPA
    }

    public TargetPlatformDto(TargetPlatform platform) {
        this.name = platform.getName();
        this.type = platform.getType();
        this.xmlContent = XmlUtil.toXml(new com.sap.cloud.lm.sl.cf.core.dto.serialization.v2.TargetPlatformDto(platform));
    }

    @Override
    public TargetPlatform toTargetPlatform() {
        return XmlUtil.fromXml(xmlContent, com.sap.cloud.lm.sl.cf.core.dto.serialization.v2.TargetPlatformDto.class).toTargetPlatform();
    }

}