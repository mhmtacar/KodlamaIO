package Kodlama.io.Devs.business.responses.language;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetLanguageResponse {
    private int id;
    private String name;
}
