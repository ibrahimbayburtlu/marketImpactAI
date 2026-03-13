package com.ibrahimbayburtlu.MarketImpactAI.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibrahimbayburtlu.MarketImpactAI.entity.Company;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class CompanyService {

    private Map<String, Company> companies = new HashMap<>();

    @PostConstruct
    public void loadCompanies() throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        InputStream inputStream =
                new ClassPathResource("data/companies.json").getInputStream();

        JsonNode root = mapper.readTree(inputStream);

        for (JsonNode node : root.get("companies")) {

            Company company = mapper.treeToValue(node, Company.class);

            companies.put(company.getName().toLowerCase(), company);
        }
    }

    public Company getCompany(String name) {
        return companies.get(name.toLowerCase());
    }
}