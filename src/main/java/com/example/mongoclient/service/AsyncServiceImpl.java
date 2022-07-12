package com.example.mongoclient.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Slf4j
public class AsyncServiceImpl implements AsyncService {
    @Async
    public void asyncMethod() {
        IntStream.range(1, 10).forEach(i -> {
            try {
                Thread.sleep(1000);
                log.info("i = " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public List<User> testBeanProperty() {
        User user = User.builder().name("Bob").city("Mumbai").age(30).createdBy("admin")
                .createdAt(LocalDateTime.now(ZoneOffset.UTC)).build();
        System.out.println("user = " + user);
        User user1 = User.builder().name("Bob").city("Mumbai").age(30).createdBy("admin")
                .createdAt(LocalDateTime.now(ZoneOffset.UTC)).build();
        System.out.println("user1 = " + user1);
        UserDto dto = UserDto.builder().name("Jhon").build();
        System.out.println("dto = " + dto);

        setUpdatedAllUserData(dto, user);
        System.out.println("after update user = " + user);
        setUpdatedNonUserData(dto, user1);
        System.out.println("after update user1 = " + user1);

        List<User> users =  new ArrayList<>();
        return Arrays.asList(user, user1);

    }

    private void setUpdatedAllUserData(UserDto source, User destination) {
        // copy as it to destination
        BeanUtils.copyProperties(source, destination);
    }

    private void setUpdatedNonUserData(UserDto source, User destination) {
        // copy only non null fields
        BeanUtils.copyProperties(source, destination, getNullPropNames(source));
    }

    private String[] getNullPropNames(UserDto source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] propertyDescriptors = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet<>();
        Arrays.stream(propertyDescriptors).forEach(pd -> {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        });
        String[] nullPropNames = new String[emptyNames.size()];
        return emptyNames.toArray(nullPropNames);
    }
}


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class User {
    private String name;
    private String city;
    private int age;
    private String createdBy;
    @JsonFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss:SSS'Z'")
    private LocalDateTime createdAt;
    private String modifiedBy;
    @JsonFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss:SSS'Z'")
    private LocalDateTime modifiedAt;
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class UserDto {
    private String name;
    private String city;
    private int age;
    private String createdBy;
}
