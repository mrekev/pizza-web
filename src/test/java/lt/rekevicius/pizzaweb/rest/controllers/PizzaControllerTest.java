package lt.rekevicius.pizzaweb.rest.controllers;

import lt.rekevicius.pizzaweb.core.entities.impl.Pizza;
import lt.rekevicius.pizzaweb.repositories.specifications.PizzaQuerySpecification;
import lt.rekevicius.pizzaweb.repositories.specifications.QuerySpecification;
import lt.rekevicius.pizzaweb.repositories.PizzaEntityRepository;
import lt.rekevicius.pizzaweb.rest.resources.asm.PizzaResourceAsm;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Mindaugas on 2017-03-20.
 */
public class PizzaControllerTest {

    @InjectMocks
    private PizzaController controller;

    @Mock
    private PizzaEntityRepository pizzaEntityRepository;

    @Mock
    private PizzaResourceAsm pizzaResourceAsm;

    private MockMvc mockMvc;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void get_existing_pizza() throws Exception {
        Pizza pizza = new Pizza();
        pizza.setTitle("Belekas");
        pizza.setId(1L);
        List<Pizza> pizzas = new ArrayList<>();
        pizzas.add(pizza);

        when(pizzaEntityRepository.query(any(PizzaQuerySpecification.class))).thenReturn(pizzas);
        when(pizzaResourceAsm.toResource(any(Pizza.class))).thenCallRealMethod();

        mockMvc.perform(get("/rest/pizzas/1")).andDo(print())
                .andExpect(jsonPath("$.title", is("Belekas")))
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/pizzas/1"))))
                .andExpect(status().isOk());
    }

    @Test
    public void get_non_existing_pizza() throws Exception {
        List<Pizza> pizzas = new ArrayList<>();

        when(pizzaEntityRepository.query(any(PizzaQuerySpecification.class))).thenReturn(pizzas);

        mockMvc.perform(get("/rest/pizzas/1")).andDo(print())
                .andExpect(status().isNotFound());
    }
}