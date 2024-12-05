package pe.gino1nobelio.proyecto_citas_optima.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario_info")
public class UsuarioInfo {

    @Id
    @Column(name="id_usuario")
    private Long idUsuarioInfo;

    private String nombres;
    private String apellidos;
    private String correo;

    @Column(length = 12)
    private Integer celular;

    private String direccion;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

}
