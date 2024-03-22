package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;

import java.util.Collection;

import java.util.List;

@Repository
public interface AdsRepository extends JpaRepository<Ads, Integer> {
    List<Ads> findAllByUser (User user);

    /**
     * метод возвращает все объявления при частичном совпадении названия или описания объявления
     */
    @Query(value = "SELECT a.* " +
            "FROM ads a " +
            "WHERE title LIKE '%?1%' " +
            "OR description LIKE '%?1%'", nativeQuery = true)
    Collection<Ads> findByAdsNameOrDescriptionIgnoreCase(String text);

}
