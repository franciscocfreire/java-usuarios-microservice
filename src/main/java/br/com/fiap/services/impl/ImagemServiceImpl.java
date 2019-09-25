package br.com.fiap.services.impl;

import br.com.fiap.domain.Imagem;
import br.com.fiap.services.ImagemService;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.OperationContext;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.*;
import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypes;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class ImagemServiceImpl implements ImagemService {

    public static final String storageConnectionString =
            "DefaultEndpointsProtocol=https;" +
                    "AccountName=cloudfiapstorage;" +
                    "AccountKey=sx6r0GcgMjJcGrLkqt3LGTXewp0ogF4BFggzqZd5mhLdiGCUzrnNTSeniuN5I5iAePehl24pQWn42KForjXhrw==";


    @Override
    public String uploadImage(MultipartFile imageData) {
        File sourceFile = null;
        File downloadedFile = null;
        String name = null;

        CloudStorageAccount storageAccount;
        CloudBlobClient blobClient = null;
        CloudBlobContainer container=null;


        try {
            // Parse the connection string and create a blob client to interact with Blob storage
            storageAccount = CloudStorageAccount.parse(storageConnectionString);
            blobClient = storageAccount.createCloudBlobClient();
            container = blobClient.getContainerReference("cloudfiapcontainer");

            // Create the container if it does not exist with public access.
            System.out.println("Creating container: " + container.getName());
            container.createIfNotExists(BlobContainerPublicAccessType.CONTAINER, new BlobRequestOptions(), new OperationContext());


            MimeTypes allTypes = MimeTypes.getDefaultMimeTypes();
            MimeType extesion = allTypes.forName(imageData.getContentType());
            String stringExtesion = extesion.getExtension(); // .jpg

            UUID uuid = UUID.randomUUID();
            String uuidStr = uuid.toString();




            //Creating a sample file
            sourceFile = File.createTempFile(uuidStr, stringExtesion);
            /*
            System.out.println("Creating a sample file at: " + sourceFile.toString());
            Writer output = new BufferedWriter(new FileWriter(sourceFile));
            output.write("Hello");
            output.close();*/

            FileOutputStream fos = new FileOutputStream(sourceFile);
            fos.write(imageData.getBytes());
            fos.close();
            //sourceFile = convert(imageData);
            //sourceFile.

            //Getting a blob reference
            CloudBlockBlob blob = container.getBlockBlobReference(sourceFile.getName());

            name = blob.getUri().toString();

            //Creating blob and uploading file to it
            System.out.println("Uploading the sample file ");
            blob.uploadFromFile(sourceFile.getAbsolutePath());

            //Listing contents of container
            for (ListBlobItem blobItem : container.listBlobs()) {
                System.out.println("URI of blob is: " + blobItem.getUri());
            }

            // Download blob. In most cases, you would have to retrieve the reference
            // to cloudBlockBlob here. However, we created that reference earlier, and
            // haven't changed the blob we're interested in, so we can reuse it.
            // Here we are creating a new file to download to. Alternatively you can also pass in the path as a string into downloadToFile method: blob.downloadToFile("/path/to/new/file").

            //downloadedFile = new File(sourceFile.getParentFile(), "downloadedFile.txt");
            //blob.downloadToFile(downloadedFile.getAbsolutePath());
        }
        catch (StorageException ex)
        {
            System.out.println(String.format("Error returned from the service. Http code: %d and error code: %s", ex.getHttpStatusCode(), ex.getErrorCode()));
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        finally
        {
            System.out.println("The program has completed successfully.");
            System.out.println("Press the 'Enter' key while in the console to delete the sample files, example container, and exit the application.");

/*            //Pausing for input
            Scanner sc = new Scanner(System.in);
            sc.nextLine();

            System.out.println("Deleting the container");
            try {
                if(container != null)
                    container.deleteIfExists();
            }
            catch (StorageException ex) {
                System.out.println(String.format("Service error. Http code: %d and error code: %s", ex.getHttpStatusCode(), ex.getErrorCode()));
            }

            System.out.println("Deleting the source, and downloaded files");

            if(downloadedFile != null)
                downloadedFile.deleteOnExit();

            if(sourceFile != null)
                sourceFile.deleteOnExit();

            //Closing scanner
            sc.close();*/
        }

        return name;
    }


    public static File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

}

