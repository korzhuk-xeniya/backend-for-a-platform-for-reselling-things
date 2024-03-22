package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.skypro.homework.entity.Ads;

import java.util.Collection;

public interface AdsRepository extends JpaRepository<Ads, Integer> {

    /**
     * метод возвращает все объявления при частичном совпадении названия или описания объявления
     */
    @Query(value = "SELECT a.* " +
            "FROM ads a " +
            "WHERE title LIKE '%?1%' " +
            "OR description LIKE '%?1%'", nativeQuery = true)
    Collection<Ads> findByAdsNameOrDescriptionIgnoreCase(String text);

}
