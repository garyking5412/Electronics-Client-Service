package com.example.electronicsspringbootclientservice.controller;

import com.example.electronicsspringbootclientservice.DTO.MessageDTO;
import com.example.electronicsspringbootclientservice.DTO.invoiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Objects;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

//    @Autowired
//    private KafkaTemplate<String, Object> kafkaTemplate;
//
//    @PostMapping("/create")
//    public String create(@RequestBody invoiceDTO dto) {
//        String response = "";
//        if (Objects.nonNull(dto.getInvoiceTotal())) {
//            MessageDTO messageDTO = new MessageDTO();
//            messageDTO.setTo("resetducoteau5412@gmail.com");
//            messageDTO.setContent("your invoice has been recorded successfully! on the day of "+new Date().getDate());
//            messageDTO.setToName("Takemi Kazuchi o mikoto!");
//            invoiceDTO bill = new invoiceDTO();
//            bill.setInvoiceDate(new Date());
//            bill.setInvoiceTotal(dto.getInvoiceTotal());
//            bill.setBillDetailList(dto.getBillDetailList());
//            kafkaTemplate.send("notification",messageDTO);
//            kafkaTemplate.send("invoice",bill);
//            response = ">>>>>>>>>>>> saving invoice infor >>>>>>>>>>>>>";
//        } else {
//            response = ">>>>>>>>>> invalid invoice infor >>>>>>>";
//        }
//        return response;
//    }
}
