package com.example.cadastroaplicacao.controllers;

import java.time.Instant;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.cadastroaplicacao.models.ToItem;
import com.example.cadastroaplicacao.repositories.ToItemRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ToFormController {
  private final Logger logger = LoggerFactory.getLogger(ToFormController.class);

  @Autowired
  private ToItemRepository todaItemRepository;

  @GetMapping("/create-todo")
  public String showCreateForm(ToItem toItem) {
      return "add-todo-item";
  }
  

  @GetMapping("/edit/{id}")
  public String showUpdateForm(@PathVariable("id")long id,Model model) {
    ToItem toItem = todaItemRepository
    .findById(id)
    .orElseThrow(()->new IllegalArgumentException("item id:"+ id +"Não encontrado"));

  model.addAttribute("todo", toItem);

  return "update-todo-item";
  }

  @PostMapping("/todo/{id}")
  public String updateToItem(@PathVariable("id")long id, @Valid ToItem toItem, BindingResult result, Model model) {
    //TODO: process POST request
    if(result.hasErrors()){
      toItem.setId(id);
      return "update-todo-item";
    }
    toItem.setModifiedDate(Instant.now());
    todaItemRepository.save(toItem);
    return "redirect:/";
  }

  @GetMapping("/delete/{id}")
  public String deleteToItem(@PathVariable("id")long id, Model model) {
    ToItem toItem = todaItemRepository
    .findById(id)
    .orElseThrow(()->new IllegalArgumentException("Item id:"+id+"Produto não encontrado"));

  todaItemRepository.delete(toItem);
  return "redirect:/";
  }
}
