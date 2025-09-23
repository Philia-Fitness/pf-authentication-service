package io.github.philiafitness.pfauthenticationservice.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
@Slf4j
public abstract class JsonMapper {
    private ObjectMapper objectMapper;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Named("toJson")
    public <T> String toJson(T object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.warn("JSONERROR - cannot serialize following object: {}", object);
            //Per non rischiare disallineamenti tra wallet e sportsbook,
            //loggo l'errore e faccio proseguire il processo per non rollbackare l'intero processo
            return "";
        }
    }

}