package HogarBudget.api.Controllers;

import HogarBudget.api.DTOs.DatosLogin;
import HogarBudget.api.DTOs.DatosRegistro;
import HogarBudget.api.DTOs.DatosTokenResponse;
import HogarBudget.api.Services.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid DatosRegistro datos) {
        authService.registrar(datos);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<DatosTokenResponse> login(@RequestBody @Valid DatosLogin datos) {
        DatosTokenResponse token = authService.login(datos);
        return ResponseEntity.ok(token);
    }
}