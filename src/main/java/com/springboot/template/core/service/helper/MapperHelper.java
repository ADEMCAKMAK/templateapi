package com.springboot.template.core.service.helper;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.Type;
import org.apache.commons.lang3.StringUtils;

public class MapperHelper {

    private static MapperFacade orikaMapper;

    private MapperHelper() {
    }

    private static void initOrikaMapper() {

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        ConverterFactory converterFactory = mapperFactory.getConverterFactory();

        converterFactory.registerConverter(new EmptyStringConverter());

        orikaMapper = mapperFactory.getMapperFacade();

    }

    public static MapperFacade getMapper() {
        return MapperHelper.getOrikaMapper();
    }

    private static MapperFacade getOrikaMapper() {

        if (orikaMapper == null)
            initOrikaMapper();

        return orikaMapper;

    }

    private static class EmptyStringConverter extends CustomConverter<String, String> {

        @Override
        public String convert(String value, Type<? extends String> type, MappingContext mappingContext) {
            return StringUtils.isBlank(value) ? null : value;
        }

    }

}
