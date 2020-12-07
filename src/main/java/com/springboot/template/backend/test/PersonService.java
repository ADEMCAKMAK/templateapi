package com.springboot.template.backend.test;

import com.springboot.template.core.mapper.EntityModelMapper;
import com.springboot.template.core.repository.base.BaseRepository;
import com.springboot.template.core.service.base.BaseService;
import com.springboot.template.core.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class PersonService
        extends BaseServiceImpl<Person, Long, PersonModel>
        implements BaseService<Person, Long, PersonModel> {
    protected PersonService(BaseRepository<Person, Long> repository,
                            EntityModelMapper<Person, PersonModel> entityModelMapper) {
        super(repository, entityModelMapper);
    }
}
