package br.com.suportware.rest.util;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Component
public class CompactadorUtil {

  static final int TAMANHO_BUFFER = 4096;

  public void compactarParaZip(String arqSaida,List<String> arqEntrada) throws IOException {

    byte[] dados = new byte[TAMANHO_BUFFER];


    try (FileOutputStream destino = new FileOutputStream(new File(arqSaida));
         ZipOutputStream saida = new ZipOutputStream(new BufferedOutputStream(destino));
    ) {

      File file = new File();
      FileInputStream streamDeEntrada = new FileInputStream(file);
      BufferedInputStream origem = new BufferedInputStream(streamDeEntrada, TAMANHO_BUFFER);

      ZipEntry entry = new ZipEntry(file.getName());
      saida.putNextEntry(entry);

      int cont;
      while ((cont = origem.read(dados, 0, TAMANHO_BUFFER)) != -1) {
        saida.write(dados, 0, cont);
      }
    }
  }
}