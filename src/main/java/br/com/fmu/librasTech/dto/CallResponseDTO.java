package br.com.fmu.librasTech.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CallResponseDTO {

    private Long id;
    private String url;
}
