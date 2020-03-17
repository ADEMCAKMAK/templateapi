package com.springboot.template.core.entity.id.base;

import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

public final class StringToPrimaryKeyConverterFactory implements ConverterFactory<String, CompoundKey> {

    @Override
    public <T extends CompoundKey> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToPrimaryKeyConverter<>(targetType);
    }

    private static final class StringToPrimaryKeyConverter<T extends CompoundKey> implements Converter<String, T> {

        private Class<T> primaryKeyClass;

        StringToPrimaryKeyConverter(Class<T> primaryKey) {
            this.primaryKeyClass = primaryKey;
        }

        public T convert(String source) {

            T id = BeanUtils.instantiateClass(this.primaryKeyClass);

            id.resolveCompoundKey(source);

            return id;

        }
    }

}
