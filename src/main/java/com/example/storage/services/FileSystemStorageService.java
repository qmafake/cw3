package com.example.storage.services;


import com.example.data.models.Gender_train;
import com.example.data.models.Tr_mcc_codes;
import com.example.data.models.Tr_type;
import com.example.data.models.Transactions;
import com.example.data.repositories.Gender_trainRepository;
import com.example.data.repositories.Tr_mcc_codesRepo;
import com.example.data.repositories.Tr_typesRepo;
import com.example.data.repositories.TransactionsRepo;
import com.example.storage.StorageProperties;
import com.example.storage.exceptions.StorageException;
import com.example.storage.exceptions.StorageFileNotFoundException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Stream;

@Service
@ComponentScan
public class FileSystemStorageService implements StorageService{
    private final Path rootLocation;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());

    }

    @Autowired
   private Gender_trainRepository gender_trainRepository;
    @Autowired
    private Tr_mcc_codesRepo codesRepo;
    @Autowired
    private Tr_typesRepo typesRepo;
    @Autowired
    private TransactionsRepo transactionsRepo;





    @Override
    public void store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file.");
            }
            Path destinationFile = this.rootLocation.resolve(
                            Paths.get(file.getOriginalFilename()))
                    .normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file outside current directory.");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
            }

            switch (file.getOriginalFilename()){
                case "gender_train.csv":
                    save(file, Gender_train.class, gender_trainRepository);
                case "transactions.csv":
                    save(file, Transactions.class, transactionsRepo);
                case "tr_mcc_codes.csv":
                    save(file, Tr_mcc_codes.class, codesRepo);
                case "tr_types.csv":
                    save(file, Tr_type.class, typesRepo);
            }
            // parse CSV file to create a list of `User` objects
//            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
//
//                // create csv bean reader
//                CsvToBean<Gender_train> csvToBean = new CsvToBeanBuilder(reader)
//                        .withType(Gender_train.class)
//                        .withIgnoreLeadingWhiteSpace(false)
//                        .build();
//
//                // convert `CsvToBean` object to list of users
//                List<Gender_train> genders = csvToBean.parse();
//                repository.saveAll(genders);
//            }catch (Exception ex) {
//
//            }
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file.", e);
        }
    }

    @Override
    public <Models> void save(MultipartFile file, Class Models, JpaRepository repos){
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            // create csv bean reader
            CsvToBean<Models> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(Models)
                    .withIgnoreLeadingWhiteSpace(false)
                    .build();

            // convert `CsvToBean` object to list of users
            List<Models> models = csvToBean.parse();
            repos.saveAll(models);

        }catch (Exception ex) {

        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        }
        catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + filename);

            }
        }
        catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

}
