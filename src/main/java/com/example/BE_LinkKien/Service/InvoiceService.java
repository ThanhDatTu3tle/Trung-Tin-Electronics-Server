package com.example.BE_LinkKien.Service;

import com.example.BE_LinkKien.Models.*;
import com.example.BE_LinkKien.Repository.BrandRepository;
import com.example.BE_LinkKien.Repository.InvoiceDetailRepository;
import com.example.BE_LinkKien.Repository.InvoiceRepository;
import com.example.BE_LinkKien.dto.ImageProductDTO;
import com.example.BE_LinkKien.dto.InvoiceDTO;
import com.example.BE_LinkKien.dto.InvoiceDetailDTO;
import com.example.BE_LinkKien.dto.SpecificationDTO;
import com.example.BE_LinkKien.exception.CustomException;
import com.example.BE_LinkKien.payload.response.ProductRespone;
import com.example.BE_LinkKien.payload.resquest.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private InvoiceDetailRepository invoiceDetailRepository;
    @Autowired
    private ModelMapper modelMapper;


    public Invoice createInvpoce(InvoiceDTO data)
    {
        try{
            Invoice invoice = new Invoice();

            List<InvoiceDetail> invoiceDetailList = new ArrayList<>();
            invoice.setCustomerName(data.getCustomerName());
            invoice.setPhone(data.getPhone());
            invoice.setAddress(data.getAddress());
            invoice.setEmail(data.getEmail());
            invoice.setContent(data.getContent());
            invoice.setStatus(data.getStatus());
            invoice.setTotal(data.getTotal());
            invoice.setPayment(data.getPayment());

            Invoice idInvoice = invoiceRepository.save(invoice);

            data.getInvoiceDetail().forEach((e)->{
                InvoiceDetail invoiceDetail1 = new InvoiceDetail();
                invoiceDetail1.setIdInvoice(idInvoice.getId());
                invoiceDetail1.setIdProduct(e.getIdProduct());
                invoiceDetail1.setNumber(e.getNumber());
                invoiceDetailList.add(invoiceDetail1);
            });
            invoiceDetailRepository.saveAll(invoiceDetailList);
            return idInvoice;
        }
        catch (Exception e){
            throw new CustomException("Cant add invoice", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public List<InvoiceDTO> getAllInvoice() {
        List<Invoice> invoices = invoiceRepository.findAll();
        if(invoices == null) {
            throw new CustomException("The invoices list are empty", HttpStatus.NOT_FOUND);
        }

        List<InvoiceDTO> invoiceDTOList = new ArrayList<>();


        invoices.stream().forEach((e)->{
            InvoiceDTO invoiceDTO = new InvoiceDTO();
            invoiceDTO.setId(e.getId());
            invoiceDTO.setCustomerName(e.getCustomerName());
            invoiceDTO.setPhone(e.getPhone());
            invoiceDTO.setEmail(e.getEmail());
            invoiceDTO.setContent(e.getContent());
            invoiceDTO.setAddress(e.getAddress());
            invoiceDTO.setStatus(e.getStatus());
            invoiceDTO.setTotal(e.getTotal());
            invoiceDTO.setPayment(e.getPayment());


            List<InvoiceDetail> invoiceDetailList = invoiceDetailRepository.findAllByIdInvoice(e.getId());
            List<InvoiceDetailDTO> invoiceDetailDTOList = invoiceDetailList.stream()
                    .map(accounts -> modelMapper.map(accounts, InvoiceDetailDTO.class))
                    .collect(Collectors.toList());
            invoiceDTO.setInvoiceDetail(invoiceDetailDTOList);
            invoiceDTOList.add(invoiceDTO);
        });
        return invoiceDTOList;
    }
    public InvoiceDTO getInvoiceById(Integer id) {
        Invoice invoice = invoiceRepository.findInvoiceById(id);
        if(invoice == null) {
            throw new CustomException("The invoice is not exists ", HttpStatus.NOT_FOUND);
        }
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setId(invoice.getId());
        invoiceDTO.setCustomerName(invoice.getCustomerName());
        invoiceDTO.setPhone(invoice.getPhone());
        invoiceDTO.setEmail(invoice.getEmail());
        invoiceDTO.setContent(invoice.getContent());
        invoiceDTO.setAddress(invoice.getAddress());
        invoiceDTO.setStatus(invoice.getStatus());
        invoiceDTO.setTotal(invoice.getTotal());
        invoiceDTO.setPayment(invoice.getPayment());


        List<InvoiceDetail> invoiceDetailList = invoiceDetailRepository.findAllByIdInvoice(invoice.getId());
        List<InvoiceDetailDTO> invoiceDetailDTOList = invoiceDetailList.stream()
                .map(accounts -> modelMapper.map(accounts, InvoiceDetailDTO.class))
                .collect(Collectors.toList());
        invoiceDTO.setInvoiceDetail(invoiceDetailDTOList);

        return invoiceDTO;
    }

}
