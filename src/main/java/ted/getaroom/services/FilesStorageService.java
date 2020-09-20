package ted.getaroom.services;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService {
    public void init();

    public void save(MultipartFile file, String roomId);

    public Resource load(String filename);

    public Resource load(String roomId, String filename);

    public void deleteAll();

    public Stream<Path> loadAll();

    public Stream<Path> loadAll(String roomId);
}
