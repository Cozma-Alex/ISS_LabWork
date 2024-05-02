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
@Table(name = "tasks", schema = "public")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "task_name")
    @Length(min = 6, message = "Name must be at least 6 characters")
    @Pattern(regexp = "^TASK-[0-9]{4}$", message = "Name must be in the format TASK-xxxx where xxxx is a 4-digit number")
    private String taskName;

    @Column(name = "details")
    @Length(min = 10, message = "Details must be at least 10 characters")
    private String details;

    @Column(name = "deadline")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime deadline;

    @Column(name = "status")
    @Pattern(regexp = "^(Todo|In_Progress|Done|Canceled)$", message = "Status must be either Todo, In_Progress, Done , or Canceled")
    private String status;

    @Column(name = "priority")
    @Pattern(regexp = "^(Low|Medium|High)$", message = "Priority must be either Low, Medium, or High")
    private String priority;

    @Column(name = "type")
    @Pattern(regexp = "^(Bug|Feature|Improvement)$", message = "Type must be either Bug, Feature, or Improvement (case-sensitive)")
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

}
