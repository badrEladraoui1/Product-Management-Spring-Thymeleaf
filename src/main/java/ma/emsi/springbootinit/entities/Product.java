package ma.emsi.springbootinit.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

//@Data = @Setter + @Getter + ...
@Data @NoArgsConstructor @AllArgsConstructor
@Entity //Pour la création de cette classe en table
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "required field!")
    @Size(min = 3, message = "Name should contain at least 3 character.")
    private String name;

    private String ref;
    private Integer code;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date prodDate;
    private Float price;
}
