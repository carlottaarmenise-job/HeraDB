package it.lavoro.hera_db.menu;

import it.lavoro.hera_db.auth.locations.Location;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@RequiredArgsConstructor
@Validated
public class MenuService {
    private final MenuRepository menuRepository;

    public List<Piatto> getMenu() {
        return menuRepository.findAll();
    }

    public Piatto create(@Valid PiattoRequest piattoRequest, Categoria categoria, Location location) {
        Piatto piatto = new Piatto();
        piatto.setNomePiatto(piattoRequest.getNomePiatto());
        piatto.setPrezzo(piattoRequest.getPrezzo());
        piatto.setCategoriaPiatto(categoria);
        piatto.setLocation(location);
        return menuRepository.save(piatto);
    }

    public Piatto update(Long id, @Valid PiattoRequest piattoRequest, Categoria categoria, Location location) {
        Piatto piatto = menuRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Piatto non trovato"));
        piatto.setNomePiatto(piattoRequest.getNomePiatto());
        piatto.setPrezzo(piattoRequest.getPrezzo());
        piatto.setCategoriaPiatto(categoria);
        piatto.setLocation(location);
        return menuRepository.save(piatto);
    }

    public void delete(Long id, Location location) {
        Piatto piatto = menuRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Piatto non trovato"));
        if (piatto.getLocation().equals(location)) {
            menuRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Piatto non trovato");
        }
    }


}
