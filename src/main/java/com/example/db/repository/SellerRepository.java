package com.example.db.repository;

import com.example.db.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SellerRepository extends JpaRepository<Seller, Long> {

       @Query(value = "SELECT sp.seller_id, sp.seller_name, sp.total_profit, " +
            "sa.sum AS sale_sum, " +
            "am.name AS auto_model_name, amk.name AS auto_mark_name, sa.date, sa.client.fullName " +
            "FROM (" +
            "    SELECT s.id AS seller_id, s.fullName AS seller_name, COALESCE(SUM(sa.sum), 0) AS total_profit " +
            "    FROM Seller s " +
            "    LEFT JOIN Sale sa ON s.id = sa.seller.id " +
            "    GROUP BY s.id, s.fullName " +
            "    ORDER BY total_profit DESC " +
            "    LIMIT 1" +
            ") sp " +
            "JOIN Sale sa ON sp.seller_id = sa.seller.id " +
            "JOIN Automobile a ON sa.automobile.id = a.id " +
            "JOIN AutomobileModel am ON a.automobileModel.id = am.id " +
            "JOIN AutomobileMark amk ON am.automobileMark.id = amk.id " +
            "ORDER BY total_profit DESC ")
    List<Object[]> findTopSellingSeller();

}


