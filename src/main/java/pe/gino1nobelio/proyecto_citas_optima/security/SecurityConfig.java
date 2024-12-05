package pe.gino1nobelio.proyecto_citas_optima.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    public SecurityConfig(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(
                        exceptionHandling -> exceptionHandling.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/sesion/**").permitAll()
                        .requestMatchers("/usuario/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/cita/crear").hasAuthority("CLIENTE")
                        .requestMatchers(HttpMethod.PUT, "/cita/asignar/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/cita/tomar/**").hasAuthority("AGENTE")
                        .requestMatchers(HttpMethod.PUT, "/cita/cerrar/**").hasAuthority("AGENTE")
                        .requestMatchers(HttpMethod.PUT, "/cita/reabrir/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/cita/eliminar/**").hasAnyAuthority("ADMIN", "AGENTE")
                        .requestMatchers(HttpMethod.GET, "/cita/lista").permitAll()
                        .requestMatchers(HttpMethod.GET, "/cita/**").permitAll()
                        .anyRequest().authenticated())
                .httpBasic(httpBasic -> {
                });
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}