package com.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

public class Function {
    

    DAOCidade cidadeDAO = new DAOCidade();

    @FunctionName("cidade-Create")
    public Cidade create(
            @HttpTrigger(name = "createC", 
            methods = { HttpMethod.POST }, route = "city") Cidade cidade) {
        return cidadeDAO.create(cidade);
    }

    @FunctionName("cidade-Read")
    public List<Cidade> read(
            @HttpTrigger(name = "readC",
             methods = { HttpMethod.GET }, route = "city") String cidade) {
        return cidadeDAO.read();
    }

    @FunctionName("cidade-Update")
    public Cidade update(
            @HttpTrigger(name = "updateC", 
            methods = { HttpMethod.PUT }, route = "city") Cidade cidade) {
        return cidadeDAO.update(cidade);
    }

    @FunctionName("cidade-Delete")
    public int delete(
        @HttpTrigger(name = "deleteC", 
        methods = {HttpMethod.DELETE }, route = "city/{id}")
         @BindingName("id") Long id) {
        return cidadeDAO.delete(id);
    }

}

@Data
class Cidade {
    private Long id;
    private String nome;
    private Estado estado;

    public Cidade(){

    }
    public Cidade(Long id, String nome, Estado estado) {
        this.id = id;
        this.nome = nome;
        this.estado = estado;
    } 

}


@Data
class Estado {
    private Long id;
    private String nome;

    public Estado(){

    }

    public Estado(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

}

class DAOCidade {


    public Cidade create(Cidade cid) {

        Cidade cidade = new Cidade();
        cidade.setNome(cid.getNome());

        return cid;
    }

    public List<Cidade> read() {
        
       return Stream.of( 
            new Cidade(1L, "SAOFNAPSKMD", new Estado(1L, "PR")),
                new Cidade(3L, "QWRETRY", new Estado(3L, "SP")),
                    new Cidade(4L, "POIOUY", new Estado(4L, "SC")),
                        new Cidade(5L, "MVJCJNCS", new Estado(5L, "RJ"))  
             ).collect(Collectors.toList());
        
    }

    public Cidade update(Cidade cid) {
        cid.setId(cid.getId() + 3L);
        cid.setNome(cid.getNome());
        return cid;
    }

    public int delete(Long id) {
        return 200;
    }

}
