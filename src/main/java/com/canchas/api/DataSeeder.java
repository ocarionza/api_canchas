package com.canchas.api;

import com.canchas.api.entity.*;
import com.canchas.api.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final SedeRepository sedeRepo;
    private final TipoCanchaRepository tipoRepo;
    private final CanchaRepository canchaRepo;
    private final HorarioRepository horarioRepo;

    @Override
    public void run(String... args) {
        if (sedeRepo.count() > 0) return; // solo carga una vez

        Sede s1 = sedeRepo.save(Sede.builder().nombre("Sede Norte").direccion("Calle 10 #45-20").build());
        Sede s2 = sedeRepo.save(Sede.builder().nombre("Sede Sur").direccion("Av. 30 #12-50").build());

        TipoCancha futbol = tipoRepo.save(TipoCancha.builder().nombre("Fútbol").build());
        TipoCancha tenis  = tipoRepo.save(TipoCancha.builder().nombre("Tenis").build());
        TipoCancha basket = tipoRepo.save(TipoCancha.builder().nombre("Baloncesto").build());

        Cancha c1 = canchaRepo.save(Cancha.builder()
                .nombre("Cancha A").descripcion("Cancha de fútbol 5")
                .capacidad(10).imagenUrl("https://placehold.co/400x300?text=Cancha+A")
                .sede(s1).tipoCancha(futbol).build());

        Cancha c2 = canchaRepo.save(Cancha.builder()
                .nombre("Cancha B").descripcion("Cancha de tenis profesional")
                .capacidad(4).imagenUrl("https://placehold.co/400x300?text=Cancha+B")
                .sede(s1).tipoCancha(tenis).build());

        Cancha c3 = canchaRepo.save(Cancha.builder()
                .nombre("Cancha C").descripcion("Cancha de baloncesto cubierta")
                .capacidad(10).imagenUrl("https://placehold.co/400x300?text=Cancha+C")
                .sede(s2).tipoCancha(basket).build());

        // Horarios lunes a viernes (1-5) para cada cancha
        for (Cancha cancha : List.of(c1, c2, c3)) {
            for (int dia = 1; dia <= 5; dia++) {
                for (int hora = 8; hora <= 20; hora += 2) {
                    horarioRepo.save(Horario.builder()
                            .cancha(cancha)
                            .diaSemana(dia)
                            .horaInicio(LocalTime.of(hora, 0))
                            .horaFin(LocalTime.of(hora + 2, 0))
                            .build());
                }
            }
        }
    }
}