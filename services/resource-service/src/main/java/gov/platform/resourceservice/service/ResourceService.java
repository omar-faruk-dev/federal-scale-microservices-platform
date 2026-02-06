package gov.platform.resourceservice.service;

import gov.platform.resourceservice.dto.ResourceRequest;
import gov.platform.resourceservice.entity.ResourceRecord;
import gov.platform.resourceservice.repo.ResourceRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ResourceService {

    private final ResourceRepository repository;

    public ResourceService(ResourceRepository repository) {
        this.repository = repository;
    }

    public ResourceRecord create(ResourceRequest request) {
        ResourceRecord record = new ResourceRecord();
        record.setName(request.name());
        record.setCategory(request.category());
        record.setDescription(request.description());
        return repository.save(record);
    }

    @Cacheable(cacheNames = "resources")
    public Page<ResourceRecord> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public ResourceRecord get(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Resource not found"));
    }

    @CacheEvict(cacheNames = "resources", allEntries = true)
    public ResourceRecord update(Long id, ResourceRequest request) {
        ResourceRecord record = get(id);
        record.setName(request.name());
        record.setCategory(request.category());
        record.setDescription(request.description());
        return repository.save(record);
    }

    @CacheEvict(cacheNames = "resources", allEntries = true)
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
