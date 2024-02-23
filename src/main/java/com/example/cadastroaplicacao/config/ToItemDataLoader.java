package com.example.cadastroaplicacao.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.cadastroaplicacao.models.ToItem;
import com.example.cadastroaplicacao.repositories.ToItemRepository;


@Component
public class ToItemDataLoader implements CommandLineRunner{
  private final Logger logger = LoggerFactory.getLogger(ToItemDataLoader.class);

  @Autowired
  ToItemRepository todaItemRepository;

  @Override

  public void run(String...args)throws Exception{
    loadData();
  }

  private void loadData(){
    if(todaItemRepository.count()==0){
    
    }

    logger.info("Numero de produtos:{}", todaItemRepository.count());
  }

}
