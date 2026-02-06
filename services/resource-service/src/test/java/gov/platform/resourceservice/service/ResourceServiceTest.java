package gov.platform.resourceservice.service;

import gov.platform.resourceservice.dto.ResourceRequest;
import gov.platform.resourceservice.entity.ResourceRecord;
import gov.platform.resourceservice.repo.ResourceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ResourceServiceTest {

    @Mock
    private ResourceRepository repository;

    @InjectMocks
    private ResourceService service;

    @Test
    void createShouldPersistRecord() {
        ResourceRecord record = new ResourceRecord();
        record.setId(1L);
        record.setName("Asset");

        when(repository.save(any(ResourceRecord.class))).thenReturn(record);

        ResourceRecord created = service.create(new ResourceRequest("Asset", "cat", "desc"));

        assertThat(created.getId()).isEqualTo(1L);
        assertThat(created.getName()).isEqualTo("Asset");
    }
}
