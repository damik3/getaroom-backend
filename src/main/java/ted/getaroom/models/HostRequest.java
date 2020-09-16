package ted.getaroom.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class HostRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    public HostRequest() {
    }

    public HostRequest(Long userId) {
        this.userId = userId;
    }
}
