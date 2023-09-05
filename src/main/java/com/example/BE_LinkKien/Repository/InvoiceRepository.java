package com.example.BE_LinkKien.Repository;

import com.example.BE_LinkKien.Models.Brand;
import com.example.BE_LinkKien.Models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    Invoice findInvoiceById(Integer id);
}
