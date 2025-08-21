package io.github.pavansharma36.saas.core.common.storage;

import io.github.pavansharma36.saas.core.common.utils.PreCondition;
import io.github.pavansharma36.saas.core.common.validation.CoreErrorCode;
import io.github.pavansharma36.saas.core.common.validation.ServerRuntimeException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
public class StorageServiceProvider {

  @Getter
  private final StorageService defaultStorageService;
  private final Map<StorageType, StorageService> storageServiceMap;

  public StorageServiceProvider(List<StorageService> storageServices) {
    defaultStorageService =
        storageServices.stream().max(Comparator.comparingInt(StorageService::priority))
            .orElseThrow(() -> new ServerRuntimeException("No storage service found"));
    storageServiceMap = storageServices.stream()
        .collect(java.util.stream.Collectors.toMap(StorageService::type, s -> s));
  }

  public StorageService getStorageService(StorageType storageType) {
    StorageService storageService = storageServiceMap.get(storageType);
    PreCondition.assertNotNull(storageService, CoreErrorCode.STORAGE_TYPE_NOT_FOUND);
    return storageService;
  }
}
