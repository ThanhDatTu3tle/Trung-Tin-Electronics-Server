package com.example.BE_LinkKien.Repository;

import com.example.BE_LinkKien.Models.Brand;
import com.example.BE_LinkKien.Models.Invoice;
import com.example.BE_LinkKien.Models.InvoiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetail, Integer> {
    List<InvoiceDetail> findAllByIdInvoice(Integer id);
}
