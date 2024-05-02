package Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "features", schema = "public")
public class Feature {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    @Length(min = 3, message = "Name must be at least 3 characters")
    @Pattern(regexp = "^Feature-[0-9]{4}$", message = "Name must be alphanumeric")
    private String name;

    @Column(name = "description")
    @Length(min = 10, message = "Description must be at least 10 characters")
    private String description;

    @Column(name = "link_to_upload")
    private String linkToUpload;

    @Column(name = "upload_moment")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime uploadMoment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task")
    private Task task;

}
