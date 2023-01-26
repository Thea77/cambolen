package co.istad.cambolen.features.auth.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
public class EmailConfirmationDto {

    @Email
    @NotBlank
    @JsonProperty("email")
    private String value;

}
