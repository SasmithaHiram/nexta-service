package com.sasmitha.lms.service;

import com.sasmitha.lms.dto.ModuleDTO;
import com.sasmitha.lms.model.Module;
import com.sasmitha.lms.repository.ModuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ModuleServiceImpl {
    private final ModuleRepository moduleRepository;

    public ModuleDTO create(ModuleDTO moduleDTO) {
        Module module = new Module();
        module.setTitle(moduleDTO.getTitle());
        module.setDescription(moduleDTO.getDescription());
        moduleRepository.save(module);

        return new ModuleDTO(module.getTitle(), module.getDescription());
    }

    public List<ModuleDTO> getAll() {
        List<Module> modulesFromDB = moduleRepository.findAll();

        if (modulesFromDB.isEmpty()) {
            return Collections.emptyList();
        } else {
            return modulesFromDB.stream().map(module -> new ModuleDTO(
                    module.getTitle(),
                    module.getDescription()
            )).toList();
        }
    }
}
