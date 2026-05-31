package HogarBudget.api.Services;

import HogarBudget.api.DTOs.DatosLogin;
import HogarBudget.api.DTOs.DatosRegistro;
import HogarBudget.api.DTOs.DatosTokenResponse;
import HogarBudget.api.Entities.Usuario;
import HogarBudget.api.Security.JwtService;
import HogarBudget.api.repositories.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Transactional
    public void registrar(DatosRegistro datos) {
        if (usuarioRepository.existsByEmail(datos.email())) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(datos.nome());
        usuario.setEmail(datos.email());
        usuario.setSenha(passwordEncoder.encode(datos.senha()));

        usuarioRepository.save(usuario);
    }

    public DatosTokenResponse login(DatosLogin datos) {
        Usuario usuario = usuarioRepository.findByEmail(datos.email())
                .orElseThrow(() -> new IllegalArgumentException("Credenciais inválidas"));

        if (!passwordEncoder.matches(datos.senha(), usuario.getSenha())) {
            throw new IllegalArgumentException("Credenciais inválidas");
        }

        String token = jwtService.generateToken(usuario.getId(), usuario.getEmail());
        return new DatosTokenResponse(token);
    }
}