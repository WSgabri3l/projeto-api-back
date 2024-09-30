package br.com.havan.controller;



import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.havan.FileStorageProperties;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

//Use esse comando tendo aberto o terminal para enviar uma imagem.
//Substitua o "file=" para trocar a imagem que deseja enviar.
// curl -X POST -F "file=@test-punpun.png" http://localhost:8080/products/files/upload

@Controller
@RequestMapping("products/files")
public class FileStorageController {

    /* Atributos */
    
    //Path é a API de arquivos do Java.
    //Essa propriedade cuida dos caminhos dos arquivos.
    private final Path fileStorageLoation;

    /* Construtor */

    //Aqui no construtor, injetamos a Propriedade FileStorageProperties, que tem um caminho relativo
    //e inicializamos a o Path. 
    //Relativo quer dizer que é preciso buscar a rota dentro da máquina.
    public FileStorageController(FileStorageProperties fileStorageProperties){

        //A classe Paths será usada para pegar a rota. 
        //Ela transforma o caminho em absoluto e normaliza, ou seja, remove pontos(.), ponto ponto (..).
        this.fileStorageLoation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
    }

    // --------------------------------------------------------------------------------------------------------------------

    /* Métodos */

    /* Métodos - Post */

    //uploadiFile método que retornará uma confirmação de upload da imagem.
    @PostMapping("/upload")
    //Primeiramente, iremos passar o arquivo inteiro na requisição usando MultipartFile.
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){

        //O nome do arquivo será baseado no arquivo passado.
        //Para isso, usamos StringUtils.cleanPath() para normalizar e evitar malícias como acessar outros caminhos da aplicação.
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        //Aqui, criamos um TRY-CATCH para lidar com os IOs que venham com a requisição.
        try {

            //Esse é o alvo da transferência, lugar de envio.
            //Primeiro pegamos o arquivo, resolvemos a partir do arquivo
            //(Isso significa concatenar o local do arquivo com o seu nome)
            Path targetLocation = fileStorageLoation.resolve(fileName);

            // Imprimindo a Rota da Imagem a fim de saber onde fica
            System.out.println(targetLocation.toString());


            //E, por fim, o transferimos.
            file.transferTo(targetLocation);

            //Depois informamos a URI de download com ServletUriComponentsBuilder do Spring Web
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("products/files/download").path(fileName).toString();

            //Por fim, enviamos uma resposta de Upload concluído com o link para Download da imagem.

            /*
             * return ResponseEntity.ok("Upload Completo. Download Link: " + fileDownloadUri);
             * return ResponseEntity.ok(targetLocation.toString());
             */
            return ResponseEntity.ok("Upload Completo. Download Link: " + fileDownloadUri);
        } catch (IOException ex){

            //Caso aconteça um erro, eviamos uma mensagem de BadRequest.
            return ResponseEntity.badRequest().build();
        }
    }

    /* Métodos - Get */

    //Geralmente, rotas desconsideram o tipo do arquivo (ex: .txt, .png, .gif)
    //para considerá-los, usaremos .+
    @GetMapping("/download/{fileName:.+}")
    //Esse Resource já será o arquivo para baixar.
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) throws IOException{

        Path filePath = fileStorageLoation.resolve(fileName).normalize();

        //Aqui novamente é usado um TRY-CATCH, pois, se for colocado algo que não exista na URL, será lançada uma exceção.
        try{

            //Essa é a obtençãp do arquivo que é transformado em uma URI
            Resource resource = new UrlResource(filePath.toUri());

            //A resposta terá um tipo específico, isso quer dizer que:
            //se for um texto, a extensão seria TXT, se fosse uma imagem um JPEG.
            //isso trará o tipo do arquivo.

            //Assim, GetMimeType captura o tipo do arquivo.
            //Se o arquivo não existir, lançamos uma exceção, por essa razão o thrwos está no método.                               
            String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());

            //Agora, caso ele não seja encontrado, utilizaremos um tipo genérico.
            
            if(contentType == null) contentType = "application/octet-stream";

            //Agora retornamos uma ResponseEntity com o código Okay, ou seja, deu certo.
            //Passamos o contentType convertido para Media com o MediaType.
            //Depois, passamos um header e body para informar que há um arquivo no anexo.
            return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(contentType))
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
            .body(resource);
            
        } catch(MalformedURLException e){

            return ResponseEntity.badRequest().build();
        }
    }

    // --------------------------------------------------------------------------------------------------------------------

    @RequestMapping(path = "/sendImage", method = RequestMethod.POST)
    public String imageSubmission(@ModelAttribute @RequestParam("file") MultipartFile file){

        uploadFile(file);

        return "redirect:success.html";
    }

}
