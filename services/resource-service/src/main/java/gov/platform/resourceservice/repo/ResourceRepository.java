package gov.platform.resourceservice.repo;

import gov.platform.resourceservice.entity.ResourceRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<ResourceRecord, Long> {
}
