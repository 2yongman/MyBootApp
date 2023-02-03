package com.tuyano.springboot.mybootapp.repository;

import com.tuyano.springboot.mybootapp.entity.MyData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MyDataRepository extends JpaRepository<MyData, Long> {

//    @Query("select d from MyData d ORDER BY d.name")
//    List<MyData> findAllOrderByName();
//
//    @Query("from MyData where age > :min and age < : max")
//    List<MyData> findByAge(@Param("min") int min, @Param("max") int max);

    Optional<MyData> findById(Long id);

    void deleteById(Long id);

    public List<MyData> findByNameLike(String name);

    public List<MyData> findByIdIsNotNullOrderByIdDesc();

    public List<MyData> findByAgeGreaterThan(Integer age);

    public List<MyData> findByAgeBetween(Integer age1, Integer age2);
}
