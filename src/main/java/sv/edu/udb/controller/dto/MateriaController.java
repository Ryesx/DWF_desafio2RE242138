package sv.edu.udb.controller.dto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.domain.Materia;
import sv.edu.udb.repository.MateriaRepository;

import java.util.List;

@RestController
@RequestMapping("/api/materias")
@RequiredArgsConstructor
public class MateriaController {

    private final MateriaRepository materiaRepository;

    @GetMapping
    public List<Materia> findAll() {
        return materiaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Materia findById(@PathVariable Long id) {
        return materiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Materia no encontrada"));
    }

    @PostMapping
    public Materia save(@RequestBody Materia materia) {
        return materiaRepository.save(materia);
    }
}
