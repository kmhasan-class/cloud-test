package bd.edu.seu.studentservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
@EqualsAndHashCode(of = {"id", "name", "dateOfBirth"})
//@EqualsAndHashCode(of = {"id"})
public class Student {
    @Id
    private long id;
    private String name;
    private LocalDate dateOfBirth;
}
