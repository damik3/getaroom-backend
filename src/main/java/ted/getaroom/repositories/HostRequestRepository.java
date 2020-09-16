package ted.getaroom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ted.getaroom.models.HostRequest;
import ted.getaroom.models.User;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface HostRequestRepository extends JpaRepository<HostRequest, Long> {

    Optional<HostRequest> findByUserId(Long userId);

    @Transactional
    void deleteByUserId(Long userId);

}
