package Kodlama.io.Devs.business.responses.technology;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTechnologyResponse {
    private int id;
    private String name;
    private int languageId;
    private String languageName;  // Language ismi de eklendi
}

