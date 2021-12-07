package com.epam.brest.rest;

import com.epam.brest.model.dto.DepartmentDto;
import com.epam.brest.service.DepartmentDtoService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
class DepartmentDtoControllerTest {

    @InjectMocks
    private DepartmentDtoController departmentDtoController;

    @Mock
    private DepartmentDtoService departmentDtoService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(departmentDtoController)
                .build();
    }

    @AfterEach
    public void end() {
        Mockito.verifyNoMoreInteractions(departmentDtoService);
    }

    @Test
    public void shouldFindAllWithAvgSalary() throws Exception {

        Mockito.when(departmentDtoService.findAllWithAvgSalary()).thenReturn(Arrays.asList(create(0), create(1)));

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/department_dtos")
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].departmentId", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].departmentName", Matchers.is("d0")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].avgSalary", Matchers.is(100)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].departmentId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].departmentName", Matchers.is("d1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].avgSalary", Matchers.is(101)))
        ;

        Mockito.verify(departmentDtoService).findAllWithAvgSalary();
    }

    private DepartmentDto create(int index) {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentId(index);
        departmentDto.setDepartmentName("d" + index);
        departmentDto.setAvgSalary(BigDecimal.valueOf(100 + index));
        return departmentDto;
    }
}