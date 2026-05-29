// package spring.learning.models;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.Id;
// import lombok.Data;

// // Models to create table

// @Entity
// @Data
// public class Todo{
//     //  generate id
//     @Id
//     // autogenerate id 
//     @GeneratedValue  
//     Long id;
//     String title;
//     String description;
//     Boolean isCompleted;
// }

package spring.learning.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
// @Data - lombark function usrd for getters and sertters wen can specify
// @Getter and @Setter seperately . @Data used for both @Getter and @Setter
public class Todo {
    // - to auto genetate id
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    @NotBlank
    private String title;
    @NotNull
    @NotBlank
    @Size(min = 5, max = 30)
    @Schema(name = "title", example = "Complete Spring Boot")
    private String description;

    private Boolean isCompleted;

    @Email
    private String email;
    @Pattern(regexp = "^[0-9]{10}$")
    private String mobile;

    // DEFAULT CONSTRUCTOR
    public Todo() {
    }

    // GETTERS

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    // SETTERS

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIsCompleted(Boolean completed) {
        isCompleted = completed;
    }
}